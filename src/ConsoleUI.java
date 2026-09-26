import java.util.Scanner;

public class ConsoleUI {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    private ConsoleUI() { }

    public static void header(String title) {
        System.out.println("\n" + CYAN + "+--------------------------------------------------+" + RESET);
        System.out.printf(CYAN + "| %-48s |%n" + RESET, title);
        System.out.println(CYAN + "+--------------------------------------------------+" + RESET);
    }

    public static void line() {
        System.out.println(CYAN + "----------------------------------------------------" + RESET);
    }

    public static void success(String message) {
        System.out.println(GREEN + "[✓] " + message + RESET);
    }

    public static void error(String message) {
        System.out.println(RED + "[!] " + message + RESET);
    }

    public static void info(String message) {
        System.out.println(YELLOW + "[i] " + message + RESET);
    }

    public static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                input.nextLine();
                return value;
            }
            error("Please enter a valid whole number.");
            input.nextLine();
        }
    }

    public static int readIntInRange(Scanner input, String prompt, int min, int max) {
        while (true) {
            int value = readInt(input, prompt);
            if (value >= min && value <= max) return value;
            error("Please enter a value from " + min + " to " + max + ".");
        }
    }

    public static double readPositiveDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                input.nextLine();
                if (value > 0) return value;
            } else {
                input.nextLine();
            }
            error("Please enter a number greater than zero.");
        }
    }

    public static double readNonNegativeDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                input.nextLine();
                if (value >= 0) return value;
            } else {
                input.nextLine();
            }
            error("Please enter a number that is zero or greater.");
        }
    }

    public static void pause(Scanner input) {
        System.out.print("\nPress Enter to return to the main menu...");
        input.nextLine();
    }
}
