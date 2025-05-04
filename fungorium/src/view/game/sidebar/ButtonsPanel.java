package view.game.sidebar;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    SidebarButton endTurnButton = new SidebarButton("End Turn");
    SidebarButton saveButton = new SidebarButton("Save");
    SidebarButton exitButton = new SidebarButton("Exit");

    public ButtonsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 buttons, spaced vertically
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Create and add sidebar buttons
        add(endTurnButton);
        add(saveButton);
        add(exitButton);
    }
}