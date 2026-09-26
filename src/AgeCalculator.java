import java.util.Scanner;

public class AgeCalculator {
    private final Scanner input;
    private final CalculationHistory history;

    public AgeCalculator(Scanner input, CalculationHistory history) {
        this.input = input;
        this.history = history;
    }

    public void calculateAge() {
        ConsoleUI.header("AGE CALCULATOR");
        System.out.println("Enter today's/current date:");
        int currentYear = ConsoleUI.readIntInRange(input, "Year (YYYY): ", 1, 9999);
        int currentMonth = ConsoleUI.readIntInRange(input, "Month (1-12): ", 1, 12);
        int currentDay = ConsoleUI.readIntInRange(input, "Day (1-31): ", 1, 31);

        System.out.println("\nEnter date of birth:");
        int birthYear = ConsoleUI.readIntInRange(input, "Year (YYYY): ", 1, 9999);
        int birthMonth = ConsoleUI.readIntInRange(input, "Month (1-12): ", 1, 12);
        int birthDay = ConsoleUI.readIntInRange(input, "Day (1-31): ", 1, 31);

        if (birthYear > currentYear ||
            (birthYear == currentYear && birthMonth > currentMonth) ||
            (birthYear == currentYear && birthMonth == currentMonth && birthDay > currentDay)) {
            ConsoleUI.error("Birth date cannot be later than the current date.");
            ConsoleUI.pause(input);
            return;
        }

        int age = currentYear - birthYear;
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) age--;

        ConsoleUI.line();
        System.out.println("Current Date : " + currentDay + "/" + currentMonth + "/" + currentYear);
        System.out.println("Birth Date   : " + birthDay + "/" + birthMonth + "/" + birthYear);
        System.out.println("Your Age     : " + age + " years");
        ConsoleUI.success("Age calculated successfully.");
        history.add("Age calculated: " + age + " years");
        ConsoleUI.pause(input);
    }
}
