import logic.PomodoroFactory;
import ui.ConsoleUI;
import ui.NotificationTasks;
import utils.ConsoleUtils;
import utils.PomodoroTasks;

public class MainComponent {

    public static void main(String[] args) {
        new MainComponent().init(new PomodoroTasks() {
            @Override
            public Runnable runOnBreak() {
                return () -> System.out.println("");
            }

            @Override
            public Runnable runOnWork() {
                return () -> System.out.println("");
            }
        });
    }

    public void init(PomodoroTasks tasks) {
        PomodoroFactory factory = new PomodoroFactory();
        ConsoleUtils utils = new ConsoleUtils();
        PomodoroTasks notificationTask = new NotificationTasks();

        ConsoleUI ui = new ConsoleUI(
                utils,
                factory,
                new PomodoroTasks() {
                    @Override
                    public Runnable runOnBreak() {
                        return () -> {
                            tasks.runOnBreak().run();
                            notificationTask.runOnBreak().run();
                        };
                    }

                    @Override
                    public Runnable runOnWork() {
                        return () -> {
                            tasks.runOnWork().run();
                            notificationTask.runOnWork().run();
                        };
                    }
                }
        );
    }
}
