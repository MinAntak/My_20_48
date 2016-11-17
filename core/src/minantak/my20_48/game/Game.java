package minantak.my20_48.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
    Board board;
    Score score;
    float screenHeight, screenWidth;
	SpriteBatch batch;
    private ArrayList<Element> elements;
    Move move;
    int turn;
    float timer, savedTime;
    boolean czymozna;
	@Override
	public void create () {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        score = new Score();
		batch = new SpriteBatch();
        elements = new ArrayList<Element>();
        board = new Board(batch, elements, score);
        move = new Move(elements, score);
		startGame();

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                move.moveUp();
                elements.add(new Element(2, elements));
            }

            @Override
            public void onRight() {
                move.moveRight();
                elements.add(new Element(2, elements));
            }

            @Override
            public void onLeft() {
                move.moveLeft();
                elements.add(new Element(2, elements));
            }

            @Override
            public void onDown() {
                move.moveDown();
                elements.add(new Element(2, elements));
            }
        }));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		board.draw();
		update();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


	public void update() {
        timer += Gdx.graphics.getDeltaTime();

        if(timer-savedTime > 0.2) {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                move.moveUp();
                elements.add(new Element(2, elements));
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move.moveDown();
                elements.add(new Element(2, elements));
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move.moveLeft();
                elements.add(new Element(2, elements));
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move.moveRight();
                elements.add(new Element(2, elements));
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.E)) {
                elements.add(new Element(2, elements));
            }
            else if (Gdx.input.isTouched()) {
                int x = Gdx.input.getX();
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();
                //System.out.println(x + " " + y);

                if (x > (screenWidth*0.05) && y > (screenWidth*0.05) && x < (screenWidth*0.35) && y < (screenWidth*0.2)) {
                    startGame();
                }

            }


        }

		//batch.draw(element.getImg(), 0, 0);





	}

	public void startGame() {
        turn = 0;
        while (elements.size() > 0) {
            elements.remove(0);
        }
        score.resetScore();
		elements.add(new Element(2, elements));
        elements.add(new Element(4, elements));
	}


}
