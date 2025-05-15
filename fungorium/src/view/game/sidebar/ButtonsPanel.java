package view.game.sidebar;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
                /*Controller.resetGame();
                Controller.getView().showPanel("mainMenu");
                Controller.refreshView();*/
                System.exit(0);
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getGame().endTurn();
                Controller.refreshView();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser();
                j.showSaveDialog(null);

                try {
                    FileOutputStream file = new FileOutputStream(j.getSelectedFile());
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    out.writeObject(Controller.getGame());

                    out.close();
                    file.close();
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }
        });
    }
}