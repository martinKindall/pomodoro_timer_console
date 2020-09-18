import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class PomodoroTest {

    PomodoroTimer pomodoro;
    int delayInMinutes = 1;
    Runnable justSomeAction;
    boolean testableCondition;

    @Before
    public void setup() {
        testableCondition = false;
        justSomeAction = () -> testableCondition = true;
        pomodoro = new SimplePomodoro(delayInMinutes, justSomeAction);
    }

    @Test
    public void checkInitialConditions() {
        Assert.assertEquals(pomodoro.programmedDelayInMinutes(), 1);
        Assert.assertFalse(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.hasFinished());
    }

    @Test
    public void checkItIsDone() {
        Completable observable = pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.hasFinished());

        TestObserver<Void> testObserver = TestObserver.create();
        observable.subscribe(testObserver);

        testObserver.awaitDone(50, TimeUnit.SECONDS);
        Assert.assertFalse(pomodoro.hasFinished());

        testObserver.awaitDone(20, TimeUnit.SECONDS);
        Assert.assertTrue(pomodoro.hasFinished());
    }

    @Test
    public void checkRunnableWasExecuted() {
        Completable observable = pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.hasFinished());
        Assert.assertFalse(testableCondition);

        TestObserver<Void> testObserver = TestObserver.create();
        observable.subscribe(testObserver);

        testObserver.awaitDone(65, TimeUnit.SECONDS);
        Assert.assertTrue(testableCondition);
    }
}
