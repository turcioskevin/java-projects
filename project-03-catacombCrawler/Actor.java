import java.util.Random;
import java.util.Scanner;

/**
 * Represents an actor in the Catacomb Crawler game.
 * An actor can be either the hero or a monster.
 */
public class Actor {

    public String name; // The name of the actor
    public int health; // The health points of the actor
    public int damage = 10; // The maximum damage the actor can inflict
    public int row; // The row position of the actor in the catacomb
    public int col; // The column position of the actor in the catacomb

    /**
     * Constructs an actor with default values.
     */
    public Actor() {

        this.name = "";
        this.health = 0;
        this.row = 0;
        this.col = 0;

    }

    /**
     * Constructs an actor with the specified attributes.
     * @param name The name of the actor
     * @param health The health points of the actor
     * @param row The row position of the actor in the catacomb
     * @param col The column position of the actor in the catacomb
     */
    public Actor(String name, int health, int row, int col) {

        this.name = name;
        this.health = health;
        this.row = row;
        this.col = col;

    }

    /**
     * Checks if the actor is alive.
     * @return true if the actor is alive, false otherwise
     */
    public boolean isAlive() {

        return health > 0;

    }

    /**
     * Checks if the actor has escaped from the catacomb.
     * @param dungeonSize The size of the catacomb
     * @return true if the actor has escaped, false otherwise
     */
    public boolean hasEscaped(int dungeonSize) {

        return row == dungeonSize - 1 && col == dungeonSize - 1;

    }

    /**
     * Checks if the actor is in the same room as another actor.
     * @param other The other actor
     * @return true if the actor is in the same room, false otherwise
     */
    public boolean inSameRoom(Actor other) {

        return row == other.row && col == other.col;

    }

    /**
     * Checks if the actor is in an adjacent room to another actor.
     * @param other The other actor
     * @return true if the actor is in an adjacent room, false otherwise
     */
    public boolean inAdjacentRoom(Actor other) {

        return Math.abs(row - other.row) + Math.abs(col - other.col) == 1;

    }

    /**
     * Inflicts damage on another actor during combat.
     * @param other The other actor to be attacked
     */
    public void hit(Actor other) {

        Random rand = new Random();
        int damage;

        if (name.startsWith("Monster")) {

            damage = rand.nextInt(5) + 1; // Monster's damage between 1 and 5

        } else {

            damage = rand.nextInt(10) + 1; // Player's damage between 1 and 10

        }
        other.health -= damage;

        // Output to display dealt damage
        if (name.startsWith("Monster")) {

            System.out.println(name + " attacks you for " + damage + " damage.");

        } else {

            System.out.println("You attack " + other.name + " for " + damage + " damage.");

        }
    }

    /**
     * Moves actor in the direction within the catacomb.
     * @param direction move north/south/east/west
     * @param dungeonSize size of the catacomb
     * @return true if the actor moves, otherwisee it'll be false
     */
    public boolean move(String direction, int dungeonSize) {

        switch (direction.toLowerCase()) {
            case "north":
                if (row > 0) {
                    row--;
                    return true;
                }
                break;
            case "south":
                if (row < dungeonSize - 1) {
                    row++;
                    return true;
                }
                break;
            case "west":
                if (col > 0) {
                    col--;
                    return true;
                }
                break;
            case "east":
                if (col < dungeonSize - 1) {
                    col++;
                    return true;
                }
                break;
        }

        return false; // returns false if the move isn't possible

    }

    /**
     * takes user input to create actor
     * @param scanner uses scanner input as input for making actor
     * @return returns character with default stats
     */
    public static Actor makeActor(Scanner scanner) {

        System.out.print("Enter the name of your hero: ");
        String name = scanner.nextLine();

        return new Actor(name, 100, 0, 0);
    }

    /**
     * Retrieves the size of the catacomb from user input.]
     * @param scanner uses scanner object to read user input
     * @return The size of the catacomb (between 5 and 10 inclusive)
     */
    public static int getDungeonSize(Scanner scanner) {

        int size;

        do {

            System.out.print("Enter the size of the catacomb (between 5 and 10 inclusive): ");
            size = scanner.nextInt();

        } while (size < 5 || size > 10);

        scanner.nextLine(); // consume newline
        
        return size;
    }
}
