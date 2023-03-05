public enum ItemMiscAttributes {
    Old(21, 1, 0.75, 1),
    Burnt(22, 1, 0.60, 1),
    Wet(23, 2, 1, 1),
    New(24, 3, 3, 2),
    Rusty(25, 2, 0.90, 1),
    Starred(26, 4, 2.75, 2),
    Strange(27, 2, 1.50, 1),
    Scratched(28, 2, 0.65, 1),
    Synthetic(29, 2, 0.40, 1),
    Unionized(30, 1, 3.50, 3),
    Broken(31, 1, 0.10, 1);
    private final int itemId;
    private final int itemWeight;
    private final double minItemValue;
    private final int minItemStrength;

    ItemMiscAttributes(int itemId, int itemWeight, double minItemValue, int minItemStrength) {
        this.itemId = itemId;
        this.itemWeight = itemWeight;
        this.minItemValue = minItemValue;
        this.minItemStrength = minItemStrength;
    }

    public static int itemIdRandomizer() {
        ItemMiscAttributes[] allItemAttributes = ItemMiscAttributes.values();
        // 0 to length of attributes
        // ordinal() also possible rather than itemId, but used itemId for more clarity when reading code
        return (int) (Math.random() * allItemAttributes.length);
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemWeight() {
        return itemWeight;
    }

    public double getMinItemValue() {
        return minItemValue;
    }

    public int getMinItemStrength() {
        return minItemStrength;
    }

    public String getItemName() {
        return "";
    }
}
