import java.util.Scanner;

public class SimpleCalculator {
    private final Scanner input;
    private final CalculationHistory history;

    public SimpleCalculator(Scanner input, CalculationHistory history) {
        this.input = input;
        this.history = history;
    }

    public void startCalc() {
        ConsoleUI.header("BASIC CALCULATOR");
        double num1 = readNumber("Enter first number: ");
        double num2 = readNumber("Enter second number: ");

        System.out.println("\n1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulus (%)");
        System.out.println("0. Back");

        int op = ConsoleUI.readIntInRange(input, "Choose operation: ", 0, 5);
        if (op == 0) return;

        double result;
        String symbol;
        switch (op) {
            case 1: result = num1 + num2; symbol = "+"; break;
            case 2: result = num1 - num2; symbol = "-"; break;
            case 3: result = num1 * num2; symbol = "*"; break;
            case 4:
                if (num2 == 0) { ConsoleUI.error("Cannot divide by zero."); return; }
                result = num1 / num2; symbol = "/"; break;
            case 5:
                if (num2 == 0) { ConsoleUI.error("Cannot use zero as the modulus divisor."); return; }
                result = num1 % num2; symbol = "%"; break;
            default: return;
        }

        System.out.printf("\nResult: %.4f %n", result);
        ConsoleUI.success("Calculation completed successfully.");
        history.add(String.format("%.2f %s %.2f = %.2f", num1, symbol, num2, result));
        ConsoleUI.pause(input);
    }

    private double readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                input.nextLine();
                return value;
            }
            ConsoleUI.error("Please enter a valid number.");
            input.nextLine();
        }
    }
}
