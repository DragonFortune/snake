package game.view.renderers;

import game.model.BonusFood;
import game.model.Point;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BonusFoodRenderer implements Renderer {
    private final BonusFood bonusFood;
    private final Image foodImage;
    private final int tileSize;

    public BonusFoodRenderer(BonusFood bonusFood, Image foodImage, int tileSize) {
        this.bonusFood = bonusFood;
        this.foodImage = foodImage;
        this.tileSize = tileSize;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (bonusFood.isExpired() || bonusFood.getOccupiedCells() == null) return;

        double scale = 0.8 + 0.2 * Math.sin((double) System.nanoTime() / 180_000_000);
        double size = tileSize * scale;
        double offset = (tileSize - size) / 2;

        List<Point> cells = bonusFood.getOccupiedCells();
        if (cells == null || cells.isEmpty()) return;

        int minX = cells.stream().mapToInt(Point::x).min().orElse(0);
        int minY = cells.stream().mapToInt(Point::y).min().orElse(0);
        int maxX = cells.stream().mapToInt(Point::x).max().orElse(0);
        int maxY = cells.stream().mapToInt(Point::y).max().orElse(0);

        double fx = minX * tileSize;
        double fy = minY * tileSize;
        double width = (maxX - minX + 1) * tileSize;
        double height = (maxY - minY + 1) * tileSize;

        gc.drawImage(foodImage, fx + offset, fy + offset, width, height);
    }
}
