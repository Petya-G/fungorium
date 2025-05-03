package view.game.sidebar;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    public ButtonsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 buttons, spaced vertically
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Create and add sidebar buttons
        add(new SidebarButton("End Turn"));
        add(new SidebarButton("Save"));
        add(new SidebarButton("Exit"));
    }
}