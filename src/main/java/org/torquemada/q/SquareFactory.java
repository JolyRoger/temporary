package org.torquemada.q;

/**
 * Created by torquemada on 5/29/16.
 */
public class SquareFactory {
    public static Square create(SquareType type) {
        return new Empty(type);
    }

}
