import java.util.ArrayList;

public class CalculationHistory {
    private final ArrayList<String> history = new ArrayList<>();

    public void add(String entry) {
        history.add(entry);
    }

    public void show() {
        ConsoleUI.header("CALCULATION HISTORY");
        if (history.isEmpty()) {
            ConsoleUI.info("No calculations have been performed yet.");
            return;
        }

        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
        ConsoleUI.line();
        System.out.println("Total entries: " + history.size());
    }
}
