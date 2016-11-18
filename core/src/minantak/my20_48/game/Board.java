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



    }


}
