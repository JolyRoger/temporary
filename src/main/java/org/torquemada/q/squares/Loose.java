package org.torquemada.q.squares;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/29/16.
 */
public class Loose extends ColorfulSquare {

    public Loose() {
        imageIcon = new ImageIcon("src/main/resources/empty.png");
        image = imageIcon.getImage();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paint(g);
    }
}
