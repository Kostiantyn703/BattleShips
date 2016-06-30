package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;
import com.hundret.battleships.Model.Entity.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class for ships.
 * Every kind of ship extends from these.
 */
public abstract class Ship {

    protected final static int BOARDERS_SIZE = 3;

    protected int shipSize;

    private int boardersWidth;
    private Cell[] location;
    private List<Cell> boarders;

    private boolean dead = false;

    private Direction direction;

    protected void setLocation(Cell pos) {
        location = new Cell[shipSize];
        for (int i = 0; i < shipSize; i++) {
            location[i] = new Cell(pos.getxPos()+i, pos.getyPos());
//            else if (direction == VERT) pos.setyPos(pos.getyPos()+1);
        }
        setBoarders();
    }

    private void setBoarders() {
        boardersWidth = shipSize + 2;
        boarders = new ArrayList<>();
        Cell temp;
        int x = location[0].getxPos()-1;
        int y = location[0].getyPos()-1;
        for (int i = y; i < y + BOARDERS_SIZE; i++) {
            for (int j = x; j < x + boardersWidth; j++) {
                temp = new Cell(j, i);
                boarders.add(temp);
            }
        }
    }

    public void hit(Cell c) {
        for (int i = 0; i < location.length; i++)
            if (c.equals(location[i]))
                location[i].setHit(true);
    }

    public boolean isDead(){
        for (int i = 0; i < location.length; i++)
            if (!location[i].isHit()) return false;
        fillBoarders();
        return true;
    }

    private void fillBoarders() {
        for (int i = 0; i < getBoarders().size(); i++)
            boarders.get(i).setHit(true);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "location=" + Arrays.toString(location) +
                '}';
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

}
