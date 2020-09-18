package logic;

import io.reactivex.rxjava3.core.Observable;

public interface PomodoroTimer {

    int programmedDelayInMinutes();
    boolean isRunning();
    boolean isOnBreak();
    Observable<Integer> start();
    void startAndSubscribe();
    void stop();
}
