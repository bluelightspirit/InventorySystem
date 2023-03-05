
import java.util.Scanner;

// Purpose: Give user options to add, drop, equip, or print certain items to/from their inventory
public class Main {

    private static boolean playing = true;
    private static int userMainOptionChoice = 0;

    // calls other methods so user can access actions done with their Inventory
    public static void main(String[] args) {
        Inventory stuff = new Inventory(25000);
        welcome();
        while (playing) {
            printMainOptions();
            scanMainOptions(stuff);
        }
    }

    // print welcome message slightly delayed
    public static void welcome() {
        String welcome = "Welcome to";
        for (char character : welcome.toCharArray()) {
            System.out.print(character);
            ms3Sleep();
        }
        for (int i = 1; i <= 3; i++) {
            System.out.print('.');
            ms10Sleep();
        }

        System.out.println();

        String gameName = "The Inventory System!";
        for (char character : gameName.toCharArray()) {
            System.out.print(character);
            ms5Sleep();
        }

        System.out.println();
    }

    // sleep for 3ms
    public static void ms3Sleep() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException f) {
            throw new RuntimeException("Uncaught", f);
        }
    }

    // sleep for 5ms
    public static void ms5Sleep() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException f) {
            throw new RuntimeException("Uncaught", f);
        }
    }

    // sleep for 10ms
    public static void ms10Sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException f) {
            throw new RuntimeException("Uncaught", f);
        }
    }

    // print to user main options they can do with their inventory
    public static void printMainOptions() {
        for (int i = 1; i <= 146; i++) {
            System.out.print("-");
        }
        System.out.println("\n1. Show inventory");
        System.out.println("2. Add random item");
        System.out.println("3. Drop item");
        System.out.println("4. Equip weapon");
        System.out.println("5. Equip armor");
        System.out.println("6. Exit");
        System.out.print("» ");
    }

    // scan for user option that matches any of the printMainOptions() options
    public static void scanMainOptions(Inventory stuff) {
        userMainOptionChoice = 0;
        Scanner userMainActionScanner = new Scanner(System.in);
        while (!(userMainOptionChoice >= 1 && userMainOptionChoice <= 6)) {
            String userAction = userMainActionScanner.nextLine();
            try {
                userMainOptionChoice = Integer.parseInt(userAction);
                // call performMainOptionChoice to check if user input is an integer before ending try-catch
                performMainOptionChoice(stuff);
            } catch (NumberFormatException e) {
                System.err.println("Error: User input is not a number between 1-6! (Non-integer detected) \nTry inputting again!");
                // sleep so print can be after error message properly
                stuff.sleep();
                printMainOptions();
            }
        }
    }

    // act upon the user option given by the user by calling the Inventory object methods, printing an error for user input that isn't 1-6, or setting the playing loop to false
    public static void performMainOptionChoice(Inventory stuff) {
        switch (userMainOptionChoice) {
            case 1:
                stuff.print();
                break;
            case 2:
                Item item = ItemGenerator.generate();
                stuff.add(item);
                break;
            case 3:
                stuff.drop();
                break;
            case 4:
                stuff.equipWeapon();
                break;
            case 5:
                stuff.equipArmor();
                break;
            case 6:
                playing = false;
                break;
            default:
                System.err.println("Error: User input is not a number between 1-6 (Integer detected, but is not within 1-6)! \nTry inputting again!");
                break;
        }
    }
}