package org.torquemada.q;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/28/16.
 */
public class Board extends JFrame {

    public Board() {
        initUI();
    }

    private void initUI() {
        Level level = new Level();
        getContentPane().add(level);
        setTitle("Q-Game");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                Board ex = new Board();
                ex.setVisible(true);
            }
        );
    }
}
