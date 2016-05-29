package org.torquemada.q.squares;

import org.torquemada.q.SquareType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/29/16.
 */
public class Ball extends ColorfulSquare {

    private ImageIcon ballImage;

    public Ball() {
        imageIcon = new ImageIcon("src/main/resources/empty.png");
    }

    @Override
    public Square with(SquareType type) {
        ballImage = new ImageIcon("src/main/resources/" + type.getColorString().orElse("light_gray") + "ball.png");

        return super.with(type);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ballImage.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);
    }
}
