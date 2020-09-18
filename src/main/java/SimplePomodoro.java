public class SimplePomodoro implements PomodoroTimer {

    private int delayInMinutes;
    private boolean isRunning;
    private long startTime;

    public SimplePomodoro(int delayInMinutes) {
        this.delayInMinutes = delayInMinutes;
        isRunning = false;
        startTime = 0;
    }

    @Override
    public int programmedDelayInMinutes() {
        return delayInMinutes;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean hasFinished() {
        if (!isRunning) return false;

        long currentMillis = System.currentTimeMillis();
        return (currentMillis - startTime)/1000 > (delayInMinutes*60);
    }

    @Override
    public void start() {
        isRunning = true;
        startTime = System.currentTimeMillis();
    }
}
