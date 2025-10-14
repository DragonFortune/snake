package game.config;

public class GameConfig {
    public static final int GRID_WIDTH = 25;
    public static final int GRID_HEIGHT = 25;
    public static final int TILE_SIZE = 20;

    public static int getCanvasWidth() {
        return GRID_WIDTH * TILE_SIZE;
    }

    public static int getCanvasHeight() {
        return GRID_HEIGHT * TILE_SIZE;
    }
}
