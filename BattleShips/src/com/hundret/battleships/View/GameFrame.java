package com.hundret.battleships.View;

import javax.swing.*;

/**
 * Main window.
 */
public class GameFrame extends JFrame {

    private String title = "Battle Ships";
    private GamePanel gamePane = new GamePanel();

    public GameFrame() {
        super();
        init();
    }

    private void init() {
        setTitle(title);
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
