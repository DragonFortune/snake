package game.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BonusFoodTest {
    private Snake snake;
    private BonusFood bonusFood;

    private static final long DURATION = 500;
    private static final long COOLDOWN = 300;

    @BeforeEach
    void setUp() {
        snake = new Snake();
        bonusFood = new BonusFood(DURATION, COOLDOWN);
    }

    @Test
    @DisplayName("Бонус появляется на 4 клетки")
    void testSpawnCreates4Cells() {
        bonusFood.spawn(snake);

        List<Point> cells = bonusFood.getOccupiedCells();
        assertEquals(4, cells.size());

        Point head = bonusFood.getPosition();
        assertTrue(cells.contains(head));
        assertTrue(cells.contains(new Point(head.x() + 1, head.y())));
        assertTrue(cells.contains(new Point(head.x(), head.y() + 1)));
        assertTrue(cells.contains(new Point(head.x() + 1, head.y() + 1)));
    }

    @Test
    @DisplayName("Бонус активен после спавна")
    void testActiveAfterSpawn() {
        bonusFood.spawn(snake);
        assertTrue(bonusFood.isActive());
    }

    @Test
    @DisplayName("Бонус деактивируется после duration")
    void testDeactivateAfterDuration() throws InterruptedException {
        bonusFood.spawn(snake);
        Thread.sleep(DURATION + 50);
        bonusFood.update(snake);

        assertFalse(bonusFood.isActive());
        assertTrue(bonusFood.getOccupiedCells().isEmpty());
        assertNull(bonusFood.getPosition());
    }

    @Test
    @DisplayName("Бонус появляется снова после spawnCooldown")
    void testRespawnAfterCooldown() throws InterruptedException {
        bonusFood.spawn(snake);
        bonusFood.deactivate();

        Thread.sleep(COOLDOWN +50);
        bonusFood.update(snake);

        assertTrue(bonusFood.isActive());
        assertEquals(4, bonusFood.getOccupiedCells().size());
    }

    @Test
    @DisplayName("isExpired возвращает true только если время жизни прошло")
    void testIsExpired() throws InterruptedException {
        bonusFood.spawn(snake);
        assertFalse(bonusFood.isExpired());

        Thread.sleep(DURATION + 50);
        assertTrue(bonusFood.isExpired());
    }
}
