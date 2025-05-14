package view.game;

import model.Game;
import view.game.contentpanel.ContentPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MiddlePanel extends JPanel {
    TurnOrderPanel turnOrderPanel = new TurnOrderPanel();
    MapPanel mapPanel;
    ContentPanel contentPanel;
    Game game;

    public MiddlePanel(Game game, MapPanel mapPanel, ContentPanel contentPanel) {
        this.game = game;
        this.mapPanel = mapPanel;
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(createTitledBorder());
        add(turnOrderPanel, BorderLayout.NORTH);

        add(mapPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.SOUTH);

        mapPanel.setTectonContentPanel(contentPanel);
    }

    private Border createTitledBorder() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)), "Game Area");
        titledBorder.setTitleColor(Color.BLACK);
        return titledBorder;
    }
}