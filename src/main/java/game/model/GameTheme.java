package game.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameTheme {
    private final Image snakeHead;
    private final Image snakeBody;
    private final Image food;
    private final Image background;
    private final Color gridColor;

    public GameTheme(Image snakeHead, Image snakeBody, Image food, Image background, Color gridColor) {
        this.snakeHead = snakeHead;
        this.snakeBody = snakeBody;
        this.food = food;
        this.background = background;
        this.gridColor = gridColor;
    }

    public Image getSnakeHead() {
        return snakeHead;
    }

    public Image getSnakeBody() {
        return snakeBody;
    }

    public Image getFood() {
        return food;
    }

    public Image getBackground() {
        return background;
    }

    public Color getGridColor() {
        return gridColor;
    }
}
