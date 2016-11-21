package minantak.my20_48.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


import java.util.ArrayList;
import java.util.Random;

public class Game extends ApplicationAdapter {
    float screenHeight, screenWidth;
    boolean isOver;
    Element tmp;
    Move move;
    int turn;
    float timer, savedTime;
    private Texture blank, field, restart, scoreN, overTexture;
    private SpriteBatch batch;
    private BitmapFont fontScore;
    private Score score;
    private ArrayList<Element> elements;
    private String version;

	@Override
	public void create () {
        blank = new Texture("images/blank.png");
        field = new Texture("images/field.png");
        restart = new Texture("images/restart.png");
        scoreN = new Texture("images/score.png");
        overTexture = new Texture("images/over.png");
        fontScore = new BitmapFont(Gdx.files.internal("images/segoe.fnt"));
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        score = new Score();
		batch = new SpriteBatch();
        elements = new ArrayList<Element>();
        //board = new Board(batch, elements, score);
        move = new Move(elements, score);
        version = "v. 0.0.22 alfa";
		startGame();

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector
                (new SimpleDirectionGestureDetector.DirectionListener() {


            @Override
            public void onUp() {
                if(timer-savedTime > 0.3 && !isOver) {
                    move.moveUp();
                    increaseTurn();
                }

            }

            @Override
            public void onRight() {
                if(timer-savedTime > 0.3 && !isOver) {
                    move.moveRight();
                    increaseTurn();
                }
            }

            @Override
            public void onLeft() {
                if(timer-savedTime > 0.3 && !isOver) {
                    move.moveLeft();
                    increaseTurn();
                }
            }

            @Override
            public void onDown() {
                if(timer-savedTime > 0.3 && !isOver) {
                    move.moveDown();
                    increaseTurn();
                }
            }
        }));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//board.draw();
        batch.draw(field, (float) (screenWidth*0.05), (float) (screenWidth*0.3),
                (float) (screenWidth*0.9), (float) (screenWidth*0.9));
        for (int i = 0; i < 4 ; i++)
            for (int j = 0; j < 4 ; j++) {
                batch.draw(blank, (float) (screenWidth*0.05+(screenWidth*0.02*(i+1))+(screenWidth*0.2*i)),
                        (float) (screenWidth*0.3+(screenWidth*0.02*(j+1))+(screenWidth*0.2*j)),
                        (float) (screenWidth*0.2), (float) (screenWidth*0.2));
            }

        for (int m = 0 ; m < elements.size(); m++) {
            tmp = elements.get(m);
            int x = tmp.getx();
            int y = tmp.gety();
            batch.draw(tmp.getImg(), (float) (screenWidth*0.05+(screenWidth*0.02*(x+1))+(screenWidth*0.2*x)),
                    (float) (screenWidth*0.3+(screenWidth*0.02*(y+1))+(screenWidth*0.2*y)),
                    (float) (screenWidth*0.2), (float) (screenWidth*0.2));
        }
        fontScore.setColor(Color.FIREBRICK);
        fontScore.draw(batch, version, (float) (screenWidth*0.05),(float) (screenHeight*0.98));

        fontScore.setColor(Color.WHITE);
        batch.draw(restart, (float) (screenWidth*0.05), (float) (screenWidth*0.05),
                (float) (screenWidth*0.3), (float) (screenWidth*0.15));
        batch.draw(scoreN, (float) (screenWidth*0.65), (float) (screenWidth*0.05),
                (float) (screenWidth*0.3), (float) (screenWidth*0.15));
        fontScore.draw(batch, Integer.toString(score.getScore()),
                (float) (screenWidth*0.66), (float) (screenWidth*0.12));
		update();
		batch.end();
	}
	
	@Override
	public void dispose () {
        while (elements.size() > 0) {
            elements.get(0).dispose();
            elements.remove(0);
        }
        blank.dispose();
        field.dispose();
        fontScore.dispose();
        restart.dispose();
        scoreN.dispose();
        batch.dispose();
        overTexture.dispose();
	}


	public void update() {
        if (elements.size() > 16)
            isOver = true;

        if (isOver)
            showOver();

        timer += Gdx.graphics.getDeltaTime();

        if(timer-savedTime > 0.3 & !isOver) {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                move.moveUp();
                increaseTurn();
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move.moveDown();
                increaseTurn();
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move.moveLeft();
                increaseTurn();
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move.moveRight();

                increaseTurn();
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.E)) {
                elements.add(new Element(2, elements));
            }

        }
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            //System.out.println(x + " " + y);

            if (x > (screenWidth*0.05) && y > (screenWidth*0.05) && x < (screenWidth*0.35)
                    && y < (screenWidth*0.2)) {
                startGame();
            }

        }





	}

    public void increaseTurn() {
        turn++;
        savedTime = timer;
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).resetNew();
        }

        Random rand = new Random();
        int randvalue = rand.nextInt(10);
        if (randvalue == 8) {
            elements.add(new Element(4, elements));
        }
        else
            elements.add(new Element(2, elements));
    }

    public void showOver() {


        batch.draw(overTexture, screenWidth/4, (screenHeight/2)-50, screenWidth/2, screenWidth/4);
        fontScore.draw(batch, "Score: " + score.getScore(), screenWidth/4, screenHeight/2);
    }

	public void startGame() {
        isOver =false;
        turn = 0;
        while (elements.size() > 0) {
            elements.get(0).dispose();
            elements.remove(0);

        }
        score.resetScore();
		elements.add(new Element(2, elements));
        elements.add(new Element(4, elements));
	}


}
