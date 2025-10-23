package game.view.renderers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridRenderer implements Renderer {
    private final Color color;
    private final int canvasWidth;
    private final int canvasHeight;
    private final int tileSize;

    public GridRenderer(Color color, int canvasWidth, int canvasHeight, int tileSize) {
        this.color = color;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.tileSize = tileSize;
    }

    public void render(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(0.5);

        for (int x = 0; x <= canvasWidth; x += tileSize) {
            gc.strokeLine(x, 0, x, canvasHeight);
        }

        for (int y = 0; y <= canvasHeight; y += tileSize) {
            gc.strokeLine(0, y, canvasWidth, y);
        }
    }
}
