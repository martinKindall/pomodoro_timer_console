import logic.PomodoroFactory;
import ui.ConsoleUI;
import utils.ConsoleUtils;
import utils.PomodoroTasks;

public class MainComponent {

    public void init(PomodoroTasks tasks) {
        PomodoroFactory factory = new PomodoroFactory();
        ConsoleUtils utils = new ConsoleUtils();
        ConsoleUI ui = new ConsoleUI(utils,factory, tasks);
    }
}
