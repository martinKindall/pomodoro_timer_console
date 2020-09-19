package utils;

import java.io.IOException;

public class AudioPlayer implements PomodoroTasks {

    String playerPath;
    String playerFile = "AudioPlayer.py";
    String breakWav = "break.wav";
    String workWav = "work.wav";

    public AudioPlayer(String playerPath) {
        this.playerPath = playerPath;
    }

    private void executeCommand(String wavFile) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "python",
                playerPath + "\\" + playerFile,
                wavFile);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            System.out.println(process.getInputStream().toString());
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Runnable runOnBreak() {
        return () -> AudioPlayer.this.executeCommand(breakWav);
    }

    @Override
    public Runnable runOnWork() {
        return () -> AudioPlayer.this.executeCommand(workWav);
    }
}
