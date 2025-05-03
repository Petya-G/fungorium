package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TectonContentPanel extends JPanel {

    public TectonContentPanel() {
        setLayout(new FlowLayout());
        setBorder(createTitledBorder("Details"));
        setBackground(Color.WHITE);

        // Add Label
        add(new JLabel("This is the smaller lower middle section."));
    }

    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }
}