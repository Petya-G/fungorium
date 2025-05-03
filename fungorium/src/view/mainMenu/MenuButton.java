package view.mainMenu;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    public MenuButton(String name) {
        super(name);
        this.setPreferredSize(new Dimension(300, 200));
        this.setFocusable(false);
        this.setBackground(new Color(0x81B64C));
        this.setForeground(Color.white);
    }
}