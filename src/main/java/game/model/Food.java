package game.model;

import game.view.GameView;

import java.util.Random;

public class Food {

    private Point position;

    public void spawn(Snake snake) {
        Random random = new Random();
        Point p;
        do {
            p = new Point(random.nextInt(GameView.GRID_WIDTH), random.nextInt(GameView.GRID_HEIGHT));
        } while (snake.getBody().contains(p));
        position = p;
    }

    public Point getPosition() {
        return position;
    }
}
