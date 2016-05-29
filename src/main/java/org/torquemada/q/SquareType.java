package org.torquemada.q;

/**
 * Created by torquemada on 5/28/16.
 */
public enum SquareType {
    empty, solid,
    blueball, redball, whiteball, yellowball, greenball, orangeball,
    blueloose, redloose, whiteloose, yellowloose, greenloose, orangeloose;

    public static SquareType getType(int i) {
        switch (i) {
            case 0 : return empty;
            case 1 : return solid;

            case 2 : return redball;
            case 3 : return whiteball;
            case 4 : return yellowball;
            case 5 : return orangeball;

            case 22 : return redloose;
            case 33 : return whiteloose;
            case 44 : return yellowloose;
            case 55 : return orangeloose;

            default: return empty;
        }
    }

    public String getPath() {
        return "src/main/resources/" + toString() + ".png";
    }
}


