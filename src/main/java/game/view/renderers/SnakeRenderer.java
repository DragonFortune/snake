package game.view.renderers;

import game.model.Direction;
import game.model.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SnakeRenderer {
    private final Snake snake;
    private final Image headImage;
    private final Image bodyImage;

    public SnakeRenderer(Snake snake, Image headImage, Image bodyImage) {
        this.snake = snake;
        this.headImage =headImage;
        this.bodyImage = bodyImage;
    }

    public void render(GraphicsContext gc, double tileSize) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            var p = snake.getBody().get(i);
            double x = p.x() * tileSize;
            double y = p.y() * tileSize;

            if (i == 0) drawHead(gc, x, y, tileSize, tileSize, snake.getDirection());
            else drawBody(gc, x, y, tileSize, tileSize);
        }
    }

    private void drawHead(GraphicsContext gc, double x, double y, double w, double h, Direction dir) {
        if (headImage == null || dir == null) {
            gc.fillRect(x, y, w, h);
            return;
        }
        double angle = switch (dir) {
            case UP -> 0;
            case RIGHT -> 90;
            case DOWN -> 180;
            case LEFT -> 270;
        };
        gc.save();
        gc.translate(x + w / 2, y + h / 2);
        gc.rotate(angle);
        gc.drawImage(headImage, -w / 2, -h / 2, w, h);
        gc.restore();
    }

    private void drawBody(GraphicsContext gc, double x, double y, double w, double h) {
        if (bodyImage != null) gc.drawImage(bodyImage, x, y, w, h);
        else gc.fillRect(x, y, w, h);
    }
}
