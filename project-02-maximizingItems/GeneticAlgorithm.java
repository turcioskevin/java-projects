import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * This class implements a genetic algorithm to optimize a selection of items.
 * The algorithm evolves a population of chromosomes over multiple generations 
 * to maximize the value of items while considering weight constraints.
 */
public class GeneticAlgorithm {

    /**
     * Reads item data from a specified file and creates a list of items.
     *
     * @param the name of the file containing item data.
     * @return an ArrayList of Item objects created from the file data.
     * @throws FileNotFoundException if the specified file cannot be found.
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        
        while (scanner.hasNextLine()) {
            String[] fileInfo = scanner.nextLine().split(", ");
            String name = fileInfo[0];
            double weight = Double.parseDouble(fileInfo[1]);
            int value = Integer.parseInt(fileInfo[2]);
            items.add(new Item(name, weight, value));
        }
        scanner.close();
        return items;
    }

    /**
     * Initializes a population of chromosomes, each representing a random selection of items.
     *
     * @param items - the list of available items to be included in the chromosomes.
     * @param populationSize - the number of chromosomes to create.
     * @return an ArrayList of initialized Chromosome objects.
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Chromosome(items));
        }
        return population;
    }

    /**
     * The main method that drives the genetic algorithm process.
     * It reads item data, initializes the population, and evolves it over several generations.
     *
     * @param args command line arguments (not used).
     * @throws FileNotFoundException if the item data file cannot be found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Item> items = readData("items.txt");
        ArrayList<Chromosome> population = initializePopulation(items, 10);
        
        for (int generation = 0; generation < 20; generation++) {
            ArrayList<Chromosome> nextGeneration = new ArrayList<>(population);
            
            nextGeneration.addAll(population);
            
            for (int i = 0; i < population.size() / 2; i++) {
                Random rng = new Random();
                Chromosome parent1 = population.get(rng.nextInt(population.size()));
                Chromosome parent2 = population.get(rng.nextInt(population.size()));
                Chromosome child = parent1.crossover(parent2);
                nextGeneration.add(child);
            }

            int mutationCount = (int) (0.1 * nextGeneration.size());
            for (int i = 0; i < mutationCount; i++) {
                Random rng = new Random();
                Chromosome individual = nextGeneration.get(rng.nextInt(nextGeneration.size()));
                individual.mutate();
            }

            Collections.sort(nextGeneration);
            population.clear();
            for (int i = 0; i < 10; i++) {
                population.add(nextGeneration.get(i)); 
            }
        }

        Collections.sort(population);
        System.out.println("Best individual:");
        System.out.println(population.get(population.size() - 1));
    }
}
