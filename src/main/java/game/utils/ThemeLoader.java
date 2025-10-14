package game.utils;

import game.model.GameTheme;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ThemeLoader {
    public static GameTheme load(String name) {
        return switch (name.toLowerCase()) {
            default -> new GameTheme(
                    new Image("/themes/classic/snake_head.png"),
                    new Image("/themes/classic/snake_body.png"),
                    new Image("/themes/classic/food.png"),
                    new Image("/themes/classic/snake_head.png"),
                    Color.web("#303030")
            );
        };
    }
}
