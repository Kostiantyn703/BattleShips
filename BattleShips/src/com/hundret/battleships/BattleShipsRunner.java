package com.hundret.battleships;

import com.hundret.battleships.Model.States.GameState;
import com.hundret.battleships.View.GameFrame;

/**
 * Game runner.
 */
public class BattleShipsRunner {
    public static void main(String[] args) {
        GameFrame gj = new GameFrame(new GameState());
        gj.start();
    }
}
