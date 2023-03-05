import java.util.ArrayList;
import java.util.Scanner;

// Purpose: set an items ArrayList based on an Inventory object set from Main.java that takes in multiple Item objects & sets a maximum weight
// then have methods to add, remove, equip, and print the Item objects within that items ArrayList
public class Inventory {
    private final ArrayList<Item> items = new ArrayList<>();
    private final int maxWeight;
    private Item equippedWeapon = null;
    private Item equippedArmor = null;

    // sets parameter from Inventory Object
    Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    // sleeps for 50ms to fix error messages being printed during Main.java's print of user options
    public void sleep() {
        // sleep so print can be after error message properly
        try {
            Thread.sleep(50);
        } catch (InterruptedException f) {
            throw new RuntimeException("Uncaught", f);
        }
    }

    // adds item to inventory
    public boolean add(Item item) {
        if (totalWeight() + item.getItemWeight() <= maxWeight) {
            items.add(item);
            System.out.println(items.get(items.size()-1).getItemName() + " was added to your inventory.\n");
            return true;
        } else {
            System.err.printf("Error: Could not add %s to inventory!%nCurrent Inventory Weight: %,d lb%n", item.getItemName(), totalWeight());
            System.err.printf("Maximum Inventory Weight: %,d lb%n", maxWeight);
            System.err.printf("Failed to add %,d lb%n", item.getItemWeight());
            // sleep so print can be after error message properly
            sleep();
            return false;
        }
    }

    // gets & returns total weight of all items within items ArrayList
    public int totalWeight() {
        int totalWeight = 0;
        for (Item item : items) {
            totalWeight += item.getItemWeight();
        }
        return totalWeight;
    }

    // gets & returns total value of all items within items ArrayList
    public double totalValue() {
        double totalValue = 0;
        for (Item item : items) {
            totalValue += item.getItemValue();
        }
        return totalValue;
    }

    // gets & returns total strength of all items within items ArrayList
    public int totalStrength() {
        int totalStrength = 0;
        for (Item item : items) {
            totalStrength += item.getItemStrength();
        }
        return totalStrength;
    }

    // search if item is equipped or not
    public boolean searchItemEquipped(Item item) {
        // return false if not & return true if so
        return equippedWeapon == item || equippedArmor == item;
    }

