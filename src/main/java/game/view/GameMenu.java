package game.view;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameMenu {
    private final StackPane root;
    private final Button startButton;

    public GameMenu(double width, double height) {
        root = new StackPane();
        root.setPrefSize(width, height);
        root.setStyle("-fx-background-color: black;");

        VBox box = new VBox(20);
        box.setStyle("-fx-alignment: center;");

        startButton = new Button("START");
        startButton.setFont(Font.font(32));
        startButton.setTextFill(Color.WHITE);
        startButton.setStyle("-fx-background-color: green;");

        box.getChildren().add(startButton);
        root.getChildren().add(box);
    }

    public StackPane getRoot() {
        return root;
    }

    public void setOnStart(Runnable action) {
        startButton.setOnAction(e -> action.run());
    }
}
