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

/**
 * A {@code WinnerView} egy grafikus panel, amely a játék végét követően megjeleníti a győztes(ek) nevét.
 *
 * <p>Háttérként egy előre definiált kép szolgál, amelyre nagy betűkkel rákerülnek a nyertes játékosok nevei.</p>
 */
public class WinnerView extends JPanel {

    /** A háttérkép, amelyre a győztesek szövege rákerül. */
    private BufferedImage backgroundImage;

    /** A játék példány, amelyből a győztesek lekérhetők. */
    Game game;

    /**
     * Létrehozza a {@code WinnerView} panelt, betölti a háttérképet és elmenti a játékreferenciát.
     */
    public WinnerView() {
        this.game = Controller.getGame();

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/icons/menu/winnerview.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Hibakezelés: konzolra írjuk a problémát
        }
    }

    /**
     * A panel kirajzolása, beleértve a háttérképet és a győztes(ek) nevét.
     *
     * @param g a grafikus kontextus, amelyre rajzolunk
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Player> winners = game.getWinners();

        // Háttérkép kirajzolása, ha van
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        // Betűtípus és stílus beállítása
        Font font = new Font("SansSerif", Font.BOLD, 48);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        int lineHeight = fm.getHeight();
        int totalHeight = lineHeight * winners.size();

        // A szöveg függőleges pozíciójának kiszámítása, hogy középre kerüljön
        int y = (getHeight() - totalHeight) / 2 + fm.getAscent();

        // Győztes(ek) nevének megjelenítése középre igazítva
        for (Player winner : winners) {
            String text = winner.getName() + " wins!";
            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;

            g2d.drawString(text, x, y);
            y += lineHeight;
        }
    }
}
