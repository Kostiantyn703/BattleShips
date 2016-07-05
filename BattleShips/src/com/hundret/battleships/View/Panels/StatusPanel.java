package com.hundret.battleships.View.Panels;

import com.hundret.battleships.Model.Entity.Board;
import com.hundret.battleships.Model.States.GameState;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kostik on 01/07/16.
 */
public class StatusPanel extends JPanel {

    private GameState gs;

    private JLabel playerStatus, compStatus;
    private String p = "Player ships: ", c = "Computer ships: ";

    public StatusPanel(GameState gs) {
        super();
        this.gs = gs;
        init();
    }

    private void init() {
        playerStatus = new JLabel();
        compStatus = new JLabel();
        add(playerStatus);
        add(compStatus);
        setPreferredSize(new Dimension(800, 100));
    }

    public void redraw() {
        playerStatus.setText(p + gs.getPlayerBoard().aliveShips() + " / " + Board.getMaxShipsOnBoard());
        compStatus.setText(c + gs.getEnemyBoard().aliveShips() + " / " + Board.getMaxShipsOnBoard());
        if (GameState.isPlayerTurn())
            setBackground(Color.CYAN);
        else
            setBackground(Color.RED);
    }

}
