package logic;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

public class SimplePomodoro implements PomodoroTimer {

    private int delayInMinutes;
    private int breakDelayInMinutes;
    private boolean isRunning;
    private boolean isOnBreak;
    private Runnable startedBreakTask;
    private Runnable finishedBreakTask;
    private Disposable disposable;

    public SimplePomodoro(int delayInMinutes, int breakDelayInMinutes,
                          Runnable startedBreakTask, Runnable finishedBreakTask) {
        this.delayInMinutes = delayInMinutes;
        this.breakDelayInMinutes = breakDelayInMinutes;
        isRunning = false;
        this.startedBreakTask = startedBreakTask;
        this.finishedBreakTask = finishedBreakTask;
        isOnBreak = false;
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
    public boolean isOnBreak() {
        return isOnBreak;
    }

    @Override
    public Observable<Integer> start() {
        isOnBreak = false;
        isRunning = true;

        ReplaySubject<Integer> newInterval = ReplaySubject.create();

        Observable<Integer> finalObservable = newInterval.flatMap(currentPeriod ->
                Observable.just(0).delay(currentPeriod*60, TimeUnit.SECONDS)
                .map(v -> {
                    if (isOnBreak) {
                        isOnBreak = false;
                        finishedBreakTask.run();
                        newInterval.onNext(delayInMinutes);
                    } else {
                        isOnBreak = true;
                        startedBreakTask.run();
                        newInterval.onNext(breakDelayInMinutes);
                    }

                    return 0;
                })
        );

        newInterval.onNext(delayInMinutes);

        return finalObservable;
    }

    @Override
    public void startAndSubscribe() {
        disposable = start().subscribe();
    }

    @Override
    public void stop() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
