package game.controller;

import game.config.GameConfig;
import game.model.Food;
import game.model.GameState;
import game.model.GameTheme;
import game.model.Score;
import game.model.Snake;
import game.view.GameView;
import game.view.renderers.FoodRenderer;
import game.view.renderers.GridRenderer;
import game.view.renderers.SnakeRenderer;
import game.view.renderers.UiRenderer;
import javafx.scene.Scene;

public class GameInitializer {
    public GameState initGame(Scene scene, GameTheme theme) {
        Snake snake = new Snake();
        Food food = new Food();
        food.spawn(snake);
        Score score = new Score();
        SnakeRenderer snakeRenderer = new SnakeRenderer(snake,
                theme.getSnakeHead(),
                theme.getSnakeBody(),
                GameConfig.TILE_SIZE);
        FoodRenderer foodRenderer = new FoodRenderer(food, theme.getFood(), GameConfig.TILE_SIZE);
        GridRenderer gridRenderer = new GridRenderer(theme.getGridColor(),
                GameConfig.getCanvasWidth(),
                GameConfig.getCanvasHeight(),
                GameConfig.TILE_SIZE);
        UiRenderer uiRenderer = new UiRenderer(GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight());
        GameView view = new GameView(snakeRenderer, foodRenderer, gridRenderer, uiRenderer);
        InputHandler input = new InputHandler(scene);
        return new GameState(snake, food, score, view, input);
    }
}
