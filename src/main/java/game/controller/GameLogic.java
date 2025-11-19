package game.controller;

import game.model.*;

public class GameLogic {
    private static final int FOOD_SCORE = 10;
    private static final int BONUS_FOOD_SCORE = 50;
    private static final int BONUS_GROW = 3;

    public void update(GameState state) {
        Snake snake = state.getSnake();
        Food food = state.getFood();
        BonusFood bonusFood = state.getBonusFood();
        bonusFood.update(snake);
        InputHandler input = state.getInput();

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

        if (bonusFood.isActive()) {
            for (Point p : bonusFood.getOccupiedCells()) {
                if (newHead.equals(p)) {
                    state.getScore().addPoint(BONUS_FOOD_SCORE);
                    snake.grow(BONUS_GROW);
                    bonusFood.deactivate();
                    break;
                }
            }
        }
    }
}
