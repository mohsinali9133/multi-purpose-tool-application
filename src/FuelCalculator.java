import java.util.Scanner;

public class FuelCalculator {
    private final Scanner input;
    private final CalculationHistory history;

    public FuelCalculator(Scanner input, CalculationHistory history) {
        this.input = input;
        this.history = history;
    }

    public void commuteCalculator() {
        ConsoleUI.header("FUEL COMMUTE ESTIMATOR");
        System.out.println("1. Petrol");
        System.out.println("2. Diesel");
        int fuelChoice = ConsoleUI.readIntInRange(input, "Choose fuel type: ", 1, 2);
        String fuelType = fuelChoice == 1 ? "Petrol" : "Diesel";
        double fuelPrice = ConsoleUI.readPositiveDouble(input, "Enter current " + fuelType + " price per liter: Rs. ");

        int vehicleChoice;
        if (fuelChoice == 1) {
            System.out.println("\n1. Bike");
            System.out.println("2. Car");
            vehicleChoice = ConsoleUI.readIntInRange(input, "Choose vehicle: ", 1, 2);
        } else {
            ConsoleUI.info("Diesel selected: using Car as the vehicle type.");
            vehicleChoice = 2;
        }

        int cc;
        double fuelAverage;
        if (vehicleChoice == 1) {
            System.out.println("\n1. 70cc   2. 100cc   3. 110cc   4. 150cc   5. 200cc");
            int bikeChoice = ConsoleUI.readIntInRange(input, "Choose engine size: ", 1, 5);
            int[] ccValues = {70, 100, 110, 150, 200};
            double[] averages = {50.0, 45.0, 42.0, 35.0, 30.0};
            cc = ccValues[bikeChoice - 1];
            fuelAverage = averages[bikeChoice - 1];
        } else {
            cc = ConsoleUI.readIntInRange(input, "Enter car engine CC: ", 1, 10000);
            if (cc <= 1000) fuelAverage = 15.0;
            else if (cc <= 1300) fuelAverage = 12.0;
            else fuelAverage = 9.0;
        }

        System.out.println("\nEstimated average for " + cc + "cc: " + fuelAverage + " km/L");
        System.out.println("1. Use estimated average");
        System.out.println("2. Enter my own average");
        int averageChoice = ConsoleUI.readIntInRange(input, "Choose: ", 1, 2);
        if (averageChoice == 2) fuelAverage = ConsoleUI.readPositiveDouble(input, "Enter custom average (km/L): ");

        double distance = ConsoleUI.readPositiveDouble(input, "Enter destination distance (km): ");
        double litersNeeded = distance / fuelAverage;
        double totalCost = litersNeeded * fuelPrice;
        String vehicleName = vehicleChoice == 1 ? "Bike" : "Car";

        ConsoleUI.line();
        System.out.println("Vehicle      : " + vehicleName + " (" + cc + "cc)");
        System.out.println("Fuel Type    : " + fuelType);
        System.out.printf("Fuel Average : %.2f km/L%n", fuelAverage);
        System.out.printf("Distance     : %.2f km%n", distance);
        System.out.printf("Fuel Needed  : %.2f liters%n", litersNeeded);
        System.out.printf("TOTAL COST   : Rs. %.2f%n", totalCost);
        ConsoleUI.success("Commute estimate completed successfully.");
        history.add(String.format("Fuel estimate: %.2f km, Rs. %.2f", distance, totalCost));
        ConsoleUI.pause(input);
    }
}
