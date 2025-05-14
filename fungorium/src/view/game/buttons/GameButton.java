package view.game.buttons;

import javax.swing.*;
import java.awt.*;

public abstract class GameButton extends JButton {
    protected static final int size = 50;
    protected ImageIcon imageIcon;
    protected JPanel parent;

    public GameButton(ImageIcon imageIcon, JPanel parent) {
        super(scaleImageIcon(imageIcon));
        this.imageIcon = imageIcon;
        this.parent = parent;
        setPreferredSize(new Dimension(size, size));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
    }

    protected static ImageIcon scaleImageIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}