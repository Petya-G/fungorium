package view.game.popup;

import controller.Action;
import controller.Controller;
import model.core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A {@code ThreadPop} egy felugró panel, amely egy gombaszálhoz (MushroomThread) tartozó műveleteket kínál.
 *
 * <p>Két fő akciót tesz elérhetővé a játékos számára:</p>
 * <ul>
 *     <li>{@code Grow Thread} – új szál növesztése egy szomszédos Tecton felé</li>
 *     <li>{@code Eat Insect} – rovar elfogyasztása a szál segítségével</li>
 * </ul>
 *
 * A kiválasztott akció a {@link Controller} osztályban kerül beállításra, a kiválasztott játékelemmel együtt.
 */
public class ThreadPop extends JPanel {

    /** A gombaszálhoz tartozó játékelem. */
    private final GameObject gameObject;

    /**
     * Létrehozza a {@code ThreadPop} panelt, amely a szálhoz tartozó műveleti gombokat jeleníti meg.
     *
     * @param gameObject a szálhoz tartozó játékelem
     */
    public ThreadPop(GameObject gameObject) {
        this.gameObject = gameObject;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        // Műveleti gombok létrehozása
        JButton threadButton = new JButton("Grow Thread");
        JButton eatButton = new JButton("Eat Insect");

        // Igazítás és méret
        threadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        eatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension buttonSize = new Dimension(110, 30);
        threadButton.setPreferredSize(buttonSize);
        eatButton.setPreferredSize(buttonSize);

        // Gomb eseménykezelők
        threadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.GROW_THREAD);
                Controller.setSelected(gameObject);
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.EAT_INSECT);
                Controller.setSelected(gameObject);
            }
        });

        // Gombok hozzáadása a panelhez
        add(threadButton);
        add(eatButton);
    }
}
