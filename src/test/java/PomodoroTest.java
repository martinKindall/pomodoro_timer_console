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
        pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.hasFinished());

        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            Assert.assertFalse(pomodoro.hasFinished());
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            Assert.assertTrue(pomodoro.hasFinished());
        }
    }

    @Test
    public void checkRunnableWasExecuted() {
        pomodoro.start();
        Assert.assertTrue(pomodoro.isRunning());
        Assert.assertFalse(pomodoro.hasFinished());

        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            Assert.assertFalse(testableCondition);
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            Assert.assertTrue(testableCondition);
        }
    }
}
