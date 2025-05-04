package view.game;

import view.game.sidebar.SidebarPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    SidebarPanel sidebarPanel = new SidebarPanel();
    MiddlePanel middlePanel = new MiddlePanel();

    public GamePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add Left Panel (Buttons and Chat Log)
        add(sidebarPanel, BorderLayout.WEST);

        // Add Middle Panel (Game Area including Game Info)
        add(middlePanel, BorderLayout.CENTER);
    }
}