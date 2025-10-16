package game.controller;

import game.model.*;

public class GameLogic {
    public void update(GameState state) {
        Snake snake = state.getSnake();
        Food food = state.getFood();
        InputHandler input = state.getInput();

        Direction dir = input.getDirection();
        Point newHead = snake.getHead().move(dir);

        if (snake.checkCollision(newHead)) {
            state.setGameOver(true);
            return;
        }

        snake.move(dir, food);
        if (newHead.equals(food.getPosition())) {
            state.getScore().addPoint(10);
            food.spawn(snake);
        }
    }
}
