package ui;

import utils.ConsoleUtils;

public class ConsoleUI {

    ConsoleUtils utils;

    public ConsoleUI(ConsoleUtils utils) {
        this.utils = utils;
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
