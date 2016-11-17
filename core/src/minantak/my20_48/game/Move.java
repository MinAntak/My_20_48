package minantak.my20_48.game;

import java.util.ArrayList;

/**
 * Created by Marcin on 17.11.2016.
 */

public class Move {
    private ArrayList<Element> elements;
    private Score score;
    Move(ArrayList<Element> elements, Score score) {
        this.score = score;
        this.elements = elements;
    }

    public void moveLeft() {
        boolean toMove, isFree;
        for (int i = 0; i < 4; i++)
            for (int j = 0 ; j < 4; j++) {
                int goX = i, goY = j;
                for (int m = 0; m < elements.size(); m++) {
                    Element tmp = elements.get(m);
                    if (tmp.getx() == i && tmp.gety() == j) {
                        toMove = true;
                        isFree = true;
                        while(toMove) {
                            for (int n = 0; n < elements.size(); n++) {
                                Element tmp1 = elements.get(n);
                                if (tmp1.testPosition(goX-1, goY)) {
                                    if(tmp1.getValue() == tmp.getValue()) {
                                        elements.add(new Element(tmp.getValue()*2, elements, goX-1, goY));
                                        score.addScore(tmp.getValue()*2);
                                        if (m > n) {
                                            elements.remove(m);
                                            elements.remove(n);
                                            n--;
                                        }
                                        else {
                                            elements.remove(n);
                                            elements.remove(m);
                                            m--;
                                            //m -= 2;
                                        }
                                    }
                                    else {
                                        isFree = false;
                                    }

                                }
                            }
                            if (isFree && goX > 0) {
                                goX--;
                                //System.out.println("zwiekszam");
                            }
                            else toMove = false;
                        }
                        //System.out.println("ide");
                        tmp.putx(goX);
                        tmp.puty(goY);

                    }
                }
            }
    }
    public void moveRight() {
        boolean toMove, isFree;
        for (int i = 3; i >= 0; i--)
            for (int j = 0 ; j < 4; j++) {
                int goX = i, goY = j;
                for (int m = 0; m < elements.size(); m++) {
                    Element tmp = elements.get(m);
                    if (tmp.getx() == i && tmp.gety() == j) {
                        toMove = true;
                        isFree = true;
                        while(toMove) {
                            for (int n = 0; n < elements.size(); n++) {
                                Element tmp1 = elements.get(n);
                                if (tmp1.testPosition(goX+1, goY)) {
                                    if(tmp1.getValue() == tmp.getValue()) {
                                        elements.add(new Element(tmp.getValue()*2, elements, goX+1, goY));
                                        score.addScore(tmp.getValue()*2);
                                        if (m > n) {
                                            elements.remove(m);
                                            elements.remove(n);
                                            n--;
                                        }
                                        else {
                                            elements.remove(n);
                                            elements.remove(m);
                                            m--;
                                            //m -= 2;
                                        }
                                    }
                                    else {
                                        isFree = false;
                                    }
                                }

                            }
                            if (isFree && goX < 3) {
                                goX++;
                                //System.out.println("zwiekszam");
                            }
                            else toMove = false;
                        }
                        //System.out.println("ide");
                        tmp.putx(goX);
                        tmp.puty(goY);
                    }
                }
            }
    }
    public void moveUp() {
        boolean toMove, isFree;
        for (int i = 3; i >= 0; i--)
            for (int j = 0 ; j < 4; j++) {
                int goX = j, goY = i;
                for (int m = 0; m < elements.size(); m++) {
                    Element tmp = elements.get(m);
                    if (tmp.getx() == j && tmp.gety() == i) {
                        toMove = true;
                        isFree = true;
                        while(toMove) {
                            for (int n = 0; n < elements.size(); n++) {
                                Element tmp1 = elements.get(n);
                                if (tmp1.testPosition(goX, goY+1)) {
                                    if(tmp1.getValue() == tmp.getValue()) {
                                        elements.add(new Element(tmp.getValue()*2, elements, goX, goY+1));
                                        score.addScore(tmp.getValue()*2);
                                        if (m > n) {
                                            elements.remove(m);
                                            elements.remove(n);
                                            n--;
                                        }
                                        else {
                                            elements.remove(n);
                                            elements.remove(m);
                                            m--;
                                            //m -= 2;
                                        }
                                    }
                                    else {
                                        isFree = false;
                                    }
                                }
                            }
                            if (isFree && goY < 3) {
                                goY++;
                                //System.out.println("zwiekszam");
                            }
                            else toMove = false;
                        }
                        //System.out.println("ide");
                        tmp.putx(goX);
                        tmp.puty(goY);
                    }
                }
            }
    }
    public void moveDown() {
        boolean toMove, isFree;
        for (int i = 0; i < 4; i++)
            for (int j = 3 ; j >= 0; j--) {
                int goX = j, goY = i;
                for (int m = 0; m < elements.size(); m++) {
                    Element tmp = elements.get(m);
                    if (tmp.getx() == j && tmp.gety() == i) {
                        toMove = true;
                        isFree = true;
                        while(toMove) {
                            for (int n = 0; n < elements.size(); n++) {
                                Element tmp1 = elements.get(n);
                                if (tmp1.testPosition(goX, goY-1)) {
                                    if(tmp1.getValue() == tmp.getValue()) {
                                        elements.add(new Element(tmp.getValue()*2, elements, goX, goY-1));
                                        score.addScore(tmp.getValue()*2);
                                        if (m > n) {
                                            elements.remove(m);
                                            elements.remove(n);
                                            n--;
                                        }
                                        else {
                                            elements.remove(n);
                                            elements.remove(m);
                                            m--;
                                            //m -= 2;
                                        }
                                    }
                                    else {
                                        isFree = false;
                                    }
                                }
                            }
                            if (isFree && goY > 0) {
                                goY--;
                                //System.out.println("zwiekszam");
                            }
                            else toMove = false;
                        }
                        //System.out.println("ide");
                        tmp.putx(goX);
                        tmp.puty(goY);
                    }
                }
            }
    }
}
