package view.game.sidebar;

import javax.swing.*;
import java.awt.*;

public class SidebarButton extends JButton {

    public SidebarButton(String text) {
        super(text);
        initializeButton();
    }

    private void initializeButton() {
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(Color.WHITE);
        setForeground(new Color(60, 120, 200));
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Subtle border
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
