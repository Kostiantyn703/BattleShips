package com.hundret.battleships.Model.Entity.Ships;

import com.hundret.battleships.Model.Entity.Cell;

/**
 * Class for three-decker ship.
 */
public class Cruiser extends Ship {

    private final static int MAX_NUM_CRSR = 2;

    public Cruiser(Cell pos){
        super();
        init();
        setLocation(pos);
    }

    private void init() {
        shipSize = 3;
    }

    public static int getMaxNumCrsr() {
        return MAX_NUM_CRSR;
    }

}
