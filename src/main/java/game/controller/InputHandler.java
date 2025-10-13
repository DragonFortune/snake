package game.controller;

import game.model.Direction;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputHandler {
    private Direction direction = Direction.RIGHT;
    private boolean restartPressed = false;

    public InputHandler(Scene scene) {
        scene.setOnKeyPressed(e -> {
            KeyCode code= e.getCode();
            if (code == KeyCode.UP && direction != Direction.DOWN) direction = Direction.UP;
            if (code == KeyCode.DOWN && direction != Direction.UP) direction = Direction.DOWN;
            if (code == KeyCode.LEFT && direction != Direction.RIGHT) direction = Direction.LEFT;
            if (code == KeyCode.RIGHT && direction != Direction.LEFT) direction = Direction.RIGHT;

            if (code == KeyCode.ENTER) restartPressed = true;
        });


    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction d) {
        direction = d;
    }

    public boolean isRestartPressed() {
        boolean pressed = restartPressed;
        restartPressed = false; // сброс после чтения
        return pressed;
    }
}
