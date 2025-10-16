package game.model;

import game.controller.InputHandler;
import game.view.GameView;

public class GameState {
    private final Snake snake;
    private final Food food;
    private final Score score;
    private final GameView view;
    private final InputHandler input;
    private boolean gameOver;

    public GameState(Snake snake, Food food, Score score, GameView view, InputHandler input) {
        this.snake = snake;
        this.food = food;
        this.score = score;
        this.view = view;
        this.input = input;
    }
}
