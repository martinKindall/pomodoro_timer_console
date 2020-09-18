import io.reactivex.rxjava3.core.*;

import java.util.concurrent.TimeUnit;

public class SimplePomodoro implements PomodoroTimer {

    private int delayInMinutes;
    private boolean isRunning;
    private boolean hasFinished;
    private Runnable task;

    public SimplePomodoro(int delayInMinutes, Runnable task) {
        this.delayInMinutes = delayInMinutes;
        isRunning = false;
        this.task = task;
        hasFinished = false;
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
        return hasFinished;
    }

    @Override
    public Completable start() {
        hasFinished = false;
        isRunning = true;

        return Completable.complete().delay(delayInMinutes*60, TimeUnit.SECONDS)
                .doOnComplete(() -> {
                    hasFinished = true;
                    task.run();
                });
    }
}
