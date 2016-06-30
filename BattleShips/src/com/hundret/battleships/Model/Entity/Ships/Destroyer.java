package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;

/**
 * Class for two-decker ship.
 */
public class Destroyer extends Ship{

    private final static int MAX_NUM_DSTR = 3;

    public Destroyer(Cell pos) {
        super();
        init();
        setLocation(pos);
    }

    private void init() {
        shipSize = 2;
    }

    public static int getMaxNumDstr() {
        return MAX_NUM_DSTR;
    }

}
