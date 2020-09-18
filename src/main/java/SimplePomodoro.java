public class SimplePomodoro implements PomodoroTimer {

    private int delay;

    public SimplePomodoro(int delay) {
        this.delay = delay;
    }

    @Override
    public int programmedDelayInMinutes() {
        return delay;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean hasFinished() {
        return false;
    }
}
