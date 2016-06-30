package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;

import java.util.ArrayList;

/**
 * Class for one-decker ship.
 */
public class Submarine extends Ship {

    private final static int MAX_NUM_SUB = 4;

    public Submarine(Cell pos) {
        super();
        init();
        setLocation(pos);
    }

    private void init() {
        shipSize = 1;
    }

}
