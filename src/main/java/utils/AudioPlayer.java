package utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
                playerPath + "\\" + wavFile);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            process.waitFor();
            handleInputStreamAndPrint(process.getInputStream());
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
//        return () -> AudioPlayer.this.executeCommand(workWav);
        return () -> System.out.println("Work started.");
    }

    private void handleInputStreamAndPrint(InputStream stream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        System.out.println(textBuilder);
    }
}
