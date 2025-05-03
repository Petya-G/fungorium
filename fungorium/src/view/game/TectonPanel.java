package view.game;

import javax.swing.*;
import java.awt.*;

public class TectonPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass to ensure proper UI behavior

        // Cast to Graphics2D for advanced rendering options
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the circle
        g2d.setColor(Color.BLUE);

        // Draw the circle (oval where width and height are the same)
        int x = 50; // X-coordinate of the top-left corner
        int y = 50; // Y-coordinate of the top-left corner
        int diameter = 100; // Diameter of the circle
        g2d.fillOval(x, y, diameter, diameter);
    }
}