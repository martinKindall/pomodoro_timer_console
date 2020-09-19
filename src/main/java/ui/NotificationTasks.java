package ui;

import utils.PomodoroTasks;

import java.awt.*;

public class NotificationTasks implements PomodoroTasks {

    Notification notification = new Notification();

    @Override
    public Runnable runOnBreak() {
        return () -> {
            try {
                notification.displayTray("Break started!", "Take a rest :)");
            } catch (AWTException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public Runnable runOnWork() {
        return () -> {
            try {
                notification.displayTray("Work started!", "Do your best!");
            } catch (AWTException e) {
                e.printStackTrace();
            }
        };
    }
}
