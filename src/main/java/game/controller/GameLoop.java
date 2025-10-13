package game.controller;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private final Runnable updateLogic;

    public GameLoop(Runnable updateLogic) {
        this.updateLogic = updateLogic;
    }

    private long lastUpdate = 0;

    @Override
    public void handle(long now) {
        if (lastUpdate == 0) lastUpdate = now;
        if (now - lastUpdate >= 200_000_000L) {
            updateLogic.run();
            lastUpdate = now;
        }
    }

    public void resetTimer() {
        lastUpdate = 0;
    }
}
