package view.game;

import model.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MiddlePanel extends JPanel {
    TurnOrderPanel turnOrderPanel = new TurnOrderPanel();
    MapPanel mapPanel;
    TectonContentPanel tectonContentPanel = new TectonContentPanel();
    Game game;

    public MiddlePanel(Game game, MapPanel mapPanel) {
        this.game = game;
        this.mapPanel = mapPanel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(createTitledBorder("Game Area"));
        add(turnOrderPanel, BorderLayout.NORTH);

        add(mapPanel, BorderLayout.CENTER);

        add(tectonContentPanel, BorderLayout.SOUTH);
    }

    private Border createTitledBorder(String title) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)), title);
        titledBorder.setTitleColor(Color.BLACK);
        return titledBorder;
    }
}