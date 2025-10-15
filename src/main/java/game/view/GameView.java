package game.view;

import game.config.GameConfig;
import game.view.renderers.*;
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
    private final UIRenderer uiRenderer;
    private final RendererManager rendererManager = new RendererManager();

    public GameView(GraphicsContext gc,
                    SnakeRenderer snakeRenderer,
                    FoodRenderer foodRenderer,
                    GridRenderer gridRenderer,
                    UIRenderer uiRenderer) {
        this.gc = gc;
        this.uiRenderer = uiRenderer;
        rendererManager.add(gridRenderer, 0);
        rendererManager.add(foodRenderer, 1);
        rendererManager.add(snakeRenderer, 2);
        rendererManager.add(uiRenderer, 3);
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

        uiRenderer.updateState(score, highScore, gameOver);

        rendererManager.renderAll(gc);
    }

    public StackPane getRoot() {
        return root;
    }
}
