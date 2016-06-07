package org.torquemada.q;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by torquemada on 6/7/16.
 */
public class SettingsPanel extends JPanel {

//    @Autowired
    private IEngine engine;

    // TODO: 6/7/16
    public SettingsPanel(IEngine engine) {
        this.engine = engine;
        setLayout(new GridLayout(1, 1, 10, 10));

        JButton reload = new JButton("Перезагрузи уровень");
        reload.setFocusable(false);

        reload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(SettingsPanel.this.engine::reloadLevel);
            }
        });

        add(reload);
    }

    private JButton createButtons() {
        try {
            BufferedImage startButton = ImageIO.read(new File("src/main/resources/start_backup.png"));
            BufferedImage startButtonHover = ImageIO.read(new File("src/main/resources/start_backup_hover.png"));
            BufferedImage startButtonActive = ImageIO.read(new File("src/main/resources/start_backup_active.png"));

            JButton startBackupButton = new JButton(new ImageIcon(startButton));
            startBackupButton.setRolloverIcon(new ImageIcon(startButtonHover));
            startBackupButton.setPressedIcon(new ImageIcon(startButtonActive));
            startBackupButton.setBorder(BorderFactory.createEmptyBorder());
            startBackupButton.setContentAreaFilled(false);
            startBackupButton.setFocusable(false);
            return startBackupButton;

        } catch (IOException e) {
            e.printStackTrace();
            return new JButton("Press");
        }
    }
}
