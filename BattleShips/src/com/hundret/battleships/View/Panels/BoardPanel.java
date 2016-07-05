package com.hundret.battleships.View.Panels;

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

    private Color[] colors;
    private final static int NUM_COLORS = 4;

    private List<JButton> buttons;
    private GridLayout layout;
    private Dimension dim;
    private ButtListener listener;

    public static boolean direction = true;

    public BoardPanel(Board b) {
        super();
        board = b;
        init();
    }

    private void init() {
        dim = new Dimension(board.getBoxSize(), board.getBoxSize());
        listener = new ButtListener();
        layout = new GridLayout(board.getSideSize(), board.getSideSize());
        buttons = new ArrayList<>();
        setPreferredSize(dim);
        setLayout(layout);
        initColors();
        initButtons();
    }

    private void initColors() {
        colors = new Color[NUM_COLORS];
        colors[0] = new Color(0,0,220);
        colors[1] = new Color(0,220,0);
        colors[2] = new Color(220,220,0);
        colors[3] = new Color(220, 0, 0);
    }

    public void initButtons() {
        for (int i = 0; i < board.getSideSize() * board.getSideSize(); i++) {
            JButton temp = new JButton(""+i);
            temp.setBackground(colors[0]);
            temp.addActionListener(listener);
            buttons.add(temp);
            add(temp);
        }
    }

    public void addActionToButts() {
        for (int i = 0; i < buttons.size(); i++)
            buttons.get(i).addActionListener(listener);
    }

    public void resetButtons() {
        for (int i = 0; i < buttons.size(); i++)
            buttons.get(i).removeActionListener(listener);
    }

    private void redrawButt(int index) {
        if (board.getField().get(index).isHit())
            buttons.get(index).setBackground(colors[2]);
        for (int i = 0; i < board.getShips().size(); i++) {
            for (int j = 0; j < board.getShips().get(i).getLocation().length; j++) {
                if (board.getField().get(index).equals(board.getShips().get(i).getLocation()[j])) {
                    if (board.getShips().get(i).getLocation()[j].isHit())
                        buttons.get(index).setBackground(colors[3]);
                }
            }
        }
    }

    public void refreshBoard() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackground(colors[0]);
        }
    }

    public void redrawField() {
        for (int i = 0; i < board.getField().size(); i++)
            for (int j = 0; j < board.getShips().size(); j++)
                for (int x = 0; x < board.getShips().get(j).getLocation().length; x++)
                    if (board.getField().get(i).equals(board.getShips().get(j).getLocation()[x]))
                        buttons.get(i).setBackground(colors[1]);
    }

    public void redrawPanel() {
        for (int i = 0; i < buttons.size(); i++)
            redrawButt(i);
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    //
    //inner class ButtListener for button manipulations
    //
    class ButtListener implements ActionListener{
        int command;
        Cell temp;

        @Override
        public void actionPerformed(ActionEvent e) {
            command = Integer.parseInt(e.getActionCommand());
            temp = board.getField().get(command);
            if (GameState.isSettleShips())
                settleControl();
            if (GameState.isBattle() && GameState.isPlayerTurn())
                attackControl();
        }

        private void settleControl() {
            board.addShip(temp, direction);
        }

        private void attackControl() {
            if (!board.shipAttack(command))
                GameState.setPlayerTurn(false);
        }

    }

}
