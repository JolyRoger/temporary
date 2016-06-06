package org.torquemada.q.squares;

import org.springframework.beans.factory.annotation.Autowired;
import org.torquemada.q.IEngine;
import org.torquemada.q.ValidColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by torquemada on 5/29/16.
 */
public class Ball extends ColorfulSquare {

    @Autowired
    private IEngine engine;
    private boolean selected = false;
    private boolean selectedToMove = false;

    public Ball() {
        imageIcon = new ImageIcon("src/main/resources/empty.png");
        image = imageIcon.getImage();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                engine.notifySelect(id);
            }
        });
    }

    @Override
    public ColorfulSquare withColor(ValidColor color) {
        this.color = color;
        return this;
    }

    @Override
    public void paint(Graphics g) {
        int fillFactor = 10;
        g.setColor(color.getColor());
        g.fillOval(getWidth()/fillFactor, getHeight()/fillFactor, getWidth() - getWidth()/fillFactor*2, getHeight() - getHeight()/fillFactor*2);
        super.paint(g);
        if (selectedToMove) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.WHITE);
        }

        Graphics2D g2d = (Graphics2D) g.create();

        if (selected) {
            BasicStroke bs1 = new BasicStroke(getWidth() / 10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2d.setStroke(bs1);
            g2d.drawRect(0, 0, getWidth(), getHeight());
        }
    }

    public void select(boolean select) {
        this.selected = select;
        selectedToMove = false;
    }

    public void selectToMove() {
        selectedToMove = !selectedToMove;
    }

    public boolean isReadyToMove() {
        return selectedToMove;
    }
}
