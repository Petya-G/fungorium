package view.game;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    public ButtonsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 buttons, spaced vertically
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Create and add buttons
        add(createButton("End Turn"));
        add(createButton("Save"));
        add(createButton("Exit"));
    }

    // Helper method to create a button with consistent styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(60, 120, 200)); // Blue text
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Subtle border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for a better UX
        return button;
    }
}