package logic;

public class PomodoroFactory {

    PomodoroTimer create(
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
