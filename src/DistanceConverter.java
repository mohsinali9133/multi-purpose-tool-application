import java.util.Scanner;

public class DistanceConverter {
    private final Scanner input;
    private final CalculationHistory history;

    public DistanceConverter(Scanner input, CalculationHistory history) {
        this.input = input;
        this.history = history;
    }

    public void startConversion() {
        ConsoleUI.header("DISTANCE CONVERTER");
        String[] units = {"Kilometers (km)", "Miles (mi)", "Meters (m)", "Feet (ft)"};
        printUnits(units);
        int fromUnit = ConsoleUI.readIntInRange(input, "Convert from: ", 1, 4);
        double distance = ConsoleUI.readNonNegativeDouble(input, "Enter distance: ");

        ConsoleUI.line();
        printUnits(units);
        int toUnit = ConsoleUI.readIntInRange(input, "Convert to: ", 1, 4);

        double meters = toMeters(distance, fromUnit);
        double result = fromMeters(meters, toUnit);

        System.out.printf("\nResult: %.4f %s%n", result, unitShort(toUnit));
        ConsoleUI.success("Conversion completed successfully.");
        history.add(String.format("%.2f %s = %.4f %s", distance, unitShort(fromUnit), result, unitShort(toUnit)));
        ConsoleUI.pause(input);
    }

    private void printUnits(String[] units) {
        for (int i = 0; i < units.length; i++) System.out.println((i + 1) + ". " + units[i]);
    }

    private double toMeters(double value, int unit) {
        switch (unit) {
            case 1: return value * 1000;
            case 2: return value * 1609.34;
            case 3: return value;
            case 4: return value * 0.3048;
            default: return 0;
        }
    }

    private double fromMeters(double meters, int unit) {
        switch (unit) {
            case 1: return meters / 1000;
            case 2: return meters / 1609.34;
            case 3: return meters;
            case 4: return meters / 0.3048;
            default: return 0;
        }
    }

    private String unitShort(int unit) {
        switch (unit) {
            case 1: return "km";
            case 2: return "mi";
            case 3: return "m";
            case 4: return "ft";
            default: return "";
        }
    }
}
