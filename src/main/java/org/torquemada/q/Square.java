package org.torquemada.q;

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

    public Square(SquareType type) {
        this.type = type;
        imageIcon = new ImageIcon(type.getPath());
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
