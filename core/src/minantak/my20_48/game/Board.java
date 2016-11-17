package minantak.my20_48.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Marcin on 17.11.2016.
 */

public class Board {
    private BitmapFont fontScore;
    private float screenHeight, screenWidth;
    //float x = (float) 0.8;
    private Texture blank, field, restart, score;
    private SpriteBatch batch;
    private Score scoreN;
    private ArrayList<Element> elements;

    Board(SpriteBatch batch,ArrayList<Element> elements, Score scoreN) {
        this.batch = batch;
        this.elements = elements;
        this.scoreN = scoreN;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        blank = new Texture("blank.png");
        field = new Texture("field.png");
        restart = new Texture("restart.png");
        score = new Texture("score.png");
        fontScore = new BitmapFont(Gdx.files.internal("segoe.fnt"));
    }

    public void draw() {
        batch.draw(field, (float) (screenWidth*0.05), (float) (screenWidth*0.3), (float) (screenWidth*0.9), (float) (screenWidth*0.9));
        for (int i = 0; i < 4 ; i++)
            for (int j = 0; j < 4 ; j++) {
                batch.draw(blank, (float) (screenWidth*0.05+(screenWidth*0.02*(i+1))+(screenWidth*0.2*i)), (float) (screenWidth*0.3+(screenWidth*0.02*(j+1))+(screenWidth*0.2*j)), (float) (screenWidth*0.2), (float) (screenWidth*0.2));
            }

        for (int m = 0 ; m < elements.size(); m++) {
            Element tmp = elements.get(m);
            int x = tmp.getx();
            int y = tmp.gety();
            batch.draw(tmp.getImg(), (float) (screenWidth*0.05+(screenWidth*0.02*(x+1))+(screenWidth*0.2*x)),
                    (float) (screenWidth*0.3+(screenWidth*0.02*(y+1))+(screenWidth*0.2*y)),
                    (float) (screenWidth*0.2), (float) (screenWidth*0.2));
        }
        batch.draw(restart, (float) (screenWidth*0.05), (float) (screenWidth*0.05),
                (float) (screenWidth*0.3), (float) (screenWidth*0.15));
        batch.draw(score, (float) (screenWidth*0.65), (float) (screenWidth*0.05),
                (float) (screenWidth*0.3), (float) (screenWidth*0.15));
        fontScore.draw(batch, Integer.toString(scoreN.getScore()),
                (float) (screenWidth*0.66), (float) (screenWidth*0.12));

    }

    public void showOver() {
        fontScore.draw(batch, "GAME OVER!", screenWidth/4, screenHeight/2);
    }
}
