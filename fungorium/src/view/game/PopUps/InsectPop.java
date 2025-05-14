package view.game.PopUps;

import controller.Controller;

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
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.MOVE);
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.EAT);
            }
        });
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.CUT);
            }
        });


        add(moveButton);
        add(eatButton);
        add(cutButton);
    }
}
