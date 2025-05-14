package view.game.PopUps;

import controller.Controller;

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
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.GROW_THREAD);
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.EAT_INSECT);
            }
        });

        add(threadButton);
        add(eatButton);
    }
}