package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;

/**
 * Class for one-decker ship.
 */
public class Submarine extends Ship {

    //unused variable
    private final static int MAX_NUM_SUB = 4;

    public Submarine(Cell pos, boolean dir) {
        super();
        init(dir);
        setLocation(pos);
    }

    private void init(boolean dir) {
        shipSize = 1;
        horizontal = dir;
    }

}
