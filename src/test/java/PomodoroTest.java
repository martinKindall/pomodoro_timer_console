import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class PomodoroTest {

    PomodoroTimer pomodoro;
    int delayInMinutes = 1;

    @Before
    public void setup() {
        pomodoro = new SimplePomodoro(delayInMinutes);
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
}
