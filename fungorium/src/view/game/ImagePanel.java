package view.game;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;
    private int x, y;

    public ImagePanel(String imagePath) {
        img = new ImageIcon(imagePath).getImage();
        setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, x, y, this);
    }
}
