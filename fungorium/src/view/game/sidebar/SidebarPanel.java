package view.game.sidebar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A {@code SidebarPanel} a játék jobb oldalán elhelyezkedő oldalsáv fő panelje,
 * amely két részből áll:
 * <ul>
 *     <li>{@link ButtonsPanel} – játékmenet vezérlőgombok (pl. mentés, kilépés)</li>
 *     <li>{@link ChatLogPanel} – eseménynapló és üzenetek megjelenítése</li>
 * </ul>
 *
 * <p>Egységes szegélyt és elrendezést biztosít az oldalsáv megjelenéséhez.</p>
 */
public class SidebarPanel extends JPanel {

    /** A játékvezérlő gombokat tartalmazó panel. */
    ButtonsPanel buttonsPanel = new ButtonsPanel();

    /** A játék eseménynaplóját megjelenítő panel. */
    ChatLogPanel chatLogPanel = new ChatLogPanel();

    /**
     * Létrehozza a {@code SidebarPanel} panelt, beállítja az elrendezést és hozzáadja az alkotóelemeket.
     */
    public SidebarPanel() {
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Options & Chat"));
        setBackground(Color.WHITE);

        // Gombok panel hozzáadása felülre
        add(buttonsPanel, BorderLayout.NORTH);

        // Chat log hozzáadása a középső részre
        add(chatLogPanel, BorderLayout.CENTER);
    }

    /**
     * Egységes címkézett szegély létrehozása.
     *
     * @param title a szegély címe
     * @return a létrehozott {@code Border} objektum
     */
    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }
}
