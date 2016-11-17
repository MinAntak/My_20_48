package minantak.my20_48.game;

/**
 * Created by Marcin on 17.11.2016.
 */

public class Score {
    private int score;

    Score() {
        score = 0;
    }

    public void addScore(int add) {
        score += add;
    }

    public int getScore() {
        return score;
    }
    public void resetScore() {
        score = 0;
    }
}
