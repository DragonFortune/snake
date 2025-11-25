package game.controller;

import game.model.Direction;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputHandler {
    private Direction direction = Direction.RIGHT;
    private boolean restartPressed = false;
    private boolean pausePressed = false;

    public InputHandler(Scene scene) {
        scene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            switch (code) {
                case UP -> {
                    if (direction != Direction.DOWN) direction = Direction.UP; }
                case DOWN -> {
                    if (direction != Direction.UP) direction = Direction.DOWN; }
                case LEFT -> {
                    if (direction != Direction.RIGHT) direction = Direction.LEFT; }
                case RIGHT -> {
                    if (direction != Direction.LEFT) direction = Direction.RIGHT; }
                case ENTER -> restartPressed = true;
                case ESCAPE -> pausePressed = true;
            }
        });
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isPausePressed() {
        boolean pressed = pausePressed;
        pausePressed = false;
        return pressed;
    }

    public boolean isRestartPressed() {
        boolean pressed = restartPressed;
        restartPressed = false;
        return pressed;
    }
}
