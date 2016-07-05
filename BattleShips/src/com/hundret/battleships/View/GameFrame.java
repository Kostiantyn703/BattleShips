package com.hundret.battleships.View;

import com.hundret.battleships.Model.States.GameState;
import com.hundret.battleships.View.Panels.GamePanel;

import javax.swing.*;

/**
 * Main window.
 */
public class GameFrame extends JFrame {

    public static String name = "Battle Ships";
    private GamePanel gamePane;// = new GamePanel();

    public GameFrame(GameState gs) {
        super();
        init(gs);
    }

    private void init(GameState gs) {
        gamePane = new GamePanel(gs);
        setTitle(name);
        setContentPane(gamePane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void start() {
        setVisible(true);
        try {
            gamePane.action();
        } catch (Exception e) {}
    }

}
