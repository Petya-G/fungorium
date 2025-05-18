package view.game.sidebar;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A {@code ButtonsPanel} a játék oldalsávjában elhelyezkedő panel, amely három fő funkciógombot tartalmaz:
 * <ul>
 *     <li>{@code End Turn} – a játékos köre véget ér</li>
 *     <li>{@code Save} – a játék állapotának fájlba mentése</li>
 *     <li>{@code Exit} – kilépés az alkalmazásból</li>
 * </ul>
 *
 * Az osztály egyszerű elrendezést és eseménykezelést biztosít a gombokhoz.
 */
public class ButtonsPanel extends JPanel {

    /** Kör befejezése gomb. */
    SidebarButton endTurnButton = new SidebarButton("End Turn");

    /** Játék mentése gomb. */
    SidebarButton saveButton = new SidebarButton("Save");

    /** Kilépés gomb. */
    SidebarButton exitButton = new SidebarButton("Exit");

    /**
     * Létrehozza a {@code ButtonsPanel} példányt, inicializálja és elhelyezi a gombokat.
     */
    public ButtonsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 gomb, függőlegesen elrendezve
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        add(endTurnButton);
        add(saveButton);
        add(exitButton);

        // Kilépés gomb eseménykezelője
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Egyszerű kilépés
            }
        });

        // Kör befejezése gomb eseménykezelője
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getGame().endTurn();
                Controller.refreshView();
            }
        });

        // Mentés gomb eseménykezelője
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser();
                j.showSaveDialog(null);

                try {
                    FileOutputStream file = new FileOutputStream(j.getSelectedFile());
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(Controller.getGame());
                    out.close();
                    file.close();
                } catch (Exception exc) {
                    System.out.println(exc); // Hiba naplózása
                }
            }
        });
    }
}
