package view.game.popup;

import controller.Action;
import controller.Controller;
import model.core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A {@code MushroomStemPop} egy felugró panel, amely egy gombatörzshöz (MushroomStem) kapcsolódó akciót kínál.
 *
 * <p>Jelenleg egyetlen műveletet tartalmaz:
 * <ul>
 *     <li>{@code Throw Spore} – spóra dobása egy másik Tectonra</li>
 * </ul>
 * A művelet kiválasztása után az {@link Controller} osztályban beállításra kerül a megfelelő akció és célobjektum.</p>
 */
public class MushroomStemPop extends JPanel {

    /** A felületen megjelenített játékelem (gombatörzs). */
    private final GameObject gameObject;

    /**
     * Létrehozza a {@code MushroomStemPop} panelt a spóradobási lehetőséggel.
     *
     * @param gameObject a törzshöz tartozó játékelem, amely a művelet forrása
     */
    public MushroomStemPop(GameObject gameObject) {
        this.gameObject = gameObject;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        // Gomb létrehozása a spóra dobásához
        JButton sporeButton = new JButton("Throw Spore");
        Dimension buttonSize = new Dimension(110, 30);
        sporeButton.setPreferredSize(buttonSize);

        // Gomb eseménykezelője
        sporeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.THROW_SPORE);
                Controller.setSelected(gameObject);
            }
        });

        add(sporeButton);
    }
}
