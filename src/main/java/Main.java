import logic.PomodoroFactory;
import ui.ConsoleUI;
import utils.ConsoleUtils;

public class Main {

    public static void main(String[] args) {
        PomodoroFactory factory = new PomodoroFactory();
        ConsoleUtils utils = new ConsoleUtils();

        ConsoleUI ui = new ConsoleUI(utils,factory);
    }
}