    // print inventory from items ArrayList
    public void print() {
        // tells user inventory is empty (technically not an error if inventory has 0 items for this specific method)
        if (items.size() == 0) {
            System.out.println("Inventory is empty!");

            // if inventory has > 1 item, print all items & if the item is equipped or not
        } else {
            System.out.printf("%-5s%-75s | %-11s | %-20s | %-27s%n", "#", "Item (Type)", "Weight (lb)", "Value ($)", "Strength (J/ms in SkyWars)");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                String itemName = item.getItemName() + " (" + item.getItemType() + ")";
                System.out.printf("%-5s%-75s | %,-11d | %,-20.2f | %,-27d", i + 1 + ".", itemName, item.getItemWeight(), item.getItemValue(), item.getItemStrength());
                if (searchItemEquipped(item)) {
                    System.out.printf("(equipped %s)%n", item.getItemType());
                } else {
                    System.out.println();
                }
            }

            // prints total
            System.out.printf("%-5s%-75s | %,-11d | %,-20.2f | %,-27d%n", "T.", "Total (ALL)", totalWeight(), totalValue(), totalStrength());
            System.out.println();
        }
    }

    // drop an item from items ArrayList
    public void drop() {
        // check if items ArrayList has no items 
        if (items.size() == 0) {
            System.err.println("Error: No items in inventory!");
            return;
        }

        // print item options the user can drop
        System.out.printf("%-5s%-75s | %-11s | %-20s | %-27s%n", "#", "Item (Type)", "Weight (lb)", "Value ($)", "Strength (J/ms in SkyWars)");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String itemName = item.getItemName() + " (" + item.getItemType() + ")";
            System.out.printf("%-5s%-75s | %,-11d | %,-20.2f | %,-27d", i+1 + ".", itemName, item.getItemWeight(), item.getItemValue(), item.getItemStrength());
            if (searchItemEquipped(item)) {
                System.out.printf("(equipped %s)%n", item.getItemType());
            } else {
                System.out.println();
            }
        }
        // print cancel option
        System.out.printf("%-5s%-75s%n» ", items.size()+1 + ".", "Cancel");

        // scan
        int userOptionChoice = 0;
        Scanner userActionScanner = new Scanner(System.in);
        while (!(userOptionChoice >= 1 && userOptionChoice <= items.size()+1)) {
            String userAction = userActionScanner.nextLine();
            try {
                userOptionChoice = Integer.parseInt(userAction);
                if (!(userOptionChoice >= 1 && userOptionChoice <= items.size()+1)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.printf("Error: User input is not a number between 1-%d! Try inputting again!%n", items.size()+1);
                // sleep so print can be after error message properly
                sleep();
            }
        }

        // cancel action or remove item from items ArrayList
        if (userOptionChoice == items.size()+1) {
            System.out.println("Cancelling action...");
        } else {
            System.out.printf("Removing %s from inventory...%n", items.get(userOptionChoice - 1).getItemName());
            // fix for dropping equipped item
            // set equippedWeapon & equippedArmor to null if nothing is equipped
            if (searchItemEquipped(items.get(userOptionChoice - 1))) {
                if (items.get(userOptionChoice - 1).getItemType() == ItemType.WEAPON) {
                    equippedWeapon = null;
                } else {
                    equippedArmor = null;
                }
            }
            items.remove(userOptionChoice - 1);
            System.out.println("Success!");
        }
    }

    // equip weapon based on searching items ArrayList & setting that item to equipped
    public void equipWeapon() {
        // check if items ArrayList has any weapons
        int weaponCount = 0;
        ArrayList<Integer> realWeaponIds = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getItemType() == ItemType.WEAPON) {
                weaponCount++;
                realWeaponIds.add(i);
            }
        }

        // print error & escape the method from continuing if items ArrayList has no weapons
        if (weaponCount == 0) {
            System.err.println("Error: No weapons in inventory!");
            // sleep so print can be after error message properly
            sleep();
            return;
        }

        // print weapon options for user to equip
        System.out.printf("%-5s%-75s | %-11s | %-20s | %-27s%n", "#", "Item (Type)", "Weight (lb)", "Value ($)", "Strength (J/ms in SkyWars)");
        for (int i = 0; i < weaponCount; i++) {
            Item item = items.get(realWeaponIds.get(i));
            String itemName;
            itemName = item.getItemName() + " (" + item.getItemType() + ")";
            System.out.printf("%-5s%-75s | %,-11d | %,-20.2f | %,-27d", i+1 + ".", itemName, item.getItemWeight(), item.getItemValue(), item.getItemStrength());
            if (searchItemEquipped(item)) {
                System.out.printf("(equipped %s)%n", item.getItemType());
            } else {
                System.out.println();
            }
        }
        // print option to cancel
        System.out.printf("%-5s%-75s%n» ", weaponCount+1 + ".", "Cancel");

        // scan
        int userOptionChoice = 0;
        Scanner userActionScanner = new Scanner(System.in);
        while (!(userOptionChoice >= 1 && userOptionChoice <= weaponCount+1)) {
            String userAction = userActionScanner.nextLine();
            try {
                userOptionChoice = Integer.parseInt(userAction);
                if (!(userOptionChoice >= 1 && userOptionChoice <= weaponCount+1)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.printf("Error: User input is not a number between 1-%d! Try inputting again!%n", weaponCount+1);
                // sleep so print can be after error message properly
                sleep();
            }
        }

        // cancel action or equip weapon from items ArrayList
        if (userOptionChoice == weaponCount+1) {
            System.out.println("Cancelling action...");
        } else {
            System.out.printf("Equipping %s...%n", items.get(realWeaponIds.get(userOptionChoice-1)).getItemName());
            equippedWeapon = items.get(realWeaponIds.get(userOptionChoice-1));
            System.out.println("Success!");
        }
    }

    // equip armor based on searching items ArrayList & setting that item to equipped
    public void equipArmor() {
        // check if items ArrayList has any armors
        int armorCount = 0;
        ArrayList<Integer> realArmorIds = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getItemType() == ItemType.ARMOR) {
                armorCount++;
                realArmorIds.add(i);
            }
        }

        // print error & escape the method from continuing if items ArrayList has no weapons
        if (armorCount == 0) {
            System.err.println("Error: No armored items in inventory!");
            // sleep so print can be after error message properly
            sleep();
            return;
        }

        // print armor options for the user to equip
        System.out.printf("%-5s%-75s | %-11s | %-20s | %-27s%n", "#", "Item (Type)", "Weight (lb)", "Value ($)", "Strength (J/ms in SkyWars)");
        for (int i = 0; i < armorCount; i++) {
            Item item = items.get(realArmorIds.get(i));
            String itemName;
            itemName = item.getItemName() + " (" + item.getItemType() + ")";
            System.out.printf("%-5s%-75s | %,-11d | %,-20.2f | %,-27d", i+1 + ".", itemName, item.getItemWeight(), item.getItemValue(), item.getItemStrength());
            if (searchItemEquipped(item)) {
                System.out.printf("(equipped %s)%n", item.getItemType());
            } else {
                System.out.println();
            }
        }
        // print option to cancel
        System.out.printf("%-5s%-75s%n» ", armorCount+1 + ".", "Cancel");

        // scan
        int userOptionChoice = 0;
        Scanner userActionScanner = new Scanner(System.in);
        while (!(userOptionChoice >= 1 && userOptionChoice <= armorCount+1)) {
            String userAction = userActionScanner.nextLine();
            try {
                userOptionChoice = Integer.parseInt(userAction);
                if (!(userOptionChoice >= 1 && userOptionChoice <= armorCount+1)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.printf("Error: User input is not a number between 1-%d! Try inputting again!%n", armorCount+1);
                // sleep so print can be after error message properly
                sleep();
            }
        }

        // cancel action or equip armor from items ArrayList
        if (userOptionChoice == armorCount+1) {
            System.out.println("Cancelling action...");
        } else {
            System.out.printf("Equipping %s...%n", items.get(realArmorIds.get(userOptionChoice-1)).getItemName());
            equippedArmor = items.get(realArmorIds.get(userOptionChoice-1));
            System.out.println("Success!");
        }
    }
}
