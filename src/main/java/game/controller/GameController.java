package game.controller;

import game.model.*;
import game.view.GameMenu;
import game.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Контроллер игры: логика + управление потоком отрисовки.
 */
public class GameController {
    private Stage stage;

    private Snake snake;
    private Food food;
    private GameView view;
    private GameMenu menu;

    private Direction direction = Direction.RIGHT;
    private boolean gameOver = false;

    private long lastUpdate = 0;
    private int score = 0;
    private int highScore = 0;

    private AnimationTimer timer;

    public GameController(Stage stage) {

        this.stage = stage;
        initGame(); // создаём модель и view
        // создаём сцену на основе view
        Scene scene = new Scene(view.getRoot());
        // клавиатура
        scene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            if (code == KeyCode.UP && direction != Direction.DOWN) direction = Direction.UP;
            if (code == KeyCode.DOWN && direction != Direction.UP) direction = Direction.DOWN;
            if (code == KeyCode.LEFT && direction != Direction.RIGHT) direction = Direction.LEFT;
            if (code == KeyCode.RIGHT && direction != Direction.LEFT) direction = Direction.RIGHT;

            // перезапуск по Enter
            if (code == KeyCode.ENTER && gameOver) {
                resetGame();
            }
        });

        stage.setScene(scene);
        stage.setTitle("🐍 Snake Game");
        stage.show();

        menu = new GameMenu(GameView.GRID_WIDTH, GameView.GRID_HEIGHT);
        menu.setOnStart(() -> {
            Snake snake = new Snake();
            Food food = new Food();
            food.spawn(snake);

            view = new GameView(snake, food);

            stage.getScene().setRoot(view.getRoot());
            startGame(snake, food);
        });

        stage.getScene().setRoot(menu.getRoot());
    }

    // инициализация (создаёт новые объекты модели и view)
    private void initGame() {
        snake = new Snake();        // конструктор Snake должен добавлять стартовый сегмент
        food = new Food();
        view = new GameView(snake, food);
        food.spawn(snake);         // ставим еду в свободную клетку
        direction = Direction.RIGHT;
        gameOver = false;
        lastUpdate = 0;
        score = 0;
    }

    // старт игрового цикла
    public void startGame(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) lastUpdate = now;
                if (now - lastUpdate >= 200_000_000L) {
                    update(); // работает с snake и food, которые уже созданы
                    view.render(gameOver, score, highScore);
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    // сброс игры: создаём новую модель и обновляем корень сцены
    private void resetGame() {
        initGame();
        // заменяем корень текущей сцены на новый view (чтобы отображение обновилось)
        if (stage.getScene() != null) {
            stage.getScene().setRoot(view.getRoot());
        }
        // не трогаем highScore — он хранится между попытками
    }

    // основной апдейт логики
    private void update() {
        if (gameOver) return;
        if (snake == null || snake.getBody().isEmpty()) return; // защита

        Point head = snake.getHead();
        Point newHead = head.move(direction);

        // столкновение со стеной или с телом
        if (snake.checkCollision(newHead)) {
            gameOver = true;
            // обновляем рекорд при смерти
            if (score > highScore) highScore = score;
            return;
        }

        // проверяем: съели ли еду (до перемещения)
        boolean ateFood = newHead.equals(food.getPosition());

        // перемещаем змейку (метод должен добавлять голову и убирать хвост, если не съели)
        snake.move(newHead, food);

        if (ateFood) {
            score++;
            // спавним новую еду (snake уже обновлён, поэтому spawn исключит клетки змеи)
            food.spawn(snake);
        }
    }
}
