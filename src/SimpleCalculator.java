
import java.util.Scanner;

public class SimpleCalculator {
    private final Scanner input;
    private final CalculationHistory history;

    public SimpleCalculator(Scanner input, CalculationHistory history) {
        this.input = input;
        this.history = history;
    }

    public void startCalc() {
        ConsoleUI.header("SCIENTIFIC CALCULATOR");

        System.out.println("\n--- BASIC OPERATIONS ---");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulus (%)");

        System.out.println("\n--- POWER AND ROOTS ---");
        System.out.println("6. Power (x^y)");
        System.out.println("7. Square Root");
        System.out.println("8. Square (x^2)");
        System.out.println("9. Cube (x^3)");
        System.out.println("10. Cube Root");

        System.out.println("\n--- TRIGONOMETRY ---");
        System.out.println("11. Sine (sin)");
        System.out.println("12. Cosine (cos)");
        System.out.println("13. Tangent (tan)");

        System.out.println("\n--- LOGARITHMS ---");
        System.out.println("14. Natural Log (ln)");
        System.out.println("15. Log Base 10");

        System.out.println("\n--- CONSTANTS AND OTHER ---");
        System.out.println("16. Pi (π)");
        System.out.println("17. Euler's Number (e)");
        System.out.println("18. Absolute Value");
        System.out.println("19. Factorial (n!)");
        System.out.println("0. Back");

        int op = ConsoleUI.readIntInRange(
                input, "Choose operation: ", 0, 19
        );

        if (op == 0) {
            return;
        }

        double num1;
        double num2;
        double result;
        String calculation;

        switch (op) {
            case 1:
                num1 = readNumber("Enter first number: ");
                num2 = readNumber("Enter second number: ");
                result = num1 + num2;
                calculation = num1 + " + " + num2;
                break;

            case 2:
                num1 = readNumber("Enter first number: ");
                num2 = readNumber("Enter second number: ");
                result = num1 - num2;
                calculation = num1 + " - " + num2;
                break;

            case 3:
                num1 = readNumber("Enter first number: ");
                num2 = readNumber("Enter second number: ");
                result = num1 * num2;
                calculation = num1 + " * " + num2;
                break;

            case 4:
                num1 = readNumber("Enter first number: ");
                num2 = readNumber("Enter second number: ");

                if (num2 == 0) {
                    ConsoleUI.error("Cannot divide by zero.");
                    return;
                }

                result = num1 / num2;
                calculation = num1 + " / " + num2;
                break;

            case 5:
                num1 = readNumber("Enter first number: ");
                num2 = readNumber("Enter second number: ");

                if (num2 == 0) {
                    ConsoleUI.error(
                            "Cannot use zero as the modulus divisor."
                    );
                    return;
                }

                result = num1 % num2;
                calculation = num1 + " % " + num2;
                break;

            case 6:
                num1 = readNumber("Enter base: ");
                num2 = readNumber("Enter exponent: ");

                result = Math.pow(num1, num2);
                calculation = num1 + " ^ " + num2;
                break;

            case 7:
                num1 = readNumber("Enter number: ");

                if (num1 < 0) {
                    ConsoleUI.error(
                            "Square root of a negative number is not real."
                    );
                    return;
                }

                result = Math.sqrt(num1);
                calculation = "sqrt(" + num1 + ")";
                break;

            case 8:
                num1 = readNumber("Enter number: ");

                result = Math.pow(num1, 2);
                calculation = num1 + " ^ 2";
                break;

            case 9:
                num1 = readNumber("Enter number: ");

                result = Math.pow(num1, 3);
                calculation = num1 + " ^ 3";
                break;

            case 10:
                num1 = readNumber("Enter number: ");

                result = Math.cbrt(num1);
                calculation = "cbrt(" + num1 + ")";
                break;

            case 11:
                num1 = readNumber("Enter angle in degrees: ");

                result = Math.sin(Math.toRadians(num1));
                calculation = "sin(" + num1 + " degrees)";
                break;

            case 12:
                num1 = readNumber("Enter angle in degrees: ");

                result = Math.cos(Math.toRadians(num1));
                calculation = "cos(" + num1 + " degrees)";
                break;

            case 13:
                num1 = readNumber("Enter angle in degrees: ");

                if (Math.abs(Math.cos(Math.toRadians(num1))) < 1e-10) {
                    ConsoleUI.error(
                            "Tangent is undefined for this angle."
                    );
                    return;
                }

                result = Math.tan(Math.toRadians(num1));
                calculation = "tan(" + num1 + " degrees)";
                break;

            case 14:
                num1 = readNumber("Enter number: ");

                if (num1 <= 0) {
                    ConsoleUI.error(
                            "Natural logarithm requires a number greater than zero."
                    );
                    return;
                }

                result = Math.log(num1);
                calculation = "ln(" + num1 + ")";
                break;

            case 15:
                num1 = readNumber("Enter number: ");

                if (num1 <= 0) {
                    ConsoleUI.error(
                            "Logarithm requires a number greater than zero."
                    );
                    return;
                }

                result = Math.log10(num1);
                calculation = "log10(" + num1 + ")";
                break;

            case 16:
                result = Math.PI;
                calculation = "pi";
                break;

            case 17:
                result = Math.E;
                calculation = "e";
                break;

            case 18:
                num1 = readNumber("Enter number: ");

                result = Math.abs(num1);
                calculation = "|" + num1 + "|";
                break;

            case 19:
                num1 = readNumber("Enter a non-negative integer: ");

                if (num1 < 0 || num1 != Math.floor(num1)) {
                    ConsoleUI.error(
                            "Factorial requires a non-negative integer."
                    );
                    return;
                }

                if (num1 > 170) {
                    ConsoleUI.error(
                            "Number is too large. Maximum supported factorial is 170!."
                    );
                    return;
                }

                result = 1;

                for (int i = 2; i <= (int) num1; i++) {
                    result *= i;
                }

                calculation = (int) num1 + "!";
                break;

            default:
                return;
        }

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            ConsoleUI.error(
                    "The result is outside the supported numeric range."
            );
            return;
        }

        System.out.printf("\nCalculation: %s%n", calculation);
        System.out.printf("Result: %.4f%n", result);

        ConsoleUI.success("Calculation completed successfully.");

        history.add(
                String.format("%s = %.4f", calculation, result)
        );

        ConsoleUI.pause(input);
    }

    private double readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                input.nextLine();

                if (Double.isFinite(value)) {
                    return value;
                }

                ConsoleUI.error(
                        "Please enter a finite number."
                );
            } else {
                ConsoleUI.error("Please enter a valid number.");
                input.nextLine();
            }
        }
    }
}
