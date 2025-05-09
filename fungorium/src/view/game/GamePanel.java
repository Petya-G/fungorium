package view.game;

import view.game.sidebar.SidebarPanel;

import javax.swing.*;
import java.awt.*;
import model.*;

public class GamePanel extends JPanel {
    SidebarPanel sidebarPanel = new SidebarPanel();
    MiddlePanel middlePanel;
    Game game;
    MapPanel mapPanel;

    public GamePanel(Game game, MapPanel mapPanel) {
        this.game = game;
        this.mapPanel = mapPanel;
        middlePanel = new MiddlePanel(game, mapPanel);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add Left Panel (Buttons and Chat Log)
        add(sidebarPanel, BorderLayout.WEST);

        // Add Middle Panel (Game Area including Game Info)
        add(middlePanel, BorderLayout.CENTER);
    }
}