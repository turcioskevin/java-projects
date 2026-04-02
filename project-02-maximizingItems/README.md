# Genetic Algorithm Optimization (Java)

## Description
This project implements a **Genetic Algorithm** to solve an optimization problem similar to the knapsack problem. The algorithm evolves a population of possible solutions over multiple generations to maximize total value while respecting a weight constraint.

Each solution (chromosome) represents a combination of items, and the algorithm applies selection, crossover, and mutation to improve results over time.

## Features
- Reads item data from an external file (`items.txt`)
- Generates a population of random solutions
- Evolves solutions over multiple generations
- Implements core genetic algorithm operations:
  - Selection
  - Crossover
  - Mutation
- Uses fitness-based sorting to retain the best solutions
- Outputs the best solution found

## How It Works

### 1. Initialization
- A population of chromosomes is created
- Each chromosome randomly includes/excludes items

### 2. Selection
- The population is duplicated to increase candidate solutions

### 3. Crossover
- Pairs of chromosomes combine to produce offspring
- Each gene (item) is randomly selected from one of the parents

### 4. Mutation
- Randomly flips item inclusion with a small probability (10%)

### 5. Fitness Evaluation
- Fitness = total value of selected items
- If total weight exceeds 10 lbs → fitness = 0

### 6. Survival of the Fittest
- Population is sorted by fitness
- Top-performing chromosomes are retained for the next generation

## Concepts Used
- Genetic Algorithms
- Object-Oriented Programming (OOP)
- ArrayLists and Collections
- File I/O (`Scanner`, `File`)
- Randomization
- Comparable interface for sorting

## Project Structure
```
GeneticAlgorithm.java   // Main driver class
Chromosome.java        // Represents a candidate solution
Item.java              // Represents an individual item
items.txt              // Input data file
```

## Example Output
```
Best individual:
Laptop (3.0 lbs, $2000)
Phone (1.0 lbs, $1000)
Fitness: 3000
```

## How to Run
```bash
javac *.java
java GeneticAlgorithm
```

## Requirements
- Java JDK installed
- `items.txt` file in the same directory

### Example `items.txt` Format
```
Laptop, 3.0, 2000
Phone, 1.0, 1000
Tablet, 2.0, 1500
Headphones, 0.5, 300
```

## Key Design Decisions
- Chromosomes extend `ArrayList<Item>` for flexibility
- Fitness-based sorting via `Comparable`
- Mutation probability set to ~10% for balance between exploration and stability

## Future Improvements
- Add configurable weight limits
- Implement tournament or roulette selection
- Track generation statistics (best/average fitness)
- Add visualization of evolution over time
- Parallelize algorithm for performance
