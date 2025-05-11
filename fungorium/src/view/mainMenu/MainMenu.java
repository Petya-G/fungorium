package view.mainMenu;

import controller.Controller;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));

        // Cím
        JLabel fungorium = new JLabel("Fungorium", SwingConstants.CENTER);
        fungorium.setFont(new Font("Arial", Font.BOLD, 40));
        fungorium.setForeground(Color.WHITE);
        fungorium.setOpaque(true);
        fungorium.setBackground(new Color(0, 0, 100));
        fungorium.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(fungorium);
        add(Box.createVerticalStrut(30));

        // Load Game gomb
        JButton loadGame = new JButton("Load Game");
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setFont(new Font("Arial", Font.PLAIN, 20));
        loadGame.setMaximumSize(new Dimension(200, 50));
        loadGame.addActionListener(e -> {
            System.out.println("Todo: Load Game");
            //TODO: Load game
        });
        add(loadGame);
        add(Box.createVerticalStrut(20));

        // Start New 4 Player Game gomb
        JButton startButton4 = new JButton("Start New 4 Player Game");
        startButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton4.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton4.setMaximumSize(new Dimension(250, 50));

        startButton4.addActionListener(e -> {
            Controller.getInstance().startGame(4);
            Controller.getInstance().getView().showPanel("gameView");
        });

        add(startButton4);
        add(Box.createVerticalStrut(20));

        // Start New 6 Player Game gomb
        JButton startButton6 = new JButton("Start New 6 Player Game");
        startButton6.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton6.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton6.setMaximumSize(new Dimension(250, 50));

        startButton6.addActionListener(e -> {
            Controller.getInstance().startGame(6);
            Controller.getInstance().getView().showPanel("gameView");

        });
        add(startButton6);
        add(Box.createVerticalStrut(20));


        JButton startButton8 = new JButton("Start New 8 Player Game");
        startButton8.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton8.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton8.setMaximumSize(new Dimension(250, 50));

        startButton8.addActionListener(e -> {
            Controller.getInstance().startGame(8);
            Controller.getInstance().getView().showPanel("gameView");

        });
        add(startButton8);
    }
}
