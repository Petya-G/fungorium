package view.game.vitem;

import javax.swing.*;
import java.awt.*;

public abstract class VItem extends JPanel {
    protected Image image;
    protected String imagePath;
    protected int x, y;
    protected String toolTipText;

    protected VItem(int x, int y, String toolTipText) {
        this.x = x;
        this.y = y;
        this.toolTipText = toolTipText;
        setToolTipText(toolTipText);
    }

    protected void loadImage() {
        this.image = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
