package org.torquemada.q.squares;

import org.torquemada.q.ValidColor;

/**
 * Created by torquemada on 5/29/16.
 */
public abstract class ColorfulSquare extends Square {


    protected ValidColor color;

    public ColorfulSquare withColor(ValidColor color) {
        this.color = color;
        return this;
    }
}
