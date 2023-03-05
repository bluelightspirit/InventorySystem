// Purpose: set types of items an item can be & randomly choose one
public enum ItemType {
        WEAPON, ARMOR, MISC;

        // randomly choose a number from 0 to the length of this enum - 1
        // meaning it currently randomly chooses a number from 0 to 2 currently & returns the number as an int
        public static int itemIdRandomizer() {
                ItemType[] allItemTypes = ItemType.values();
                // 0 to length of itemTypes
                return (int) (Math.random() * allItemTypes.length);
        }
}
