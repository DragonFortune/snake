package game.controller;

public class GameLoopManager {
    private GameLoop loop;

    public void start (Runnable update) {
        stop();
        loop = new GameLoop(update);
        loop.start();
    }

    public void stop() {
        if(loop != null) {
            loop.stop();
            loop = null;
        }
    }
}
