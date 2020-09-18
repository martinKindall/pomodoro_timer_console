import io.reactivex.rxjava3.core.Completable;

public interface PomodoroTimer {

    int programmedDelayInMinutes();
    boolean isRunning();
    boolean hasFinished();
    Completable start();
}
