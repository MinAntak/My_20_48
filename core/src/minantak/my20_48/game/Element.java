package minantak.my20_48.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marcin on 17.11.2016.
 */

public class Element {
    private int value;
    private int x, y;
    private boolean isNew; //flag if this element was created in this turn
    private Texture img;
    private ArrayList<Element> elements;


    Element(int value, ArrayList<Element> elements ) {
        this.value = value;
        this.elements = elements;
        this.isNew = false;
        initImage();
        newRandom();
    }
    Element(int value, ArrayList<Element> elements, int x, int y) {
        this.value = value;
        this.elements = elements;
        this.x=x;
        this.y=y;
        this.isNew = true;
        initImage();
    }
    private void initImage() {
        switch(value) {
            case 2:
                img = new Texture("0002.png");
                break;
            case 4:
                img = new Texture("0004.png");
                break;
            case 8:
                img = new Texture("0008.png");
                break;
            case 16:
                img = new Texture("0016.png");
                break;
            case 32:
                img = new Texture("0032.png");
                break;
            case 64:
                img = new Texture("0064.png");
                break;
            case 128:
                img = new Texture("0128.png");
                break;
            case 256:
                img = new Texture("0256.png");
                break;
            case 512:
                img = new Texture("0512.png");
                break;
            case 1024:
                img = new Texture("1024.png");
                break;
            case 2048:
                img = new Texture("2048.png");
                break;
            default:
                break;
        }
    }

    public Texture getImg() {
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

    public boolean testPosition(int testedX, int testedY) {
        if (testedX == x && testedY == y) return true; //if this position is occupied
        else return false; //if not
    }

    public int getx() { return x; }
    public int gety() { return y; }
    public int getValue() {return value; }
    public void putx(int x) { this.x = x; }
    public void puty(int y) { this.y = y; }
    public void resetNew() {isNew = false; }
    public void dispose() {
        img.dispose();
    }
    public boolean getNew() {return isNew; }
}
