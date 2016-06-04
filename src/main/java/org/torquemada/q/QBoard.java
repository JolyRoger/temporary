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
                    case KeyEvent.VK_DOWN : engine.notifyDown(); break;
                    case KeyEvent.VK_RIGHT : engine.notifyRight(); break; // engine.notifySelectNext(false);
                    case KeyEvent.VK_UP : engine.notifyUp(); break;
                    case KeyEvent.VK_LEFT : engine.notifyLeft(); break; // engine.notifySelectNext(true);
                    case KeyEvent.VK_CONTROL : engine.notifySelectToMove(); break;
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

    @Override
    public boolean isReadyToMove(int selectedId) {
        return level.isReadyToMove(selectedId);
    }

    @Override
    public void moveBall(int from, int to) {
        if (to == -1) level.moveBall(from);
        else level.moveBall(from, to);
    }
}
