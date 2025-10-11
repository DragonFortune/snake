package game.view;

import game.model.Food;
import game.model.Point;
import game.model.Snake;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Отрисовка игрового поля. Рисует фон, еду, сегменты змейки и UI (Score / High score).
 */
public class GameView {
    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;
    public static final int TILE_SIZE = 20;

    private static final int CANVAS_WIDTH = GRID_WIDTH * TILE_SIZE;
    private static final int CANVAS_HEIGHT = GRID_HEIGHT * TILE_SIZE;

    private final StackPane root = new StackPane();
    private final Canvas canvas;
    private final GraphicsContext gc;

    private final Snake snake;
    private final Food food;

    private final Font uiFont = Font.font(16);

    public GameView(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;

        // создаём Canvas и GraphicsContext — через него будут все draw-операции
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        gc.setFont(uiFont);
        gc.setTextAlign(TextAlignment.LEFT);
    }

    /**
     * render вызывается из AnimationTimer на JavaFX Application Thread.
     *
     * @param gameOver флаг конца игры
     * @param score    текущий счёт
     * @param highScore рекорд
     */
    public void render(boolean gameOver, int score, int highScore) {
        // 1) фон
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        renderGrid();

        // 2) еда
        Point f = food.getPosition();
        if (f != null) {
            double fx = f.x() * TILE_SIZE;
            double fy = f.y() * TILE_SIZE;
            gc.setFill(Color.RED);
            gc.fillOval(fx, fy, TILE_SIZE, TILE_SIZE);
        }

        // 3) змейка
        for (int i = 0; i < snake.getBody().size(); i++) {
            Point p = snake.getBody().get(i);
            if (i == 0) { // голова
                gc.setFill(Color.DARKGREEN);
            } else {     // тело
                gc.setFill(Color.LIMEGREEN);
            }
            gc.fillRect(p.x() * TILE_SIZE, p.y() * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
        }


        // 4) UI: score и high score
        gc.setFill(Color.WHITE);
        gc.setFont(uiFont);
        gc.fillText("Score: " + score, 8, 18);
        gc.fillText("High: " + highScore, CANVAS_WIDTH - 80, 18);

        // 5) Game Over overlay
        if (gameOver) {
            gc.setFill(Color.color(0, 0, 0, 0.5));
            gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

            gc.setFill(Color.YELLOW);
            gc.setFont(Font.font(36));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("GAME OVER", CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0 - 10);

            gc.setFont(Font.font(16));
            gc.fillText("Press ENTER to restart", CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0 + 20);

            gc.setTextAlign(TextAlignment.LEFT); // вернуть выравнивание по умолчанию
        }
    }

    public StackPane getRoot() {
        return root;
    }

    private void renderGrid() {
        gc.setStroke(Color.web("#303030")); // тёмно-серый цвет линий
        gc.setLineWidth(0.5);

        // Вертикальные линии
        for (int x = 0; x <= CANVAS_WIDTH; x += TILE_SIZE) {
            gc.strokeLine(x, 0, x, CANVAS_HEIGHT);
        }

        // Горизонтальные линии
        for (int y = 0; y <= CANVAS_HEIGHT; y += TILE_SIZE) {
            gc.strokeLine(0, y, CANVAS_WIDTH, y);
        }
    }

}
