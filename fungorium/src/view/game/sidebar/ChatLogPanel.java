package view.game.sidebar;

import javax.swing.*;
import javax.swing.border.Border;

import controller.Controller;

import java.awt.*;

/**
 * A {@code ChatLogPanel} osztály egy egyszerű naplópanel, amely szöveges üzeneteket jelenít meg a játékos számára.
 *
 * <p>Tipikus használata: játékbeli események, logok, hibák, vagy visszajelzések megjelenítése.</p>
 * <p>A {@link Controller} segítségével globálisan is elérhető és frissíthető.</p>
 */
public class ChatLogPanel extends JPanel {

    /** A szövegterület, amely a chat naplót tartalmazza. */
    JTextArea chatLog;

    /**
     * Létrehozza a {@code ChatLogPanel} panelt, amely tartalmaz egy görgethető szövegmezőt.
     */
    public ChatLogPanel() {
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Chat Log"));
        setBackground(Color.WHITE);

        // Görgethető, nem szerkeszthető szövegterület létrehozása
        chatLog = new JTextArea(10, 20);
        chatLog.setEditable(false);
        chatLog.setBackground(new Color(245, 245, 245));
        chatLog.setFont(new Font("Arial", Font.PLAIN, 12));
        chatLog.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane chatScrollPane = new JScrollPane(chatLog);
        chatScrollPane.setBorder(null); // Letisztult görgetősáv
        add(chatScrollPane, BorderLayout.CENTER);

        // Beállítás a Controller-en keresztül globális hozzáféréshez
        Controller.setChatPanel(this);
    }

    /**
     * Segédfüggvény egy címkézett szegély létrehozásához.
     *
     * @param title a megjelenítendő cím
     * @return a létrehozott szegély
     */
    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }

    /**
     * Egy új sor hozzáadása a chat loghoz.
     *
     * @param s a megjelenítendő szöveg
     */
    public void addLine(String s) {
        chatLog.append(s + "\n");
    }
}
