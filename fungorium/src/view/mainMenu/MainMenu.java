package view.mainMenu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu(){
        // Set the layout to BoxLayout with vertical alignment
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create the first MenuButton
        JButton button1 = new JButton("Button 1");

        // Create the second MenuButton
        JButton button2 = new JButton("Button 2");

        // Center alignment for the buttons
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add some vertical spacing between the buttons
        add(Box.createVerticalGlue());
        add(button1);
        add(Box.createRigidArea(new Dimension(0, 20))); // 20px spacing
        add(button2);
        add(Box.createVerticalGlue());
    }
}
