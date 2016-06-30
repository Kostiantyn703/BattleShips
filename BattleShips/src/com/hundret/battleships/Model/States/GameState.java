package com.hundret.battleships.Model.States;

import com.hundret.battleships.Model.Entity.Board;

/**
 * Class represents information about current game status.
 */
public class GameState {

    private static int numOfTurns = 0;

    private Board playerBoard;
    private Board enemyBoard;

    private static boolean setShips = false;
    private static boolean startBattle = false;
    private static boolean playerTurn;

    public GameState() {
        playerBoard = new Board();
        enemyBoard = new Board();
    }

    public void fillEnemyBoard() {
        enemyBoard.randomShipSet();
    }

    public void updateGame() {
        if (setShips) {
            fillEnemyBoard();
            if (shipsSetteled()) {
                setShips = false;
                startBattle = true;
            }
        }
        if (startBattle) {
            if (numOfTurns == 0) chooseFirstTurn();
            gamePlay();
            if (endGameCondition()) {
                startBattle = false;
                System.out.println("Game over");
            }
            numOfTurns++;
        }
    }

    private boolean shipsSetteled() {
        return playerBoard.getShips().size() == Board.getMaxShipsOnBoard() &&
                    enemyBoard.getShips().size() == Board.getMaxShipsOnBoard();
    }

    private void gamePlay() {
        if (!isPlayerTurn()) {
            playerBoard.shipAttack(computerTurn());
            playerTurn = true;
        }
    }

    private int computerTurn() {
        return (int)(Math.random() * getPlayerBoard().getField().size());
    }

    private void chooseFirstTurn() {
        int val = (int)(Math.random() * 100);
        if (val % 2 == 1)
            playerTurn = true;
        else
            playerTurn = false;
    }

    private boolean endGameCondition() {
        return playerBoard.checkShipsDead() || enemyBoard.checkShipsDead();
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public static boolean isSetShips() {
        return setShips;
    }

    public static void setSetShips(boolean isSet) {
        setShips = isSet;
    }

    public static boolean isStartBattle() {
        return startBattle;
    }

    public static boolean isPlayerTurn() {
        return playerTurn;
    }

    public static void setPlayerTurn(boolean playerTurn) {
        GameState.playerTurn = playerTurn;
    }

}
