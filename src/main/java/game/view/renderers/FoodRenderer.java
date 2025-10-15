package game.view.renderers;

import game.model.Food;
import game.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FoodRenderer implements Renderer{
    private final Food food;
    private final Image foodImage;
    private final int tileSize;

    public FoodRenderer(Food food, Image foodImage, int tileSize) {
        this.food = food;
        this.foodImage = foodImage;
        this.tileSize = tileSize;
    }

    public void render(GraphicsContext gc) {
        Point f = food.getPosition();
        if (f != null) {
            double fx = f.x() * tileSize;
            double fy = f.y() * tileSize;

            double scale = 0.8 + 0.2 * Math.sin((double) System.nanoTime() / 180_000_000);
            double size = (tileSize + 8) * scale;
            double offset = (tileSize - size) / 2;
            gc.drawImage(foodImage, fx + offset, fy + offset, size, size);
        }
    }
}
