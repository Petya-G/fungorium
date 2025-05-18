package view.game.sidebar;

import javax.swing.*;
import java.awt.*;

/**
 * A {@code SidebarButton} egy stilizált gomb, amelyet a játék oldalsávjában (pl. {@code ButtonsPanel}) használnak.
 *
 * <p>Letisztult megjelenést és egységes formázást biztosít a többi standard gombhoz képest:
 * <ul>
 *     <li>Fehér háttér</li>
 *     <li>Kék szöveg</li>
 *     <li>Finom szürke szegély</li>
 *     <li>Egér felett kézkurzor</li>
 * </ul>
 * </p>
 */
public class SidebarButton extends JButton {

    /**
     * Létrehozza a {@code SidebarButton} példányt a megadott felirattal, előre definiált stílussal.
     *
     * @param text a gombon megjelenő szöveg
     */
    public SidebarButton(String text) {
        super(text);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(Color.WHITE);
        setForeground(new Color(60, 120, 200)); // Kék szöveg
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Finom szegély
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Egér felett kéz ikon
    }
}
