import logic.PomodoroFactory;
import ui.ConsoleUI;
import ui.NotificationTasks;
import utils.AudioPlayer;
import utils.ConsoleUtils;
import utils.PomodoroTasks;

public class MainComponent {

    public static void main(String[] args) {
        String audioPlayerPath = args[0];

        new MainComponent().init(audioPlayerPath);
    }

    public void init(String audioPlayerPath) {
        PomodoroFactory factory = new PomodoroFactory();
        ConsoleUtils utils = new ConsoleUtils();
        PomodoroTasks notificationTask = new NotificationTasks();
        PomodoroTasks audioPlayerTask = new AudioPlayer(audioPlayerPath);

        ConsoleUI ui = new ConsoleUI(
                utils,
                factory,
                new PomodoroTasks() {
                    @Override
                    public Runnable runOnBreak() {
                        return () -> {
                            audioPlayerTask.runOnBreak().run();
                            notificationTask.runOnBreak().run();
                        };
                    }

                    @Override
                    public Runnable runOnWork() {
                        return () -> {
                            audioPlayerTask.runOnWork().run();
                            notificationTask.runOnWork().run();
                        };
                    }
                }
        );
    }
}
