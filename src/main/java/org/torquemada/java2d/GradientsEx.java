package org.torquemada.java2d;

import javax.swing.*;
import java.awt.*;

class Surface8 extends JPanel {
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g.create();

        GradientPaint gp1 = new GradientPaint(5, 5, 
            Color.red, 20, 20, Color.black, true);

        g2d.setPaint(gp1);
        g2d.fillRect(20, 20, 300, 40);

        GradientPaint gp2 = new GradientPaint(5, 25, 
            Color.yellow, 20, 2, Color.black, true);

        g2d.setPaint(gp2);
        g2d.fillRect(20, 80, 300, 40);

        GradientPaint gp3 = new GradientPaint(5, 25, 
            Color.green, 2, 2, Color.black, true);

        g2d.setPaint(gp3);
        g2d.fillRect(20, 140, 300, 40);

        GradientPaint gp4 = new GradientPaint(25, 25, 
            Color.blue, 15, 25, Color.black, true);

        g2d.setPaint(gp4);
        g2d.fillRect(20, 200, 300, 40);

        GradientPaint gp5 = new GradientPaint(0, 0, 
             Color.orange, 0, 20, Color.black, true);

        g2d.setPaint(gp5);
        g2d.fillRect(20, 260, 300, 40);   
        
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class GradientsEx extends JFrame {
    
    public GradientsEx() {

        initUI();
    }    
    
    private void initUI() {
        
        add(new Surface8());
        
        setTitle("Gradients");
        setSize(350, 350);
        setLocationRelativeTo(null);            
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                GradientsEx ex = new GradientsEx();
                ex.setVisible(true);
            }
        });
    }    
}