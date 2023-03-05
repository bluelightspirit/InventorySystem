
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Purpose: generate a new item with a random item type, attribute, weight, value, strength, and name depending on the random item type
public class ItemGenerator {

    private ItemType itemType;
    private ItemAttribute attribute;
    private final int randomizedItemTypeId = generateItemType();
    private String armorWeaponMiscName = "";
    private int itemWeight = 0;
    private double minItemValue = 0;
    private int minItemStrength = 0;
    
    // generate information & return an Item object
    public static Item generate() {
        ItemGenerator item = new ItemGenerator();
        // generate an attribute
        item.generateAttribute();
        // weapon
        if (item.randomizedItemTypeId == 0) {
            item.generateWeaponType();
        // armor
        } else if (item.randomizedItemTypeId == 1) {
            item.generateArmorType();
        // misc
        } else {
            item.generateMiscType();
        }
        // weight: 1 to 4
        int itemWeightModifier = (int) (Math.random() * 4 + 1);
        item.itemWeight = item.itemWeight * itemWeightModifier;

        // value: 1 to 3.75
        double itemValueModifier = Math.random() * 2.75 + 1;
        double itemValue = item.minItemValue * itemValueModifier;

        // strength: 1 to 10
        int itemStrengthModifier = (int) (Math.random() * 10 + 1);
        int itemStrength = item.minItemStrength * itemStrengthModifier;

        // combine item randomized value & weight
        itemValue = item.minItemValue * item.itemWeight;

        return new Item(item.itemType, item.attribute, item.armorWeaponMiscName, item.itemWeight, itemValue, itemStrength);
    }

    // randomize weapon type from WeaponTypes.txt
    public void generateWeaponType() {
        ArrayList<String> weaponTypes = new ArrayList<>();
        try {
            // input file being searched
            Scanner fIn = new Scanner(new FileInputStream("WeaponTypes.txt"));
            while (fIn.hasNext()) {
                weaponTypes.add(fIn.nextLine());
            }
            fIn.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: WeaponTypes.txt not found!");
        }
        int randomWeaponId = (int) (Math.random() * weaponTypes.size());
        armorWeaponMiscName = weaponTypes.get(randomWeaponId);
    }

    // randomize armor type from ArmorTypes.txt
    public void generateArmorType() {
        ArrayList<String> armorTypes = new ArrayList<>();
        try {
            // input file being searched
            Scanner fIn = new Scanner(new FileInputStream("ArmorTypes.txt"));
            while (fIn.hasNext()) {
                armorTypes.add(fIn.nextLine());
            }
            fIn.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: ArmorTypes.txt not found!");
        }
        int randomArmorId = (int) (Math.random() * armorTypes.size());
        armorWeaponMiscName = armorTypes.get(randomArmorId);
    }

    // randomize misc type from MiscellaneousTypes.txt
    public void generateMiscType() {
        ArrayList<String> MiscellaneousTypes = new ArrayList<>();
        try {
            // input file being searched
            Scanner fIn = new Scanner(new FileInputStream("MiscellaneousTypes.txt"));
            while (fIn.hasNext()) {
                MiscellaneousTypes.add(fIn.nextLine());
            }
            fIn.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: MiscellaneousTypes.txt not found!");
        }
        int randomMiscId = (int) (Math.random() * MiscellaneousTypes.size());
        armorWeaponMiscName = MiscellaneousTypes.get(randomMiscId);
    }

    // randomize item type
    public int generateItemType() {
        int randomId = ItemType.itemIdRandomizer();
        for (ItemType types : ItemType.values()) {
            if (types.ordinal() == randomId) {
                itemType = types;
                return randomId;
            }
        }
        return randomId;
    }

    // randomize item attribute & obtain its attributes
    public void generateAttribute() {
        int randomId;
        if (randomizedItemTypeId == 2) {
            randomId = ItemAttribute.miscItemIdRandomizer();
        } else {
            randomId = ItemAttribute.itemIdRandomizer();
        }
        for (ItemAttribute attributes : ItemAttribute.values()) {
            if (attributes.ordinal() == randomId) {
                attribute = attributes;
                itemWeight = attributes.getItemWeight();
                minItemValue = attributes.getMinItemValue();
                minItemStrength = attributes.getMinItemStrength();
            }
        }
    }

    // test method to see if calling generate() works
    public static void main(String[] args) {
        Item item = generate();
        System.out.println("" + item.getItemType() + " " + item.getItemStrength() + " J/ms, " + item.getItemName() + ", " + item.getItemWeight() + " lb, $" + item.getItemValue());
    }
}
