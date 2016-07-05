package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for ships.
 * Every kind of ship extends from these.
 */
public abstract class Ship {

    protected final static int BOARDERS_SIZE = 3;

    protected int shipSize;
    protected boolean horizontal;

    private int boardersWidth;
    private Cell[] location;
    private List<Cell> boarders;
    private boolean dead = false;

    protected void setLocation(Cell pos) {
        location = new Cell[shipSize];
        for (int i = 0; i < shipSize; i++) {
            if (horizontal)
                location[i] = new Cell(pos.getxPos()+i, pos.getyPos());
            else
                location[i] = new Cell(pos.getxPos(), pos.getyPos()+i);
        }
        setBoarders();
    }

    private void setBoarders() {
        boardersWidth = shipSize + 2;
        boarders = new ArrayList<>();
        int x = location[0].getxPos()-1;
        int y = location[0].getyPos()-1;
        if (horizontal)
            horzBoarders(x,y);
        else
            vertBoarders(x,y);
    }

    private void vertBoarders(int x, int y) {
        Cell temp;
        for (int i = y; i < y + boardersWidth; i++) {
            for (int j = x; j < x + BOARDERS_SIZE ; j++) {
                temp = new Cell(j, i);
                for (int z = 0 ; z < location.length; z++)
                    if (!temp.equals(getLocation()[z]))
                        boarders.add(temp);
            }
        }
    }

    private void horzBoarders(int x, int y) {
        Cell temp;
        for (int i = y; i < y + BOARDERS_SIZE; i++) {
            for (int j = x; j < x + boardersWidth; j++) {
                temp = new Cell(j, i);
                for (int z = 0 ; z < location.length; z++)
                    if (!temp.equals(getLocation()[z]))
                        boarders.add(temp);
            }
        }
    }

    public void hit(Cell c) {
        for (int i = 0; i < location.length; i++)
            if (c.equals(location[i]))
                location[i].setHit(true);
    }

    public void checkIfDead(){
        for (int i = 0; i < location.length; i++)
            if (!location[i].isHit()) {
                return;
            }
        setDead(true);
        fillBoarders();
    }

    private void fillBoarders() {
        for (int i = 0; i < getBoarders().size(); i++)
            boarders.get(i).setHit(true);
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Cell[] getLocation() {
        return location;
    }

    public List<Cell> getBoarders() {
        return boarders;
    }

    public boolean isDead() {
        return dead;
    }
}
