package view.mainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A {@code MenuButton} egy stilizált gomb, amelyet a főmenüben használnak játékindításhoz, betöltéshez vagy kilépéshez.
 *
 * <p>Egységes megjelenést biztosít: méret, betűtípus, színek, középre igazítás.</p>
 */
public class MenuButton extends JButton {

    /**
     * Létrehozza a {@code MenuButton} példányt a megadott szöveggel, mérettel és eseménykezelővel.
     *
     * @param text a gomb felirata
     * @param size a gomb mérete (preferált és maximális)
     * @param actionListener az eseménykezelő, amely kattintásra lefut (null érték megengedett)
     */
    public MenuButton(String text, Dimension size, ActionListener actionListener) {
        super(text);

        // Méret és igazítás
        setPreferredSize(size);
        setMaximumSize(size);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Megjelenés
        setFont(new Font("Arial", Font.PLAIN, 20));
        setFocusable(false);
        setBackground(new Color(0x3399FF)); // világoskék háttér
        setForeground(Color.BLACK);        // fekete szöveg

        // Eseménykezelő hozzáadása, ha van
        if (actionListener != null) {
            addActionListener(actionListener);
        }
    }
}
