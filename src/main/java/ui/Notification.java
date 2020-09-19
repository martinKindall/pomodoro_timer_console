package ui;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Notification {

    public void displayTray(String title, String message) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(title, message, MessageType.INFO);
    }
}
