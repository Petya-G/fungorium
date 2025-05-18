package view.game.winner;

import controller.Controller;
import model.Game;
import model.core.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

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

        List<Player> winners = game.getWinners();


        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font font = new Font("SansSerif", Font.BOLD, 48);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();

        int lineHeight = fm.getHeight();
        int totalHeight = lineHeight * winners.size();

        int y = (getHeight() - totalHeight) / 2 + fm.getAscent();

        for (Player winner : winners) {
            String text = winner.getName() + " wins!";
            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;

            g2d.drawString(text, x, y);
            y += lineHeight;
        }
    }
}
