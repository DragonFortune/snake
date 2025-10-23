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
        this.gameOver = false;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public Score getScore() {
        return score;
    }

    public GameView getView() {
        return view;
    }

    public InputHandler getInput() {
        return input;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
