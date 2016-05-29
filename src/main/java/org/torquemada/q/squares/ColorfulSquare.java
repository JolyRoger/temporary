package org.torquemada.q.squares;

import org.torquemada.q.SquareType;

import java.awt.*;

/**
 * Created by torquemada on 5/29/16.
 */
public class ColorfulSquare extends Square {

    protected Color color;

    public Color getColor(SquareType type) {
        if (color == null) color = type.getColor().orElse(Color.LIGHT_GRAY);
        return color;
    }
}
