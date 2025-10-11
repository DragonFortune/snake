package game;

import game.controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        new GameController(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
