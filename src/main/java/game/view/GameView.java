package game.view;

import game.config.GameConfig;
import game.view.renderers.FoodRenderer;
import game.view.renderers.GridRenderer;
import game.view.renderers.SnakeRenderer;
import game.view.renderers.UIRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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

    public GameView(GraphicsContext gc,
                    SnakeRenderer snakeRenderer,
                    FoodRenderer foodRenderer,
                    GridRenderer gridRenderer,
                    UIRenderer uiRenderer) {
        this.gc = gc;
        this.snakeRenderer = snakeRenderer;
        this.foodRenderer = foodRenderer;
        this.gridRenderer = gridRenderer;
        this.uiRenderer = uiRenderer;
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
