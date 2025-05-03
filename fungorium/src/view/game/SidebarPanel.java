package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Options & Chat"));
        setBackground(Color.WHITE);

        // Add Buttons Panel
        add(new ButtonsPanel(), BorderLayout.NORTH);

        // Add Chat Log Panel
        add(new ChatLogPanel(), BorderLayout.CENTER);
    }

    // Create a titled border with consistent formatting
    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }
}