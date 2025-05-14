package view.game.popup;

import controller.Action;
import controller.Controller;
import model.core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MushroomStemPop extends JPanel {
    private final GameObject gameObject;

    public MushroomStemPop(GameObject gameObject) {
        this.gameObject = gameObject;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));


        JButton sporeButton = new JButton("Throw Spore");
        Dimension buttonSize = new Dimension(110, 30);

        sporeButton.setPreferredSize(buttonSize);

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