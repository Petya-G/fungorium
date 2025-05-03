package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsecterPop extends JPanel {
    public InsecterPop() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));  // Vízszintes elrendezés
        setBackground(new Color(240, 240, 240));
        JButton moveButton = new JButton("Move");
        JButton eatButton = new JButton("Eat");
        JButton cutButton= new JButton("Cut");
        moveButton.setPreferredSize(new Dimension(800, 30));
        eatButton.setPreferredSize(new Dimension(800, 30));
        cutButton.setPreferredSize(new Dimension(800, 30));

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a föggvényt, kitalálni, hogy hova fog mozogni
                System.out.println("move Gomb lett megnyomva.");
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a függvényt
                System.out.println("eat Gomb lett megnyomva.");
            }
        });
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a függvényt
                System.out.println("cut Gomb lett megnyomva.");
            }
        });


        add(moveButton);
        add(eatButton);
        add(cutButton);
    }
}
