package logic;

public class PomodoroFactory {

    public PomodoroTimer create(
            int workTime,
            int breakTime,
            Runnable onBreakTask,
            Runnable onWorkTask
    ) {
        return new SimplePomodoro(
                workTime,
                breakTime,
                onBreakTask,
                onWorkTask
        );
    }
}
