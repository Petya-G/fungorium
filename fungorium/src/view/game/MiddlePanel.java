package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A JPanel class that acts as the middle area of the GUI,
 * containing subpanels organized in a vertical layout.
 */
public class MiddlePanel extends JPanel {

    /**
     * Constructor for the MiddlePanel.
     * Initializes and arranges the child panels with proper layout and styling.
     */
    public MiddlePanel() {
        // Set the main layout and background
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(createTitledBorder("Game Area"));

        try {
            // Add the Game Info Panel to the top (NORTH)
            add(new TurnOrderPanel(), BorderLayout.NORTH);

            // Add the Upper Middle Panel to the center (CENTER)
            add(new MapPanel(), BorderLayout.CENTER);

            // Add the Lower Middle Panel to the bottom (SOUTH)
            add(new TectonContentPanel(), BorderLayout.SOUTH);

        } catch (Exception e) {
            // Display an error dialog if any exception occurs
            JOptionPane.showMessageDialog(this,
                    "Error initializing MiddlePanel: " + e.getMessage(),
                    "Initialization Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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