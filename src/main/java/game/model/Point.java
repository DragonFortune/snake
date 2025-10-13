package game.model;

import static game.view.GameView.GRID_HEIGHT;
import static game.view.GameView.GRID_WIDTH;

public record Point(int x, int y) {
    public Point move (Direction direction) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case UP -> newY--;
            case DOWN -> newY++;
            case LEFT -> newX--;
            case RIGHT -> newX++;
        }

        // оборачивание координат по краям
        if (newX < 0) newX = GRID_WIDTH - 1;
        if (newX >= GRID_WIDTH) newX = 0;
        if (newY < 0) newY = GRID_HEIGHT - 1;
        if (newY >= GRID_HEIGHT) newY = 0;

        return new Point(newX, newY);
    }
}
