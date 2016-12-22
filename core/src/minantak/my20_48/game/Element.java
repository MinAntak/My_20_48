package minantak.my20_48.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marcin on 17.11.2016.
 */

public class Element {
    private int value;
    private int id;
    private int previousX, previousY;
    private int x, y;
    private boolean isNew; //flag if this element was created in this turn
    private Texture img;
    private ArrayList<Element> elements;


    Element(int value, int elementID, ArrayList<Element> elements ) {
        this.value = value;
        this.elements = elements;
        this.isNew = false;
        id = elementID;
        initImage();
        newRandom();
        System.out.println(id);
    }
    Element(int value, ArrayList<Element> elements, int x, int y, int elementId) {
        this.value = value;
        this.elements = elements;
        this.x=x;
        this.y=y;
        this.isNew = true;
        id = elementId;
        initImage();
        System.out.println(id);
    }
    private void initImage() {
        switch(value) {
            case 2:
                img = new Texture(Gdx.files.internal("images/0002.png"));
                break;
            case 4:
                img = new Texture(Gdx.files.internal("images/0004.png"));
                break;
            case 8:
                img = new Texture(Gdx.files.internal("images/0008.png"));
                break;
            case 16:
                img = new Texture(Gdx.files.internal("images/0016.png"));
                break;
            case 32:
                img = new Texture(Gdx.files.internal("images/0032.png"));
                break;
            case 64:
                img = new Texture(Gdx.files.internal("images/0064.png"));
                break;
            case 128:
                img = new Texture(Gdx.files.internal("images/0128.png"));
                break;
            case 256:
                img = new Texture(Gdx.files.internal("images/0256.png"));
                break;
            case 512:
                img = new Texture(Gdx.files.internal("images/0512.png"));
                break;
            case 1024:
                img = new Texture(Gdx.files.internal("images/1024.png"));
                break;
            case 2048:
                img = new Texture(Gdx.files.internal("images/2048.png"));
                break;
            default:
                break;
        }
    }

    Texture getImg() {
        return img;
    }

    private void newRandom() {
        int randx, randy;
        boolean isOccupied = true;
        Element tmp;

        Random rand = new Random();
        randx = rand.nextInt(4);
        randy = rand.nextInt(4);
        int fieldsChecked = 0;
        int tempx = randx;
        int tempy = randy;
        while(isOccupied) {
            isOccupied = false;
            for (int i = 0; i < elements.size(); i++) {
                tmp = elements.get(i);
                if (tmp.testPosition(tempx, tempy)) {
                    isOccupied = true;
                }
            }
            if (isOccupied) {
                fieldsChecked++;
                if(tempx < 3) tempx++;
                else {
                    if (tempy < 3) tempy++;
                    else tempy = 0;
                    tempx = 0;
                }
            }
            if (fieldsChecked == 15) {
                isOccupied = false; //just to quit while
            }
        }
        if (fieldsChecked == 15) {
            System.out.println("Game Over!");
        }
        x = tempx;
        y = tempy;
    }

    boolean testPosition(int testedX, int testedY) {
        //if this position is occupied
//if not
        return testedX == x && testedY == y;
    }

    int getx() { return x; }
    int gety() { return y; }
    int getValue() {return value; }
    void putx(int x) { this.x = x; }
    void puty(int y) { this.y = y; }
    void resetNew() {isNew = false; }
    void dispose() {
        img.dispose();
    }
    boolean getNew() { return isNew; }

    void setPrevious() {
        previousX = x;
        previousY = y;
    }
}
