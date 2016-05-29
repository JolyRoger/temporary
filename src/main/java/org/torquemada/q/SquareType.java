package org.torquemada.q;

import java.awt.*;
import java.util.Optional;

/**
 * Created by torquemada on 5/28/16.
 */
public enum SquareType {
    empty, solid,
    redball, whiteball, yellowball, greenball, orangeball, blueball,
    redloose, whiteloose, yellowloose, greenloose, orangeloose, blueloose;

    public static SquareType getType(int i) {
        switch (i) {
            case 0 : return empty;
            case 1 : return solid;

            case 2 : return redball;
            case 3 : return whiteball;
            case 4 : return yellowball;
            case 5 : return orangeball;
            case 6 : return blueball;

            case 22 : return redloose;
            case 33 : return whiteloose;
            case 44 : return yellowloose;
            case 55 : return orangeloose;
            case 66 : return blueloose;

            default: return empty;
        }
    }

    public Optional<Color> getColor() {
        switch (getColorString().orElse("")) {
            case "red" : return Optional.of(Color.RED);
            case "white" : return Optional.of(Color.WHITE);
            case "yellow" : return Optional.of(Color.YELLOW);
            case "orange" : return Optional.of(Color.ORANGE);
            case "blue" : return Optional.of(Color.BLUE);
        }
        return Optional.empty();
    }

    public Optional<String> getColorString() {
        String strType = toString();

        if (strType.endsWith("ball")) {
            return Optional.of(strType.substring(0, strType.indexOf("ball")));
        } else if (strType.endsWith("loose")) {
            return Optional.of(strType.substring(0, strType.indexOf("loose")));
        } else return Optional.empty();
    }
}


