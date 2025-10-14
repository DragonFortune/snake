package game.view.renderers;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class RendererManager {
    private final List<Renderer> renderers = new ArrayList<>();

    public void add(Renderer renderer) {
        renderers.add(renderer);
    }

    public void renderAll (GraphicsContext gc) {
        for (Renderer renderer : renderers) {
            renderer.render(gc);
        }
    }
}
