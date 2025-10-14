package game.view.renderers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class UIRenderer implements Renderer{
    private final int canvasWidth;
    private final int canvasHeight;

    private int score;
    private int highScore;
    private boolean gameOver;

    public UIRenderer(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void updateState(int score, int highScore, boolean gameOver) {
        this.score = score;
        this.highScore = highScore;
        this.gameOver = gameOver;
    }

    public void render(GraphicsContext gc) {
        drawScore(gc);
        drawOverlay(gc);
    }

    private void drawOverlay(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.color(0, 0, 0, 0.5));
            gc.fillRect(0, 0, canvasWidth, canvasHeight);

            gc.setFill(Color.YELLOW);
            gc.setFont(Font.font(36));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("GAME OVER", canvasWidth / 2.0, canvasHeight / 2.0 - 10);

            gc.setFont(Font.font(16));
            gc.fillText("Press ENTER to restart", canvasWidth / 2.0, canvasHeight / 2.0 + 20);

            gc.setTextAlign(TextAlignment.LEFT);
        }
    }

    private void drawScore (GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(16));
        gc.fillText("Score: " + score, 8, 18);
        gc.fillText("High: " + highScore, canvasWidth - 80, 18);
    }
}