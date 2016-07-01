package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;

/**
 * Class for four decker ship.
 */
public class Battleship extends Ship {

    private final static int MAX_NUM_BTL = 1;

    public Battleship(Cell pos, boolean dir) {
        super();
        init(dir);
        setLocation(pos);
    }

    private void init(boolean dir) {
        shipSize = 4;
        horizontal = dir;
    }

    public static int getMaxNumBtl() {
        return MAX_NUM_BTL;
    }
}
