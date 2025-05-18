package view.game.winner;

import controller.Controller;
import model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WinnerView extends JPanel {

    private BufferedImage backgroundImage;
    Game game;
    public WinnerView() {
        this.game = Controller.getGame();

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/icons/menu/winnerview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        //TODO: we have to check who won
        String text =  game.getCurrentPlayer().getName() + " wins!";

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font font = new Font("SansSerif", Font.BOLD, 48);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2;

        g2d.drawString(text, x, y);
    }
}
