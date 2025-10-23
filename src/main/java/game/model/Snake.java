package game.model;

import game.config.GameConfig;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<Point> body = new LinkedList<>();
    private Direction currentDirection = Direction.RIGHT;

    public Snake() {
        body.add(new Point(10, 10));
    }

    public void move(Direction newDirection, Food food) {
        if (newDirection != null && !newDirection.isOpposite(currentDirection)) {
            currentDirection = newDirection;
        }

        Point newHead = getHead().move(currentDirection);
        body.add(0, newHead);

        if (!newHead.equals(food.getPosition())) {
            body.remove(body.size() - 1);
        }
    }

    public boolean checkCollision(Point p) {
        return p.x() < 0 || p.y() < 0 || p.x() >= GameConfig.GRID_WIDTH ||
                p.y() >= GameConfig.GRID_HEIGHT || body.contains(p);
    }

    public Point getHead() {
        return body.getFirst();
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return currentDirection;
    }
}
