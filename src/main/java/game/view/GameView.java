package game.view;

import game.config.GameConfig;
import game.model.Food;
import game.model.GameTheme;
import game.model.Snake;
import game.view.renderers.FoodRenderer;
import game.view.renderers.GridRenderer;
import game.view.renderers.SnakeRenderer;
import game.view.renderers.UIRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Отрисовка игрового поля. Рисует фон, сетку, еду, сегменты змейки и UI (Score / High score).
 * Также отображает overlay при Game Over.
 */
public class GameView {
    private final StackPane root = new StackPane();
    private final GraphicsContext gc;

    private final SnakeRenderer snakeRenderer;
    private final FoodRenderer foodRenderer;
    private final GridRenderer gridRenderer;
    private final UIRenderer uiRenderer;

    public GameView(GameTheme theme, Snake snake, Food food) {
        Canvas canvas = new Canvas(GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight());
        this.gc = canvas.getGraphicsContext2D();

        this.snakeRenderer = new SnakeRenderer(snake, theme.getSnakeHead(), theme.getSnakeBody());
        this.foodRenderer = new FoodRenderer(food, theme.getFood());
        this.gridRenderer = new GridRenderer(theme.getGridColor());
        this.uiRenderer = new UIRenderer(gc);

        root.getChildren().add(canvas);
        gc.setFont(Font.font(16));
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
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight());

        gridRenderer.render(gc, GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight(), GameConfig.TILE_SIZE);
        foodRenderer.render(gc, GameConfig.TILE_SIZE);
        snakeRenderer.render(gc, GameConfig.TILE_SIZE);
        uiRenderer.render(score, highScore, GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight(), gameOver);
    }

    public StackPane getRoot() {
        return root;
    }
}
