package game.controller;

import game.model.*;

public class GameLogic {
    private static final int FOOD_SCORE = 10;
    private static final int BONUS_FOOD_SCORE = 50;
    private static final int BONUS_GROW = 3;

    public void update(GameState state) {
        if (state.isPaused() || state.isGameOver()) {
            return;
        }

        Snake snake = state.getSnake();
        Food food = state.getFood();
        BonusFood bonusFood = state.getBonusFood();
        InputHandler input = state.getInput();

        bonusFood.update(snake);

        Direction dir = input.getDirection();
        Point newHead = snake.getHead().move(dir);

        if (snake.checkCollision(newHead)) {
            state.setGameOver(true);
            return;
        }

        snake.move(dir, food);

        if (newHead.equals(food.getPosition())) {
            state.getScore().addPoint(FOOD_SCORE);
            snake.grow(1);
            food.spawn(snake);
        }

        if (bonusFood.isActive() && bonusFood.getOccupiedCells().contains(newHead)) {
            state.getScore().addPoint(BONUS_FOOD_SCORE);
            snake.grow(BONUS_GROW);
            bonusFood.deactivate();
        }

        if (input.isPausePressed()) {
            state.setPaused(!state.isPaused());
        }
    }
}
