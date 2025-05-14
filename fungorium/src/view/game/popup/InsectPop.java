package view.game.popup;

import controller.Action;
import controller.Controller;
import model.core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsectPop extends JPanel {
    private final GameObject gameObject;
    public InsectPop(GameObject gameObject) {
        this.gameObject = gameObject;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        JButton moveButton = new JButton("Move");
        JButton eatButton = new JButton("Eat");
        JButton cutButton = new JButton("Cut");

        Dimension buttonSize = new Dimension(100, 30);

        moveButton.setMaximumSize(buttonSize);
        eatButton.setMaximumSize(buttonSize);
        cutButton.setMaximumSize(buttonSize);

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.MOVE);
                Controller.setSelected(gameObject);
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.EAT);
                Controller.setSelected(gameObject);
            }
        });

        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setAction(Action.CUT);
                Controller.setSelected(gameObject);
            }
        });

        add(moveButton);
        add(eatButton);
        add(cutButton);
    }
}
