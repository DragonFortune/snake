package game.controller;

import game.config.GameConfig;
import game.model.Food;
import game.model.Score;
import game.model.Snake;
import game.utils.ThemeLoader;
import game.view.GameView;
import game.view.GameMenu;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController {

    private final Scene scene;

    private Snake snake;
    private Food food;
    private GameView view;
    private InputHandler input;
    private final Score score;
    private GameLoop gameLoop;

    private boolean gameOver = false;

    public GameController(Stage stage) {
        this.score = new Score();
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
        // создаём модели
        snake = new Snake();
        food = new Food();
        food.spawn(snake);
        gameOver = false;

        score.reset();

        // создаём view
        var theme = ThemeLoader.load("classic");
        view = new GameView(theme,snake, food);

        // заменяем корень сцены на игровое поле
        scene.setRoot(view.getRoot());

        // создаём InputHandler на этой сцене
        input = new InputHandler(scene);

        // создаём и запускаем GameLoop
        if (gameLoop != null) {
            gameLoop.stop();
        }

        gameLoop = new GameLoop(this::update);
        gameLoop.start();
    }

    private void update() {
        if (input == null) return; // защита от NPE

        if (gameOver) {
            if (input.isRestartPressed()) {
                startNewGame();
            }
            view.render(true, score.getScore(), score.getHighScore());
            return;
        }

        var direction = input.getDirection();
        var head = snake.getHead();
        var newHead = head.move(direction);

        if (snake.checkCollision(newHead)) {
            gameOver = true;
            score.updateHighScore();
            view.render(true, score.getScore(), score.getHighScore());
            return;
        }

        boolean ateFood = newHead.equals(food.getPosition());
        snake.move(input.getDirection(), food);

        if (ateFood) {
            score.addPoint(10);
            food.spawn(snake);
        }

        view.render(false, score.getScore(), score.getHighScore());
    }
}
