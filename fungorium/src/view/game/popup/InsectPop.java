package view.game.popup;

import controller.Action;
import controller.Controller;
import model.Game;
import model.core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Az {@code InsectPop} egy felugró panel, amely interakciós lehetőségeket kínál egy rovarhoz tartozó műveletekhez.
 *
 * <p>A panel három fő műveletet kínál:
 * <ul>
 *     <li>{@code Move} – rovar mozgatása</li>
 *     <li>{@code Eat} – rovar etetése spórával</li>
 *     <li>{@code Cut} – rovar általi szál elvágása</li>
 * </ul>
 * A kiválasztott művelet a {@link Controller}-ben kerül beállításra.</p>
 */
public class InsectPop extends JPanel {

    /** A rovarhoz tartozó játékelem (GameObject) referenciája. */
    private final GameObject gameObject;

    /**
     * Létrehozza az {@code InsectPop} panelt, amely tartalmazza a műveleti gombokat.
     *
     * @param gameObject a rovarhoz tartozó játékelem, amelyen a műveletek végrehajthatók
     */
    public InsectPop(GameObject gameObject) {
        this.gameObject = gameObject;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        // Műveleti gombok
        JButton moveButton = new JButton("Move");
        JButton eatButton = new JButton("Eat");
        JButton cutButton = new JButton("Cut");

        // Gombok méretének beállítása
        Dimension buttonSize = new Dimension(100, 30);
        moveButton.setMaximumSize(buttonSize);
        eatButton.setMaximumSize(buttonSize);
        cutButton.setMaximumSize(buttonSize);

        // MOVE művelet
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.MOVE);
                Controller.setSelected(gameObject);
            }
        });

        // EAT művelet
        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.EAT);
                Controller.setSelected(gameObject);
            }
        });

        // CUT művelet
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.CUT);
                Controller.setSelected(gameObject);
            }
        });

        // Gombok hozzáadása a panelhez
        add(moveButton);
        add(eatButton);
        add(cutButton);
    }
}
