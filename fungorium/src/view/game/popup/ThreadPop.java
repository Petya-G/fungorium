package view.game.popup;

import controller.Action;
import controller.Controller;
import model.core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadPop extends JPanel {
    private final GameObject gameObject;

    public ThreadPop(GameObject gameObject) {
        this.gameObject = gameObject;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        JButton threadButton = new JButton("Grow Thread");
        JButton eatButton = new JButton("Eat Insect");

        threadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        eatButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(110, 30);

        threadButton.setPreferredSize(buttonSize);
        eatButton.setPreferredSize(buttonSize);

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

        add(threadButton);
        add(eatButton);
    }
}