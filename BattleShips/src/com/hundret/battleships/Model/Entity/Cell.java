package com.hundret.battleships.Model.Entity;

/**
 * Class represents coordinates and state of single box on the field.
 * Also used for ships.
 */
public class Cell {

    Cell temp;

    private final static int SIZE = 35;

    private int xPos, yPos;
    private boolean isHit;

    public Cell(int x, int y) {
        xPos = x;
        yPos = y;
        setHit(false);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public static int getSIZE() {
        return SIZE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        temp = (Cell)obj;
        if (temp.getxPos() == this.getxPos() && temp.getyPos() == this.getyPos())
            return true;
        else
            return false;
    }

}

