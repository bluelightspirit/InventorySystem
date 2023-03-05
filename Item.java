// Purpose: set the parameters from the Item object generated from ItemGenerator.java so that get() methods can be called from that Item object
public class Item {

    private final ItemType itemType;
    private final ItemAttribute itemAttribute;
    private final String itemName;
    private final int itemWeight;
    private final double itemValue;
    private final int itemStrength;

    // sets parameters from Item object
    public Item(ItemType itemType, ItemAttribute attributeInput, String itemName, int itemWeight, double itemValue, int itemStrength) {
        this.itemType = itemType;
        this.itemAttribute = attributeInput;
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.itemStrength = itemStrength;
    }

    // gets item type & returns it
    public ItemType getItemType() {
        return itemType;
    }

    // gets item attribute & the item name & returns it
    public String getItemName() {
        return itemAttribute + " " + itemName;
    }

    // gets item weight & returns it
    public int getItemWeight() {
        return itemWeight;
    }

    // gets item value & returns it
    public double getItemValue() {
        return itemValue;
    }

    // gets item strength & returns it
    public int getItemStrength() {
        return itemStrength;
    }

    // test method to check if itemGenerator functions, checking if it returns an Item object with the correct parameters
    public static void main(String[] args) {
        Item item = ItemGenerator.generate();
        System.out.println("" + item.getItemType() + " " + item.getItemStrength() + " J/ms, " + item.getItemName() + ", " + item.getItemWeight() + " lb, $" + item.getItemValue());
    }
}
