package game.controller;

import game.config.GameConfig;
import game.model.*;
import game.view.GameView;
import game.view.renderers.*;
import javafx.scene.Scene;

public class GameInitializer {
    public GameState initGame(Scene scene, GameTheme theme) {
        Snake snake = new Snake();
        Food food = new Food();
        food.spawn(snake);
        BonusFood bonusFood = new BonusFood(5000, 50_000);
        bonusFood.spawn(snake);
        Score score = new Score();
        SnakeRenderer snakeRenderer = new SnakeRenderer(
                snake,
                theme.getSnakeHead(),
                theme.getSnakeBody(),
                GameConfig.TILE_SIZE
        );
        FoodRenderer foodRenderer = new FoodRenderer(food, theme.getFood(), GameConfig.TILE_SIZE);
        BonusFoodRenderer bonusFoodRenderer = new BonusFoodRenderer(
                bonusFood,
                theme.getFood(),
                GameConfig.TILE_SIZE
        );
        GridRenderer gridRenderer = new GridRenderer(
                theme.getGridColor(),
                GameConfig.getCanvasWidth(),
                GameConfig.getCanvasHeight(),
                GameConfig.TILE_SIZE
        );
        UiRenderer uiRenderer = new UiRenderer(GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight());
        GameView view = new GameView(
                snakeRenderer,
                foodRenderer,
                bonusFoodRenderer,
                gridRenderer,
                uiRenderer
        );
        InputHandler input = new InputHandler(scene);
        return new GameState(snake, food, bonusFood, score, view, input);
    }
}
