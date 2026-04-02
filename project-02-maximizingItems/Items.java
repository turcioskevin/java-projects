/**
 * Represents an item with a name, weight, and value.
 * This class can be used to manage the inclusion of items
 * in various contexts, such as inventory or selection problems.
 */
public class Item {
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    /**
     * Constructs an Item with the specified name, weight, and value.
     *
     * @param name the name of the item
     * @param weight the weight of the item in pounds
     * @param value the value of the item in dollars
     */
    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.included = false;
    }

    /**
     * Constructs a new Item as a copy of the specified Item.
     *
     * @param other the Item to copy
     */
    public Item(Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    /**
     * Returns the weight of the item.
     *
     * @return the weight of the item in pounds
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the value of the item.
     *
     * @return the value of the item in dollars
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if the item is included in a selection.
     *
     * @return true if the item is included, false otherwise
     */
    public boolean isIncluded() {
        return included;
    }

    /**
     * Sets the inclusion status of the item.
     *
     * @param included true to include the item, false to exclude it
     */
    public void setIncluded(boolean included) {
        this.included = included;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return a string in the format "name (weight lbs, $value)"
     */
    @Override
    public String toString() {
        return name + " (" + weight + " lbs, $" + value + ")";
    }
}
