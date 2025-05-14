package view.game.PopUps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadPop extends JPanel {
    public ThreadPop() {
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
                //TODO: meghívni a függvényt, kitalálni, hogy hova fog nőni
                System.out.println("thread  Gomb lett megnyomva.");
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a függvényt, kitalálni, hogy hova fog nőni
                System.out.println("eat  Gomb lett megnyomva.");
            }
        });

        add(threadButton);
        add(eatButton);
    }
}