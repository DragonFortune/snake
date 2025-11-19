package game.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SnakeTest {

    private Snake snake;
    private Food food;

    @BeforeEach
    @DisplayName("Подготовка новой змеи и еды")
    void setUp() {
        snake = new Snake();
        food = new Food();
    }

    @Test
    @DisplayName("Изначально змея имеет один сегмент в (10,10)")
    void initialSnakePosition() {
        assertEquals(1, snake.getBody().size());
        assertEquals(new Point(10, 10), snake.getHead());
    }

    @Test
    @DisplayName("Змея движется вперёд без роста")
    void snakeMovesForward() {
        snake.move(Direction.RIGHT, food);

        assertEquals(new Point(11, 10), snake.getHead());
        assertEquals(1, snake.getBody().size());
    }

    @Test
    @DisplayName("Змея не растёт если не съела еду и growSegments = 0")
    void snakeDoesNotGrow() {
        food.setPosition(new Point(999, 999));

        snake.move(Direction.RIGHT, food);

        assertEquals(1, snake.getBody().size());
    }

    @Test
    @DisplayName("Змея растёт на 1 сегмент после grow(1)")
    void snakeGrowsOneSegment() {
        snake.grow(1);

        snake.move(Direction.RIGHT, food);

        assertEquals(2, snake.getBody().size());
        assertEquals(new Point(11, 10), snake.getHead());
    }

    @Test
    @DisplayName("Змея растёт на несколько сегментов (3)")
    void snakeGrowsMultipleSegments() {
        snake.grow(3);

        snake.move(Direction.RIGHT, food);
        snake.move(Direction.RIGHT, food);
        snake.move(Direction.RIGHT, food);

        assertEquals(4, snake.getBody().size());
    }

    @Test
    @DisplayName("Если есть growSegments, хвост НЕ удаляется")
    void snakeDoesNotRemoveTailWhenGrowing() {
        snake.grow(1);

        snake.move(Direction.RIGHT, food);

        assertEquals(2, snake.getBody().size());
    }

    @Test
    @DisplayName("Змея не может повернуть назад")
    void cannotTurnOpposite() {
        assertEquals(Direction.RIGHT, snake.getDirection());

        snake.move(Direction.LEFT, food);

        assertEquals(Direction.RIGHT, snake.getDirection());
    }

    @Test
    @DisplayName("Проверка столкновения со стеной")
    void wallCollision() {
        Point wallPoint = new Point(-1, 10);
        assertTrue(snake.checkCollision(wallPoint));
    }

    @Test
    @DisplayName("Проверка столкновения с собой")
    void selfCollision() {
        snake.getBody().add(new Point(11, 10));
        snake.getBody().add(new Point(12, 10));

        assertTrue(snake.checkCollision(new Point(11, 10)));
    }
}
