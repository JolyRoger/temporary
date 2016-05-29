package org.torquemada.q;

import org.torquemada.q.squares.*;

/**
 * Created by torquemada on 5/29/16.
 */
public class SquareFactory {
    public static Square create(SquareType type) {
        switch (type) {
            case empty : return new Empty().with(type);
            case solid : return new Solid().with(type);

            case blueball:
            case redball:
            case whiteball:
            case yellowball:
            case greenball:
            case orangeball:
                return new Ball().with(type);

            case blueloose:
            case redloose:
            case whiteloose:
            case yellowloose:
            case greenloose:
            case orangeloose:
                return new Loose().with(type);
        }
        return new Empty().with(type);
    }
}
