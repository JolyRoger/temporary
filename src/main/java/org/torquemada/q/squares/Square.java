package org.torquemada.q.squares;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/28/16.
 */
public abstract class Square extends Component {

    protected int width = 64;
    protected int height = 64;
    protected ImageIcon imageIcon = null;
    protected Image image = null;
    protected int id;
    public Square() {
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public Square withId(int id) { this.id = id; return this; }

}
