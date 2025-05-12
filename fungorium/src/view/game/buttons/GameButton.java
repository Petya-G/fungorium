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
        if (icon == null || icon.getImage() == null) {
            return null;
        }
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    protected double invCenterX(int x) {
        return (x - size) / (parent.getSize().getWidth() - 2 * size);
    }

    protected double invCenterY(int y) {
        return (y - size) / (parent.getSize().getHeight() - 2 * size);
    }

    protected int CenterX(double coordX) {
        return (int) (coordX * (parent.getSize().getWidth() - 2 * size) + size);
    }

    protected int CenterY(double coordY) {
        return (int) (coordY * (parent.getSize().getHeight() - 2 * size) + size);
    }
}
