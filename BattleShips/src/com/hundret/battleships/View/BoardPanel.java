package com.hundret.battleships.View;

import com.hundret.battleships.Model.Entity.Board;
import com.hundret.battleships.Model.Entity.Cell;
import com.hundret.battleships.Model.States.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel of graphical representation for game board.
 * Includes actionListener to manipulate with board cells.
 */
public class BoardPanel extends JPanel {

    private Board board;

    private List<JButton> buttons;
    private GridLayout layout;
    private Dimension dim;
    private BoardListener listener;

    public BoardPanel(Board b) {
        super();
        board = b;
        init();
    }

    private void init() {
        dim = new Dimension(board.getBoxSize(), board.getBoxSize());
        listener = new BoardListener();
        layout = new GridLayout(board.getSideSize(), board.getSideSize());
        buttons = new ArrayList<>();
        setPreferredSize(dim);
        setLayout(layout);
        initButtons();
    }

    private void initButtons() {
        for (int i = 0; i < board.getSideSize() * board.getSideSize(); i++) {
            JButton temp = new JButton(""+i);
            temp.setBackground(Color.BLUE);
            temp.addActionListener(listener);
            buttons.add(temp);
            add(temp);
        }
    }

    public void resetButtons() {
        for (int i = 0; i < buttons.size(); i++)
            buttons.get(i).removeActionListener(listener);
    }

    private void redrawButt(int index) {
        if (GameState.isSetShips() && board.equalityShipAndField(index))
            buttons.get(index).setBackground(Color.GREEN);
        if (board.getField().get(index).isHit()) {
            buttons.get(index).setBackground(Color.YELLOW);
            for (int i = 0; i < board.getShips().size(); i++) {
                for (int j = 0; j < board.getShips().get(i).getLocation().length; j++)
                    if (board.getField().get(index).equals(board.getShips().get(i).getLocation()[j]))
                        if (board.getShips().get(i).getLocation()[j].isHit())
                            buttons.get(index).setBackground(Color.RED);
            }
        }
    }

    public void redrawPanel() {
        for (int i = 0; i < buttons.size(); i++) redrawButt(i);
    }
    //
    //inner class BoardListener for button manipulations
    //
    class BoardListener implements ActionListener {
        int command;
        Cell temp;
        @Override
        public void actionPerformed(ActionEvent e) {
            command = Integer.parseInt(e.getActionCommand());
            temp = board.getField().get(command);
            System.out.println(command);
            if (GameState.isSetShips())
                settleControl();
            if (GameState.isStartBattle() && GameState.isPlayerTurn())
                attackControl();
        }

        private void settleControl() {
            board.addShip(temp);
        }

        private void attackControl() {
            board.shipAttack(command);
            GameState.setPlayerTurn(false);
        }

    }

}
