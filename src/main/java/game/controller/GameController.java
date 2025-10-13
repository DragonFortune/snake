package game.controller;

import game.model.Food;
import game.model.Snake;
import game.view.GameView;
import game.view.GameMenu;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController {

    private Stage stage;
    private Scene scene;

    private Snake snake;
    private Food food;
    private GameView view;
    private GameMenu menu;
    private InputHandler input;
    private GameLoop gameLoop;

    private boolean gameOver = false;

    public GameController(Stage stage) {
        this.stage = stage;

        // создаём меню
        menu = new GameMenu(GameView.GRID_WIDTH * GameView.TILE_SIZE,
                GameView.GRID_HEIGHT * GameView.TILE_SIZE);

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

        // создаём view
        view = new GameView(snake, food);

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
            view.render(true, snake.getBody().size() - 1, 0);
            return;
        }

        var direction = input.getDirection();
        var head = snake.getHead();
        var newHead = head.move(direction);

        if (snake.checkCollision(newHead)) {
            gameOver = true;
            view.render(true, snake.getBody().size() - 1, 0);
            return;
        }

        boolean ateFood = newHead.equals(food.getPosition());
        snake.move(newHead, food);

        if (ateFood) food.spawn(snake);

        view.render(false, snake.getBody().size() - 1, 0);
    }
}
