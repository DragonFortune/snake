package game.model;

import game.controller.InputHandler;
import game.view.GameView;

public class GameState {
    private boolean gameOver = false;
    public boolean paused = false;

    private final Snake snake;
    private final Food food;
    private final BonusFood bonusFood;
    private final Score score;
    private final GameView view;
    private final InputHandler input;

    public GameState(Snake snake, Food food, BonusFood bonusFood,
                     Score score, GameView view, InputHandler input) {
        this.snake = snake;
        this.food = food;
        this.bonusFood = bonusFood;
        this.score = score;
        this.view = view;
        this.input = input;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public BonusFood getBonusFood() {
        return bonusFood;
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

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean getPaused() {
        return paused;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
