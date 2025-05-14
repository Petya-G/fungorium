package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MushroomerPop extends JPanel{
    public MushroomerPop() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));


        JButton sporeButton = new JButton("Throw Spore");
        JButton threadButton = new JButton("Grow Thread");

        sporeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        threadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(150, 30);

        sporeButton.setPreferredSize(buttonSize);
        threadButton.setPreferredSize(buttonSize);

        sporeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a függvényt, kitalálni, hogy hova fog dobni
                System.out.println("spore Gomb lett megnyomva.");
            }
        });


        threadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a függvényt, kitalálni, hogy hova fog nőni
                System.out.println("thread  Gomb lett megnyomva.");
            }
        });
        add(sporeButton);
        add(threadButton);


    }
}
