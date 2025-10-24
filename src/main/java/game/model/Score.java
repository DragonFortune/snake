package game.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Score {
    private static final Logger LOGGER = Logger.getLogger(Score.class.getName());
    private static final String FILE_NAME = "score.dat";
    private int score = 0;
    private int highScore;

    public Score() {
        this.highScore = loadHighScore();
    }

    public void addPoint(int value) {
        score += value;
    }

    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void reset() {
        score = 0;
    }

    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            LOGGER.log(Level.WARNING, "Не удалось загрузить рекорд", e);
            return 0;
        }
    }

    private void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при сохранении рекорда", e);
        }
    }
}
