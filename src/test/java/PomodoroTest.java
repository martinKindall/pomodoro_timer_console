import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import logic.PomodoroTimer;
import logic.SimplePomodoro;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class PomodoroTest {

    PomodoroTimer pomodoro;
    int delayInMinutes = 1;
    int breakDelayInMinutes = 1;
    Runnable startedBreak;
    Runnable finishedBreak;
    boolean startedBreakCondition;
    boolean finishedBreakCondition;

    @Before
    public void setup() {
        startedBreakCondition = false;
        finishedBreakCondition = false;
        startedBreak = () -> startedBreakCondition = true;
        finishedBreak = () -> finishedBreakCondition = true;
        pomodoro = new SimplePomodoro(
                delayInMinutes,
                breakDelayInMinutes,
                startedBreak,
                finishedBreak);
    }

    @Test
    public void checkInitialConditions() {
        Assert.assertEquals(pomodoro.programmedDelayInMinutes(), 1);
        Assert.assertFalse(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.isOnBreak());
    }

    @Test
    public void checkItIsDone() {
        Observable<Integer> observable = pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.isOnBreak());

        TestObserver<Integer> testObserver = TestObserver.create();
        observable.subscribe(testObserver);

        testObserver.awaitDone(65, TimeUnit.SECONDS);
        Assert.assertTrue(pomodoro.isOnBreak());
    }

    @Test
    public void checkRunnableWasExecuted() {
        Observable<Integer> observable = pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.isOnBreak());
        Assert.assertFalse(startedBreakCondition);

        TestObserver<Integer> testObserver = TestObserver.create();
        observable.subscribe(testObserver);

        testObserver.awaitDone(65, TimeUnit.SECONDS);
        Assert.assertTrue(startedBreakCondition);
    }

    @Test
    public void checkBreakHasStartedAndFinished() {
        Observable<Integer> observable = pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.isOnBreak());
        Assert.assertFalse(startedBreakCondition);

        TestObserver<Integer> testObserver = TestObserver.create();
        observable.subscribe(testObserver);

        try {
            testObserver.await(65, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.assertTrue(pomodoro.isOnBreak());
        }

        try {
            testObserver.await(65, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.assertFalse(pomodoro.isOnBreak());
        }
    }
}
