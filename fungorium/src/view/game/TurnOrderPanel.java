package view.game;

import javax.swing.*;
import java.awt.*;

public class TurnOrderPanel extends JPanel {

    public TurnOrderPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        // Add Game Info Label
        JLabel gameInfoLabel = new JLabel("Game Information");
        gameInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gameInfoLabel.setForeground(new Color(60, 60, 60)); // Dark gray
        add(gameInfoLabel);
    }
}