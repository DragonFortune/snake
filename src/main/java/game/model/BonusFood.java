package game.model;

import java.util.ArrayList;
import java.util.List;

public class BonusFood extends Food {
    private final long duration;
    private final long spawnCooldown;
    private long spawnTime;
    private long lastRespawnTime;

    private boolean active = false;
    private List<Point> occupiedCells = List.of();

    public BonusFood(long duration, long spawnCooldown) {
        this.duration = duration;
        this.spawnCooldown = spawnCooldown;
    }

    public void spawn(Snake snake) {
        super.spawn(snake);

        Point base = getPosition();
        spawnTime = System.currentTimeMillis();
        active = true;

        occupiedCells = List.of(base,
                new Point(base.x() + 1, base.y()),
                new Point(base.x(), base.y() + 1),
                new Point(base.x() + 1, base.y() + 1)
        );
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
        lastRespawnTime = System.currentTimeMillis();
        occupiedCells = List.of();
        setPosition(null);
    }

    public void update(Snake snake) {
        long now = System.currentTimeMillis();

        if (active) {
            if (now - spawnTime >= duration) {
                deactivate();
            }
            return;
        }

        if (now - lastRespawnTime >= spawnCooldown) {
            spawn(snake);
        }
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - spawnTime > duration;
    }

    public List<Point> getOccupiedCells() {
        return occupiedCells;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
