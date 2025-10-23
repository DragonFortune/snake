package game.view.renderers;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;

public class RendererManager {
    private static final Logger LOGGER = Logger.getLogger(RendererManager.class.getName());
    private final Map<Integer, List<Renderer>> layers = new TreeMap<>();
    private final Set<Integer> disabledLayers = new HashSet<>();

    /**
     * Добавляет новый рендерер в указанный слой.
     *
     * @param renderer экземпляр рендера
     * @param layer номер слоя
     */
    public void add(Renderer renderer, int layer) {
        layers.computeIfAbsent(layer, k -> new ArrayList<>()).add(renderer);
    }

    public void disableLayers(int layer) {
        disabledLayers.add(layer);
    }

    public void enableLayer(int layer) {
        disabledLayers.remove(layer);
    }

    public void renderAll(GraphicsContext gc) {
        for (Map.Entry<Integer, List<Renderer>> entry : layers.entrySet()) {
            int layer = entry.getKey();

            if (disabledLayers.contains(layer)) continue;

            for (Renderer r : entry.getValue()) {
                try {
                    r.render(gc);
                } catch (Throwable e) {
                    LOGGER.log(Level.WARNING, "Render failed for " + r);
                }
            }
        }
    }
}
