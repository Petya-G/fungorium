package view.mainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuButton extends JButton {
    public MenuButton(String text, Dimension size, ActionListener actionListener) {
        super(text);
        setPreferredSize(size);
        setMaximumSize(size);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setFocusable(false);
        setBackground(new Color(0x81B64C));
        setForeground(Color.WHITE);

        setBackground(new Color(0x3399FF));
        setForeground(Color.BLACK);

        if (actionListener != null) {
            addActionListener(actionListener);
        }
    }
}
