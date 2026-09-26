import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculationHistory history = new CalculationHistory();

        FuelCalculator fuelTool = new FuelCalculator(sc, history);
        AgeCalculator ageTool = new AgeCalculator(sc, history);
        CurrencyConverter currencyTool = new CurrencyConverter(sc, history);
        DistanceConverter distanceTool = new DistanceConverter(sc, history);
        SimpleCalculator basicCalc = new SimpleCalculator(sc, history);

        showWelcome();

        boolean running = true;
        while (running) {
            ConsoleUI.header("MULTIPURPOSE TOOL APPLICATION");
            System.out.println("  1. Fuel Commute Estimator");
            System.out.println("  2. Age Calculator");
            System.out.println("  3. International Currency Converter");
            System.out.println("  4. Distance Unit Converter");
            System.out.println("  5. Basic Calculator");
            System.out.println("  6. Calculation History");
            System.out.println("  7. About Project");
            System.out.println("  0. Exit");
            ConsoleUI.line();

            int choice = ConsoleUI.readIntInRange(sc, "Enter your choice: ", 0, 7);

            switch (choice) {
                case 1: fuelTool.commuteCalculator(); break;
                case 2: ageTool.calculateAge(); break;
                case 3: currencyTool.startConversion(); break;
                case 4: distanceTool.startConversion(); break;
                case 5: basicCalc.startCalc(); break;
                case 6:
                    history.show();
                    ConsoleUI.pause(sc);
                    break;
                case 7:
                    showAbout();
                    ConsoleUI.pause(sc);
                    break;
                case 0:
                    showGoodbye();
                    running = false;
                    break;
            }
        }
        sc.close();
    }

    private static void showWelcome() {
        System.out.println(ConsoleUI.CYAN + "\n+--------------------------------------------------+");
        System.out.println("|                                                  |");
        System.out.println("|          MULTIPURPOSE TOOL APPLICATION          |");
        System.out.println("|                Java Edition                     |");
        System.out.println("|                                                  |");
        System.out.println("|       Object Oriented Programming SE(353)       |");
        System.out.println("|            University of Karachi - UBIT        |");
        System.out.println("|                                                  |");
        System.out.println("+--------------------------------------------------+" + ConsoleUI.RESET);
    }

    private static void showAbout() {
        ConsoleUI.header("ABOUT PROJECT");
        System.out.println("Multipurpose Tool Application");
        System.out.println("Java console-based utility application");
        ConsoleUI.line();
        System.out.println("Course    : Object Oriented Programming SE(353)");
        System.out.println("Instructor: Ms. Ilsa Naeem");
        System.out.println("University: University of Karachi - UBIT");
        ConsoleUI.line();
        System.out.println("Group Members:");
        System.out.println("  Mohsin Ali  - 23 (Group Leader)");
        System.out.println("  Jawad Ahmed - 21");
        System.out.println("  Ali Subhan  - 6");
        System.out.println("  M. Zaid     - 54");
    }

    private static void showGoodbye() {
        System.out.println(ConsoleUI.GREEN + "\n+--------------------------------------------------+");
        System.out.println("|       Thank you for using the application!      |");
        System.out.println("|                    Goodbye!                      |");
        System.out.println("+--------------------------------------------------+" + ConsoleUI.RESET);
    }
}
