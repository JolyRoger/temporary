package org.torquemada.q;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/28/16.
 */
public class Init extends JPanel {

    private final String SPLASH_PATH = "src/main/resources/qgame.png";

    private Image splash = new ImageIcon(SPLASH_PATH).getImage();

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.drawImage(splash, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
    }
}
