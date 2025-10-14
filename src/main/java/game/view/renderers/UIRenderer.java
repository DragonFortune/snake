package game.view.renderers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class UIRenderer {
    private final GraphicsContext gc;

    public UIRenderer(GraphicsContext gc) {
        this.gc = gc;
    }

    public void render(int score, int highScore, int canvasWidth, int canvasHeight, boolean gameOver ) {
        drawScore(score, highScore, canvasWidth);
        drawOverlay(gameOver, canvasWidth, canvasHeight);
    }

    private void drawOverlay(boolean gameOver, int canvasWidth, int canvasHeight) {
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

    private void drawScore (int score, int highScore, int canvasWidth) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(16));
        gc.fillText("Score: " + score, 8, 18);
        gc.fillText("High: " + highScore, canvasWidth - 80, 18);
    }
}