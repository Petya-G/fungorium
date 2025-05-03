package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MapPanel extends JPanel {

    public MapPanel() {
        setLayout(new FlowLayout());
        setBorder(createTitledBorder("Main View"));
        setBackground(new Color(250, 250, 250)); // Subtle gray background

        // Add Label
        add(new JLabel("This is the larger upper middle section."));
    }

    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }
}