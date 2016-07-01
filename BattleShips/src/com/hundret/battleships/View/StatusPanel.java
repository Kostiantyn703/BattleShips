package com.hundret.battleships.View;

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

    public void setText() {
        playerStatus.setText(p + gs.getPlayerBoard().getNumOfShips() + " / " + Board.getMaxShipsOnBoard());
        compStatus.setText(c + gs.getEnemyBoard().getNumOfShips() + " / " + Board.getMaxShipsOnBoard());
    }

}
