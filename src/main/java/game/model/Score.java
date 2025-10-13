package game.model;

public class Score {
    private  int score = 0;
    private int highScore = 0;

    public void reset() {
        score = 0;
    }

    public void addPoint() {
        score++;
        if (score > highScore) highScore = score;
    }

    public  int getScore() {
        return score;
    }

    public  int getHighScore() {
        return highScore;
    }
}
