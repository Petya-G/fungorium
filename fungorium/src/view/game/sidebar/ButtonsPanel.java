package view.game.sidebar;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {
    SidebarButton endTurnButton = new SidebarButton("End Turn");
    SidebarButton saveButton = new SidebarButton("Save");
    SidebarButton exitButton = new SidebarButton("Exit");

    public ButtonsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 buttons, spaced vertically
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Create and add sidebar buttons
        add(endTurnButton);
        add(saveButton);
        add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getView().showPanel("mainMenu");
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: megcsinálni
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: megcsinálni
            }
        });
    }
}