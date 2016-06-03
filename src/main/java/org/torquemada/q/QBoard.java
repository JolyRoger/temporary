package org.torquemada.q;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by torquemada on 5/28/16.
 */
public class QBoard extends JFrame implements IBoard {

    @Autowired
    private ILevel level;

    @Autowired
    private IEngine engine;

    @Override
    public void initUI() {
        getContentPane().add((QLevel) level);
        setTitle("Q-Game");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("keyChar: " + e.getKeyChar() +
//                        " keyCode: " + e.getKeyCode() +
//                " keyLocation: " + e.getKeyLocation() +
//                " extendedKeyCode: " + e.getExtendedKeyCode());
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_DOWN :
                    case KeyEvent.VK_RIGHT : engine.notifySelectNext(false);
                        break;
                    case KeyEvent.VK_UP :
                    case KeyEvent.VK_LEFT : engine.notifySelectNext(true);
                        break;
                    case KeyEvent.VK_CONTROL : engine.notifySelectToMove();
                }
            }
        });
        setVisible(true);
    }

    @Override
    public void assignLevel(int[] dimension, int[] levelData) {
        level.setDimension(dimension);
        level.setLevelData(levelData);
        level.init();
    }

    @Override
    public void select(int id, boolean select) {
        level.select(id, select);
    }

    @Override
    public void selectToMove(int selectedId) {
        level.selectToMove(selectedId);
    }
}
