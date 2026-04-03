
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        Actor hero = Actor.makeActor(scnr);
        int dungeonSize = Actor.getDungeonSize(scnr);
        int numMonsters = (dungeonSize * dungeonSize / 6);
        System.out.println("There are " + numMonsters + " monsters around in the catacomb!");

        ArrayList<Actor> monsters = makeMonsters(dungeonSize);

        System.out.println("Welcome to Catacomb Crawler!");
        System.out.println("Your goal is to escape the catacomb! Just make sure you keep an eye on your health!");
        System.out.println("There are monsters all over the catacomb!!");

        while (true) {

            System.out.println("You are currently in room (" + hero.row + ", " + hero.col + ").");
            System.out.println("You have " + hero.health + " health remaining!");

            int nearbyMonsters = countNearbyMonsters(hero, monsters);
            System.out.println("There are " + nearbyMonsters + " monsters in adjacent rooms.");

            if (hero.hasEscaped(dungeonSize)) {

                System.out.println("Congratulations! You have escaped!");

                break;
            }

            if (!hero.isAlive()) {

                System.out.println("Game Over! You have been defeated.");

                break;
            }

            System.out.print("Which way do you want to move? (north/south/east/west): ");
            String direction = scnr.nextLine();

            if (!move(hero, monsters, dungeonSize, direction)) {

                System.out.println("Invalid move. Try again.");
                
            }

            System.out.println();
        }

        scnr.close();
    }

    /**
     * Generates monsters in the catacomb.
     * @param dungeonSize The size of the catacomb.
     * @return An ArrayList containing the generated monsters.
     */
    public static ArrayList<Actor> makeMonsters(int dungeonSize) {

        ArrayList<Actor> monsters = new ArrayList<>();
        boolean[][] roomOccupied = new boolean[dungeonSize][dungeonSize]; // Track occupied rooms
    
        int numRooms = dungeonSize * dungeonSize;
        int numMonsters = numRooms / 6;
        Random rand = new Random();

        for (int i = 0; i < numMonsters; i++) {

            int row, col;

            do {

                row = rand.nextInt(dungeonSize);
                col = rand.nextInt(dungeonSize);

            } while (row == 0 && col == 0 || roomOccupied[row][col]); // Ensures room is valid and not occupied

            roomOccupied[row][col] = true; // Marks room as occupied
            monsters.add(new Actor("Monster " + (i + 1), 25, row, col));

        }
        return monsters;
    }
    

    /**
     * Counts # of monsters in adj rooms
     * @param actor checks surroundings for actor
     * @param monsters establishes monsters in array
     * @return # of monsters in room
     */
    public static int countNearbyMonsters(Actor actor, ArrayList<Actor> monsters) {

        int count = 0;

        for (Actor monster : monsters) {

            if (actor.inAdjacentRoom(monster)) {

                count++;

            }
        }

        return count;

    }

    /**
     * Moves the hero and handles encounters with monsters.
     * @param hero The hero actor.
     * @param monsters The list of monsters.
     * @param dungeonSize The size of the catacomb.
     * @param direction The direction in which the hero wants to move.
     * @return True if the hero successfully moves, false otherwise.
     */
    public static boolean move(Actor hero, ArrayList<Actor> monsters, int dungeonSize, String direction) {

        if (hero.move(direction, dungeonSize)) {

            for (Actor monster : monsters) {

                if (hero.inSameRoom(monster) && monster.isAlive()) {

                    fight(hero, monster);

                    if (!hero.isAlive()) {

                        return true;

                    }
                }
            }

            hero.health -= 2; // 2 HP loss for moving

            return true;

        }

        return false;

    }

    /**
     * Handles the fight between the hero and a monster.
     * @param hero The hero actor.
     * @param monster The monster actor.
     */
    public static void fight(Actor hero, Actor monster) {

        System.out.println("You encounter " + monster.name + "!");

        while (hero.isAlive() && monster.isAlive()) {

            hero.hit(monster);

            if (! monster.isAlive()) {

                System.out.println("You defeated " + monster.name + "!");

                break;
            }

            monster.hit(hero);

            if (!hero.isAlive()) {

                System.out.println("You were defeated by " + monster.name + "!");

                break;
            }
        }
    }
}
