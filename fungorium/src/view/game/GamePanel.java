package view.game;

import model.Game;
import view.game.sidebar.SidebarPanel;
import view.game.contentpanel.ContentPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    SidebarPanel sidebarPanel = new SidebarPanel();
    MiddlePanel middlePanel;
    Game game;
    MapPanel mapPanel;
    ContentPanel contentPanel;

    public GamePanel(Game game, MapPanel mapPanel, ContentPanel contentPanel) {
        this.game = game;
        this.middlePanel = new MiddlePanel(game, mapPanel, contentPanel);
        this.mapPanel = mapPanel;
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add Left Panel (Buttons and Chat Log)
        add(sidebarPanel, BorderLayout.WEST);

        // Add Middle Panel (Game Area including Game Info)
        add(middlePanel, BorderLayout.CENTER);
    }
}