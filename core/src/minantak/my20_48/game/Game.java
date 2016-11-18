package minantak.my20_48.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
    //Board board;
    float screenHeight, screenWidth;
    boolean isOver;
    Move move;
    int turn;
    float timer, savedTime;
    boolean czymozna;
    private Texture blank, field, restart, scoreN;
    private SpriteBatch batch;
    private BitmapFont fontScore;
    private Score score;
    private ArrayList<Element> elements;
	@Override
	public void create () {
        blank = new Texture("blank.png");
        field = new Texture("field.png");
        restart = new Texture("restart.png");
        scoreN = new Texture("score.png");
        fontScore = new BitmapFont(Gdx.files.internal("segoe.fnt"));
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        score = new Score();
		batch = new SpriteBatch();
        elements = new ArrayList<Element>();
        //board = new Board(batch, elements, score);
        move = new Move(elements, score);
		startGame();

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                if(!isOver) {
                    move.moveUp();
                    elements.add(new Element(2, elements));
                }

            }

            @Override
            public void onRight() {
                if(!isOver) {
                    move.moveRight();
                    elements.add(new Element(2, elements));
                }
            }

            @Override
            public void onLeft() {
                if(!isOver) {
                    move.moveLeft();
                    elements.add(new Element(2, elements));
                }
            }

            @Override
            public void onDown() {
                if(!isOver) {
                    move.moveDown();
                    elements.add(new Element(2, elements));
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
        batch.draw(scoreN, (float) (screenWidth*0.65), (float) (screenWidth*0.05),
                (float) (screenWidth*0.3), (float) (screenWidth*0.15));
        fontScore.draw(batch, Integer.toString(score.getScore()),
                (float) (screenWidth*0.66), (float) (screenWidth*0.12));
		update();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
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
                elements.add(new Element(2, elements));
                savedTime = timer;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move.moveDown();
                elements.add(new Element(2, elements));
                savedTime = timer;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move.moveLeft();
                elements.add(new Element(2, elements));
                savedTime = timer;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move.moveRight();
                elements.add(new Element(2, elements));
                savedTime = timer;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.E)) {
                elements.add(new Element(2, elements));
                savedTime = timer;
            }

        }
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            //System.out.println(x + " " + y);

            if (x > (screenWidth*0.05) && y > (screenWidth*0.05) && x < (screenWidth*0.35) && y < (screenWidth*0.2)) {
                startGame();
            }

        }





	}

    public void showOver() {
        fontScore.draw(batch, "GAME OVER!", screenWidth/4, screenHeight/2);
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
