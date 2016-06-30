package com.hundret.battleships.Model.Entity;

import com.hundret.battleships.Model.Entity.Ships.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents board, all ships located here
 */
public class Board {

    private final static int MAX_SHIPS_ON_BOARD = 10;
    private final static int SIDE_SIZE = 10;
    private final static int BOX_SIZE = Cell.getSIZE() * SIDE_SIZE;

    private List<Cell> field;
    private List<Ship> ships;

    private boolean shipsReady;

    public Board() {
        init();
    }

    public boolean addShip(Cell pos) {
        Ship tempShip = chooseShipType(pos);
        //check if ship out of boarders
        for (int i = 0; i < tempShip.getLocation().length; i++) {
            if (tempShip.getLocation()[i].getxPos() >= getSideSize())
                return false;
        }
        //check if ship sets into filled cell
        for (int i = 0; i < getShips().size(); i++) {
            for (int j = 0; j < getShips().get(i).getLocation().length; j++) {
                for (int x = 0; x < tempShip.getLocation().length; x++) {
                    if (tempShip.getLocation()[x].equals(getShips().get(i).getLocation()[j]))
                        return false;
                }
            }
            for (int j = 0; j < getShips().get(i).getBoarders().size(); j++) {
                for (int x = 0; x < tempShip.getLocation().length; x++)
                    if (tempShip.getLocation()[x].equals(getShips().get(i).getBoarders().get(j)))
                        return false;
            }
        }
        ships.add(tempShip);
        if (checkMaxOfShips()) shipsReady = true;
        return true;
    }

    public void randomShipSet() {
        int xTemp, yTemp;
        Cell tempVal;
        while (!checkMaxOfShips()) {
            xTemp = (int)(Math.random() * SIDE_SIZE);
            yTemp = (int)(Math.random() * SIDE_SIZE);
            tempVal = new Cell(xTemp, yTemp);
            addShip(tempVal);
        }
    }

    private Ship chooseShipType(Cell c) {
        if (ships.size() < Battleship.getMaxNumBtl())
            return new Battleship(c);
        else if (ships.size() >= Battleship.getMaxNumBtl() &&
                    ships.size() < Battleship.getMaxNumBtl() + Cruiser.getMaxNumCrsr())
                        return new Cruiser(c);
        else if (ships.size() >= Battleship.getMaxNumBtl() + Cruiser.getMaxNumCrsr() &&
                    ships.size() < Battleship.getMaxNumBtl() + Cruiser.getMaxNumCrsr() + Destroyer.getMaxNumDstr())
                        return new Destroyer(c);
        else if (ships.size() >= Battleship.getMaxNumBtl() + Cruiser.getMaxNumCrsr() + Destroyer.getMaxNumDstr() &&
                    ships.size() < MAX_SHIPS_ON_BOARD)
                        return new Submarine(c);
        return null;
    }

    public boolean equalityShipAndField(int index) {
        boolean res = false;
        for (int i = 0; i < ships.size(); i++) {
            for (int j = 0; j < ships.get(i).getLocation().length; j++) {
                res = getField().get(index).equals(getShips().get(i).getLocation()[j]);
                if (res) break;
            }
        }
        return res;
    }

    public void shipAttack(int index) {
        field.get(index).setHit(true);
        for (int i = 0; i < ships.size(); i++) {
            for (int j = 0; j < ships.get(i).getLocation().length; j++) {
                if (field.get(index).equals(ships.get(i).getLocation()[j]))
                    ships.get(i).hit(field.get(index));
                if (ships.get(i).isDead()) {
                    ships.get(i).setDead(true);
                    fillBoardersOfShip(ships.get(i));
                }
            }
        }
    }

    private void fillBoardersOfShip(Ship ship) {
        for (int x = 0; x < field.size(); x++) {
            for (int y = 0; y < ship.getBoarders().size(); y++) {
                if (ship.getBoarders().get(y).equals(field.get(x)))
                    field.get(x).setHit(true);
            }
        }
    }

    public boolean checkShipsDead() {
        for (int i = 0; i < ships.size(); i++)
            if (!ships.get(i).getLocation()[0].isHit())
                return false;
        return true;
    }

    private void init() {
        ships = new ArrayList<>();
        field = new ArrayList<>();
        settingCoordinates();
    }

    private void settingCoordinates() {
        for (int i = 0; i < SIDE_SIZE; i++)
            for (int j = 0; j < SIDE_SIZE; j++)
                field.add(new Cell(j, i));
    }

    public static int getSideSize() {
        return SIDE_SIZE;
    }

    public List<Cell> getField() {
        return field;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public static int getBoxSize() {
        return BOX_SIZE;
    }

    public boolean checkMaxOfShips() {
        return ships.size() == MAX_SHIPS_ON_BOARD;
    }

    public static int getMaxShipsOnBoard() {
        return MAX_SHIPS_ON_BOARD;
    }
}
