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
public class Ball extends ColorfulSquare /*implements ISelectable */{

    @Autowired
    private IEngine engine;
//    private ILevel level;
    private Image ballImage;
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
        ballImage = new ImageIcon("src/main/resources/" + color.toString().toLowerCase() +  "ball.png").getImage();
        return this;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ballImage, 0, 0, getWidth(), getHeight(), null);
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
            g2d.drawRect(getWidth() / 15, getHeight() / 15, getWidth() - 20, getHeight() - 20);
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
