package ui;

import logic.PomodoroFactory;
import logic.PomodoroTimer;
import utils.ConsoleUtils;

public class ConsoleUI {

    ConsoleUtils utils;
    Runnable onBreakTask;
    Runnable onWorkTask;

    public ConsoleUI(ConsoleUtils utils, PomodoroFactory factory) {
        this.utils = utils;

        onBreakTask = () -> {
            utils.print("Empezó el break");
        };

        onWorkTask = () -> {
          utils.print("Empezó el trabajo");
        };

        PomodoroTimer timer = factory.create(
                requestWorkTime(),
                requestBreakTime(),
                onBreakTask,
                onWorkTask
        );
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
