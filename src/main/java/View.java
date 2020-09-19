import com.almasb.fxgl.app.GameSettings;

import com.almasb.fxgl.app.GameApplication;

import static com.almasb.fxgl.dsl.FXGL.*;

public class View extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(480);
        gameSettings.setHeight(480);
        gameSettings.setTitle("Lumbar Pomodoro");
        gameSettings.setVersion("1.0");
        gameSettings.setGameMenuEnabled(false);
        gameSettings.setMainMenuEnabled(false);
        gameSettings.setDeveloperMenuEnabled(false);
    }

    @Override
    protected void initGame() {
        getGameScene().clearUINodes();
        getGameScene().clearGameViews();
        getGameScene().clearCSS();
        getGameScene().clearEffect();
        new MainComponent().init(new SoundPlayer(getAssetLoader(), getAudioPlayer()));
    }
}
