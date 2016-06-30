package com.hundret.battleships.View;

import com.hundret.battleships.Model.States.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays game state.
 * Includes inner class for manipulation with main menu buttons.
 */
public class GamePanel extends JPanel {

    private final static int SHIFT_FROM_EDGE = 40;
    private final static int WIDTH = 800, HEIGHT = 600;

    private JButton startButt, exitButt;
    private final String startString = " Start ", exitString = " Exit ";

    private BoardPanel playerPane;
    private BoardPanel enemyPane;

    private MainMenuControl control;

    private GameState gs;
    private boolean running;

    public GamePanel() {
        init();
        gs = new GameState();
        initPanels();
        running = true;
    }

    public void action() throws Exception {
        while (running) {
            redraw();
            update();
            Thread.sleep(600);
        }
    }

    private void update() {
        gs.updateGame();
    }

    private void redraw() {
        //mainMenu();
        if (gs.isSetShips()) {
            if (!playerPane.isVisible()) settle();
            playerPane.redrawPanel();
        } else if (gs.isStartBattle()) {
            if (!enemyPane.isVisible()) battle();
            playerPane.redrawPanel();
            enemyPane.redrawPanel();
        }
//        } else if (!gs.isSetShips() && !gs.isStartBattle()){
//            mainMenu();
//        }
    }

    private void mainMenu() {
        hidePanels();
        showButts();
    }

    private void battle() {
        playerPane.resetButtons();
        enemyPane.setVisible(true);
    }

    private void settle() {
        hideButts();
        playerPane.setVisible(true);
    }

    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.ORANGE);
        initButts();
    }

    private void initPanels() {
        if (gs == null) gs = new GameState();

        playerPane = new BoardPanel(gs.getPlayerBoard());
        add(playerPane);
        enemyPane = new BoardPanel(gs.getEnemyBoard());
        add(enemyPane);
        hidePanels();
    }

    private void hidePanels() {
        playerPane.setVisible(false);
        enemyPane.setVisible(false);
    }

    private void initButts() {
        control = new MainMenuControl();
        startButt = new JButton(startString);
        startButt.addActionListener(control);
        exitButt = new JButton(exitString);
        exitButt.addActionListener(control);
        add(startButt);
        add(exitButt);
    }

    private void hideButts() {
        startButt.setVisible(false);
        exitButt.setVisible(false);
    }

    private void showButts() {
        startButt.setVisible(true);
        exitButt.setVisible(true);
    }
    //
    // inner controller class
    //
    class MainMenuControl implements ActionListener {
        StringBuffer action;
        @Override
        public void actionPerformed(ActionEvent e) {
            action = new StringBuffer(e.getActionCommand());
            switch (action.toString()) {
                case startString:
                    gs.setSetShips(true);
                    break;
                case exitString:
                    running = false;
                    System.exit(0);
                    break;
            }
        }
    }

}
