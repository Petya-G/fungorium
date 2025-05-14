package view.game.PopUps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsectPop extends JPanel {
    public InsectPop() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        JButton moveButton = new JButton("Move");
        JButton eatButton = new JButton("Eat");
        JButton cutButton= new JButton("Cut");

        Dimension buttonSize = new Dimension(100, 30);

        moveButton.setMaximumSize(buttonSize);
        eatButton.setMaximumSize(buttonSize);
        cutButton.setMaximumSize(buttonSize);


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
