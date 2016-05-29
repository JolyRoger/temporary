package org.torquemada.q.squares;

import org.torquemada.q.SquareType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/29/16.
 */
public class Loose extends ColorfulSquare {

    private Color color = null;

    public Loose() {
        imageIcon = new ImageIcon("src/main/resources/empty.png");
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getColor(type));
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paint(g);
    }
}
