package com.hundret.battleships.View.Panels;

import com.hundret.battleships.Model.States.GameState;
import com.hundret.battleships.View.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays game state.
 * Includes inner class for manipulation with main menu buttons.
 */
public class GamePanel extends JPanel {

    private final static int WIDTH = 800, HEIGHT = 600;

    private JButton startButt, exitButt;
    private final String startString = " Start ", exitString = " Exit ";

    private JButton directionButt;
    private final String vertDir = "Vertical", horzDir = "Horizontal";

    private String message = "Do you want to set ships manualy?";
    private String againMsg = "Do you wish to play again?";

    private BoardPanel playerPane;
    private BoardPanel enemyPane;
    private StatusPanel statusPane;

    private MainMenuControl control;

    private GameState gs;
    private boolean running;

    public static int choice = -1;
    public static int againChoice = -1;

    public GamePanel(GameState state) {
        initPanel();
        gs = state;
        running = true;
    }

    public void action() throws Exception {
        while (running) {
            update();
            redraw();
            Thread.sleep(500);
        }
    }

    private void update() {
        gs.updateGame();
    }

    private void redraw() {
        mainMenuScreen();
        settleScreen();
        battleScreen();
        endGameScreen();
    }

    private void endGameScreen() {
        if (gs.isEndGame()) {
            hidePanels();
            againChoice = JOptionPane.showOptionDialog(null, againMsg, GameFrame.name,
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (againChoice == 0) {
                refreshPanes();
            }
        }
    }

    private void mainMenuScreen() {
        if (gs.isMainMenu()) {
            mainMenu();
            if (playerPane == null || enemyPane == null)
                initBoardPanels();
            if (againChoice != -1) {
                playerPane.setBoard(gs.getPlayerBoard());
                enemyPane.setBoard(gs.getEnemyBoard());
                againChoice = -1;
            }
        } else {
            hideButts();
        }
    }

    private void settleScreen() {
        if (gs.isSettleShips()) {
            if (!playerPane.isVisible())
                settle();
            playerPane.redrawField();
        }
    }

    private void battleScreen() {
        if (gs.isBattle()) {
            if (choice != -1) {
                playerPane.redrawField();
                choice = -1;
            }
            if (!enemyPane.isVisible())
                battle();
            playerPane.redrawPanel();
            enemyPane.redrawPanel();
            statusPane.redraw();
        }
    }

    private void initDirButt() {
        directionButt = new JButton("Horizontal");
        directionButt.addActionListener(new DirectionListener());
        directionButt.setVisible(false);
    }

    private void mainMenu() {
        showButts();
    }

    private void battle() {
        playerPane.resetButtons();
        if (!playerPane.isVisible()) playerPane.setVisible(true);
        if (directionButt.isVisible()) directionButt.setVisible(false);
        enemyPane.setVisible(true);
        statusPane.setVisible(true);
    }

    private void settle() {
        playerPane.setVisible(true);
        directionButt.setVisible(true);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.ORANGE);
        initButts();
        initDirButt();
    }

    private void refreshPanes() {
        playerPane.refreshBoard();
        enemyPane.refreshBoard();
        playerPane.addActionToButts();
    }

    private void initBoardPanels() {
        if (gs == null) gs = new GameState();
        setPanels();
        add(playerPane);
        add(directionButt);
        add(enemyPane);
        add(statusPane);
    }

    private void setPanels() {
        playerPane = new BoardPanel(gs.getPlayerBoard());
        enemyPane = new BoardPanel(gs.getEnemyBoard());
        statusPane = new StatusPanel(gs);
        hidePanels();
    }

    private void hidePanels() {
        playerPane.setVisible(false);
        enemyPane.setVisible(false);
        statusPane.setVisible(false);
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
                    choice = JOptionPane.showOptionDialog(null, message, GameFrame.name,
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    gs.setSettleShips(true);
                    break;
                case exitString:
                    running = false;
                    System.exit(0);
                    break;
            }
        }

    }
    //
    // inner class for button that choose direction
    //
    class DirectionListener implements ActionListener {
        StringBuffer action;
        @Override
        public void actionPerformed(ActionEvent e) {
            action = new StringBuffer(e.getActionCommand());
            switch (action.toString()) {
                case horzDir:
                    directionButt.setText(vertDir);
                    BoardPanel.direction = false;
                    break;
                case vertDir:
                    directionButt.setText(horzDir);
                    BoardPanel.direction = true;
                    break;
            }
        }

    }

}
