package org.torquemada.q.squares;

import org.torquemada.q.SquareType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/28/16.
 */
public abstract class Square extends Component {

    protected int width = 64;
    protected int height = 64;
    protected SquareType type;
    protected ImageIcon imageIcon = null;
    private Image image = null;

    public Square() {
        setPreferredSize(new Dimension(width, height));
    }

    public Square with(SquareType type) {
        this.type = type;
        image = imageIcon.getImage();
        return this;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
