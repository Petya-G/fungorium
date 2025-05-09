package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import model.*;
/**
 * A JPanel class that acts as the middle area of the GUI,
 * containing subpanels organized in a vertical layout.
 */
public class MiddlePanel extends JPanel {
    TurnOrderPanel turnOrderPanel = new TurnOrderPanel();
    MapPanel mapPanel;
    TectonContentPanel tectonContentPanel = new TectonContentPanel();
    Game game;
    /**
     * Constructor for the MiddlePanel.
     * Initializes and arranges the child panels with proper layout and styling.
     */
    public MiddlePanel(Game game, MapPanel mapPanel) {
        this.game = game;
        this.mapPanel = mapPanel;
        // Set the main layout and background
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(createTitledBorder("Game Area"));
        add(turnOrderPanel, BorderLayout.NORTH);

        add(mapPanel, BorderLayout.CENTER);

        add(tectonContentPanel, BorderLayout.SOUTH);
    }

    /**
     * Helper method to create a styled titled border for the panel.
     *
     * @param title The title of the border.
     * @return A configured Border instance.
     */
    private Border createTitledBorder(String title) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
        // Make the title text color black for better visibility
        titledBorder.setTitleColor(Color.BLACK);
        return titledBorder;
    }
}