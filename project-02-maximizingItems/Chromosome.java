import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a chromosome in a genetic algorithm.
 * This class extends ArrayList<Item> and implements Comparable<Chromosome>.
 * It contains methods for crossover, mutation, and fitness evaluation.
 */
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng = new Random();

    /**
     * Constructs an empty Chromosome.
     */
    public Chromosome() {
        //no argument
    }

    /**
     * Constructs a Chromosome from a given list of items.
     * Each item is cloned, and a random inclusion state is assigned.
     *
     * @param items the list of items to initialize the Chromosome
     */
    public Chromosome(ArrayList<Item> items) {
        for (Item item : items) {
            Item newItem = new Item(item);
            newItem.setIncluded(rng.nextBoolean());
            this.add(newItem);
        }
    }

    /**
     * Performs crossover with another Chromosome to produce a child Chromosome.
     * Randomly selects items from both parents based on a 50% chance.
     *
     * @param other the other Chromosome to crossover with
     * @return a new Chromosome representing the child
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();
        for (int i = 0; i < this.size(); i++) {
            int randomNumber = rng.nextInt(10) + 1;
            if (randomNumber <= 5) {
                child.add(new Item(this.get(i)));
            } else {
                child.add(new Item(other.get(i)));
            }
        }
        return child;
    }

    /**
     * Mutates the Chromosome by randomly toggling the inclusion of items.
     * Each item has a 10% chance of being toggled.
     */
    public void mutate() {
        for (Item item : this) {
            int randomNumber = rng.nextInt(10) + 1;
            if (randomNumber == 1) {
                item.setIncluded(!item.isIncluded());
            }
        }
    }

    /**
     * Calculates the fitness of the Chromosome.
     * The fitness is the total value of included items,
     * or zero if the total weight exceeds 10.
     *
     * @return the fitness score of the Chromosome
     */
    public int getFitness() {
        double totalWeight = 0;
        int totalValue = 0;

        for (Item item : this) {
            if (item.isIncluded()) {
                totalWeight += item.getWeight();
                totalValue += item.getValue();
            }
        }

        if (totalWeight > 10) {
            return 0;
        } else {
            return totalValue;
        }        
    }

    /**
     * Compares this Chromosome with another for sorting based on fitness.
     *
     * @param the other Chromosome to compare against
     * @return a negative integer, zero, or a positive integer as this Chromosome 
     *         is less than, equal to, or greater than the specified Chromosome
     */
    @Override
    public int compareTo(Chromosome other) {
        return Integer.compare(this.getFitness(), other.getFitness());
    }

    /**
     * Pilot AI was used to build this section of code.
     * Returns a string representation of the Chromosome, including the included items
     * and the fitness score.
     *
     * @return a string describing the Chromosome
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item : this) {
            if (item.isIncluded()) {
                result.append(item.toString()).append("\n");
            }
        }
        result.append("Fitness: ").append(getFitness());
        return result.toString();
    }
}
