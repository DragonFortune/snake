package game.model;

import game.view.GameView;

import java.util.LinkedList;

public class Snake {

    private final LinkedList<Point> body = new LinkedList<>();

    public Snake() {
        body.add(new Point(10, 10));
    }

    public void move(Point newHead, Food food) {
        body.addFirst(newHead);
        if (newHead.equals(food.getPosition())) {
            food.spawn(this);
        } else {
            body.removeLast();
        }
    }

    public boolean checkCollision(Point p) {
        return p.x() < 0 || p.y() < 0 || p.x() >= GameView.GRID_WIDTH || p.y() >= GameView.GRID_HEIGHT || body.contains(p);
    }

    public Point getHead() {
        return body.getFirst();
    }

    public LinkedList<Point> getBody() {
        return body;
    }
}
