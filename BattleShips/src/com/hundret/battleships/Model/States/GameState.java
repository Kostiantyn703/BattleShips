package com.hundret.battleships.Model.States;

import com.hundret.battleships.Model.Entity.Board;
import com.hundret.battleships.View.Panels.GamePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents information about current game status.
 */
public class GameState {

    private static int numOfTurns = 0;

    private Board playerBoard;
    private Board enemyBoard;

    private List<Integer> compUsedVals = new ArrayList<>();

    private static boolean settleShips;
    private static boolean battle;
    private static boolean mainMenu;
    private static boolean endGame;

    private static boolean playerTurn;

    public GameState() {
        init();
    }

    private void init() {
        settleShips = false;
        battle = false;
        endGame = false;
        mainMenu = true;
    }

    private void initBoards() {
        playerBoard = new Board();
        enemyBoard = new Board();
    }

    private void fillEnemyBoard() {
        enemyBoard.randomShipSet();
    }

    public void updateGame() {
        if (mainMenu) {}
        if (settleShips)
            mainMenu = false;
            shipSettle();
        if (battle)
            gamePlay();
        if (endGame) {
            if (GamePanel.againChoice == 0) {
                mainMenu = true;
                endGame = false;
                initBoards();
            } else if (GamePanel.againChoice == 1) {
                System.exit(0);
            }
        }
    }

    private void shipSettle() {
        if (playerBoard == null || enemyBoard == null) initBoards();
        fillEnemyBoard();
        if (GamePanel.choice == 1) {
            playerBoard.randomShipSet();
        }
        if (shipsSetteled()) {
            settleShips = false;
            battle = true;
        }
    }

    private void gamePlay() {
        if (numOfTurns == 0)
            chooseFirstTurn();
        turn();
        if (endGameCondition()) {
            endGame();
        }
        numOfTurns++;
    }

    private void turn() {
        int val;
        if (!isPlayerTurn()) {
            val = computerTurn();
            if (!playerBoard.shipAttack(val)) {
                compUsedVals.add(val);
                playerTurn = true;
            }
        }
    }

    public void endGame() {
        battle = false;
        endGame = true;
        playerBoard = null;
        enemyBoard = null;
        numOfTurns = 0;
    }

    private boolean shipsSetteled() {
        return playerBoard.getShips().size() == Board.getMaxShipsOnBoard() &&
                    enemyBoard.getShips().size() == Board.getMaxShipsOnBoard();
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
        return playerBoard.checkAllShipsDead() || enemyBoard.checkAllShipsDead();
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public static boolean isBattle() {
        return battle;
    }

    public static boolean isEndGame() {
        return endGame;
    }

    public static boolean isPlayerTurn() {
        return playerTurn;
    }

    public static void setPlayerTurn(boolean playerTurn) {
        GameState.playerTurn = playerTurn;
    }

    public static boolean isSettleShips() {
        return settleShips;
    }

    public static void setSettleShips(boolean settleShips) {
        GameState.settleShips = settleShips;
    }

    public static boolean isMainMenu() {
        return mainMenu;
    }

}
