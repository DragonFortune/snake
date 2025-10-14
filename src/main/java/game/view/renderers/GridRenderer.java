package game.view.renderers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridRenderer {
    private final Color color;

    public GridRenderer(Color color) {
        this.color = color;
    }

    public void render(GraphicsContext gc, int canvasWidth, int canvasHeight, double tileSize) {
        gc.setStroke(color);
        gc.setLineWidth(0.5);

        for (int x = 0; x<= canvasWidth; x+= (int) tileSize) {
            gc.strokeLine(x, 0, x, canvasHeight);
        }

        for (int y = 0; y<= canvasHeight; y+= (int) tileSize) {
            gc.strokeLine(0, y, canvasWidth, y);
        }
    }
}
