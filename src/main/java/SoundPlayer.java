import com.almasb.fxgl.app.services.FXGLAssetLoaderService;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Sound;
import utils.PomodoroTasks;


public class SoundPlayer implements PomodoroTasks {

    AudioPlayer player;
    Sound breakSound;
    Sound workSound;


    public SoundPlayer(FXGLAssetLoaderService loader, AudioPlayer player) {
        this.player = player;

        breakSound = loader.loadSound("break.wav");
        workSound = loader.loadSound("work.wav");
    }

    @Override
    public Runnable runOnBreak() {
        return () -> player.playSound(breakSound);
    }

    @Override
    public Runnable runOnWork() {
        return () -> player.playSound(workSound);
    }
}
