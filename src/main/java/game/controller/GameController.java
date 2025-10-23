package game.controller;

import game.config.GameConfig;
import game.model.GameState;
import game.utils.ThemeLoader;
import game.view.GameMenu;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController {
    private final Scene scene;

    private final GameInitializer initializer;
    private final GameLoopManager loopManager;
    private final GameLogic logic;

    private GameState state;

    public GameController(Stage stage) {
        this.initializer = new GameInitializer();
        this.loopManager = new GameLoopManager();
        this.logic = new GameLogic();
        // создаём меню
        GameMenu menu = new GameMenu(GameConfig.GRID_WIDTH * GameConfig.TILE_SIZE,
                GameConfig.GRID_HEIGHT * GameConfig.TILE_SIZE);

        // создаём сцену один раз на основе меню
        scene = new Scene(menu.getRoot());
        stage.setScene(scene);
        stage.setTitle("🐍 Snake Game");
        stage.show();

        // обработка нажатия кнопки Start
        menu.setOnStart(this::startNewGame);
    }

    private void startNewGame() {
        state = initializer.initGame(scene, ThemeLoader.load("classic"));
        scene.setRoot(state.getView().getRoot());
        loopManager.start(this::update);
    }

    private void update() {
        if (state.isGameOver()) {
            if (state.getInput().isRestartPressed()) startNewGame();
            return;
        }
        logic.update(state);
        state.getView().render(
                state.isGameOver(),
                state.getScore().getScore(),
                state.getScore().getHighScore());
    }
}
