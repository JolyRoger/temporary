package org.torquemada.q;

import org.torquemada.q.squares.Square;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/28/16.
 */
public class Level extends JPanel {
    int[] dimension = {6, 6};
    int [] level = {
            1, 1, 33, 1, 1, 1,
            1, 3, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 44,
            1, 0, 0, 0, 0, 1,
            1, 1, 0, 4, 0, 1,
            0, 1, 1, 1, 1, 0
    };

    private Square[] squares;

    public Level() {
        GridLayout layout = new GridLayout(dimension[0], dimension[1]);
        setLayout(layout);
        setSize(new Dimension(dimension[0] * 64, dimension[1] * 64));

        squares = new Square[level.length];

        for (int i = 0; i < squares.length; i++) {
            squares[i] = SquareFactory.create(SquareType.getType(level[i]));
            add(squares[i]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
