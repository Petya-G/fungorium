package view.game.PopUps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MushroomPop extends JPanel{
    public MushroomPop() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));


        JButton sporeButton = new JButton("Throw Spore");


        //sporeButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        Dimension buttonSize = new Dimension(110, 30);

        sporeButton.setPreferredSize(buttonSize);


        sporeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: meghívni a függvényt, kitalálni, hogy hova fog dobni
                System.out.println("spore Gomb lett megnyomva.");
            }
        });



        add(sporeButton);



    }
}
