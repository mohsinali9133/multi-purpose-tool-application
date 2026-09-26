import java.util.Scanner;

public class CurrencyConverter {
    private final Scanner input;
    private final CalculationHistory history;

    // Configured reference rates. These are not live market rates.
    private final double usdRate = 280.0;
    private final double euroRate = 305.0;
    private final double yuanRate = 39.0;
    private final double dirhamRate = 76.0;

    public CurrencyConverter(Scanner input, CalculationHistory history) {
        this.input = input;
        this.history = history;
    }

    public void startConversion() {
        ConsoleUI.header("CURRENCY CONVERTER");
        ConsoleUI.info("Rates are configured reference values, not live exchange rates.");
        System.out.println("1. PKR to Foreign Currency");
        System.out.println("2. Foreign Currency to PKR");
        System.out.println("0. Back");
        int direction = ConsoleUI.readIntInRange(input, "Choose direction: ", 0, 2);
        if (direction == 0) return;

        System.out.println("\n1. US Dollar (USD)");
        System.out.println("2. Euro (EUR)");
        System.out.println("3. Chinese Yuan (CNY)");
        System.out.println("4. UAE Dirham (AED)");
        int currency = ConsoleUI.readIntInRange(input, "Choose currency: ", 1, 4);
        double amount = ConsoleUI.readNonNegativeDouble(input, "Enter amount: ");
        double rate = getRate(currency);
        String code = getCode(currency);
        double result = direction == 1 ? amount / rate : amount * rate;

        if (direction == 1) System.out.printf("\n%.2f PKR = %.2f %s%n", amount, result, code);
        else System.out.printf("\n%.2f %s = %.2f PKR%n", amount, code, result);

        ConsoleUI.success("Conversion completed successfully.");
        history.add(direction == 1
                ? String.format("%.2f PKR = %.2f %s", amount, result, code)
                : String.format("%.2f %s = %.2f PKR", amount, code, result));
        ConsoleUI.pause(input);
    }

    private double getRate(int currency) {
        switch (currency) {
            case 1: return usdRate;
            case 2: return euroRate;
            case 3: return yuanRate;
            case 4: return dirhamRate;
            default: return 1;
        }
    }

    private String getCode(int currency) {
        switch (currency) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "CNY";
            case 4: return "AED";
            default: return "";
        }
    }
}
