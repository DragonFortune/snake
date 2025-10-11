package game.model;

import java.util.Random;

public class Food {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private Point position;

    public void spawn(Snake snake) {
        Random random = new Random();
        Point p;
        do {
            p = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        } while (snake.getBody().contains(p));
        position = p;
    }

    public Point getPosition() {
        return position;
    }
}
