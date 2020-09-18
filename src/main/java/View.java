import com.almasb.fxgl.app.GameSettings;

import com.almasb.fxgl.app.GameApplication;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAudioPlayer;

public class View extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
    }

    @Override
    protected void initGame() {
        new MainComponent().init(new SoundPlayer(getAssetLoader(), getAudioPlayer()));
    }
}
