package ui;

import logic.PomodoroFactory;
import logic.PomodoroTimer;
import utils.ConsoleUtils;
import utils.PomodoroTasks;

public class ConsoleUI {

    ConsoleUtils utils;
    Runnable onBreakTask;
    Runnable onWorkTask;

    public ConsoleUI(
            ConsoleUtils utils,
            PomodoroFactory factory,
            PomodoroTasks tasks) {
        this.utils = utils;

        onBreakTask = () -> {
            utils.print("Time to take a break :)");
        };

        onWorkTask = () -> {
          utils.print("Work started!");
        };

        PomodoroTimer timer = factory.create(
                requestWorkTime(),
                requestBreakTime(),
                () -> {
                    onBreakTask.run();
                    tasks.runOnBreak().run();
                },
                () -> {
                    onWorkTask.run();
                    tasks.runOnWork().run();
                }
        );
        System.out.println("Work started!");
        timer.startAndSubscribe();
    }

    private String lineSeparator = "\n\n";

    void welcomeMsg() {
        String welcomeMsg = "Welcome to Lumbar Pomodoro Timer";

        System.out.println(welcomeMsg);
        System.out.println(lineSeparator);
    }

    int requestWorkTime() {
        return utils.readInt("Enter work time in minutes: ");
    }

    int requestBreakTime() {
        return utils.readInt("Enter break time in minutes: ");
    }
}
