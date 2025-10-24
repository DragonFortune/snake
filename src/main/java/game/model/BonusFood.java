package game.model;

public class BonusFood extends Food {
    private final long duration;
    private long spawnTime;

    public BonusFood(long duration) {
        this.duration = duration;
    }

    public void spawn(Snake snake) {
        super.spawn(snake);
        this.spawnTime = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - spawnTime > duration;
    }
}
