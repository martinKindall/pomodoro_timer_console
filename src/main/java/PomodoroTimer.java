public interface PomodoroTimer {

    int programmedDelayInMinutes();
    boolean isRunning();
    boolean hasFinished();
}
