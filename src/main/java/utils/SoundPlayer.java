package utils;

import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Sound;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class SoundPlayer implements PomodoroTasks {

    Sound breakSound = getAssetLoader().loadSound("break.wav");
    Sound workSound = getAssetLoader().loadSound("work.wav");

    @Override
    public Runnable runOnBreak() {
        return () -> new AudioPlayer().playSound(breakSound);
    }

    @Override
    public Runnable runOnWork() {
        return () -> new AudioPlayer().playSound(workSound);
    }
}
