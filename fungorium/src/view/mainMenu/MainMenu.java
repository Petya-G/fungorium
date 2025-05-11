package view.mainMenu;

import controller.Controller;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private Controller controller;

    public MainMenu(Controller controller) {
        this.controller = controller;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));

        JLabel fungorium = new JLabel("Fungorium", SwingConstants.CENTER);
        fungorium.setFont(new Font("Arial", Font.BOLD, 40));
        fungorium.setForeground(Color.WHITE);
        fungorium.setOpaque(true);
        fungorium.setBackground(new Color(0, 0, 100));
        fungorium.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(fungorium);
        add(Box.createVerticalStrut(30));

        JButton loadGame = new JButton("Load Game");
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setFont(new Font("Arial", Font.PLAIN, 20));
        loadGame.setMaximumSize(new Dimension(200, 50));
        loadGame.addActionListener(e -> {
            System.out.println("Todo: Load Game");
        });

        JButton startButton4 = createStartButton("Start New 4 Player Game", 4);
        JButton startButton6 = createStartButton("Start New 6 Player Game", 6);
        JButton startButton8 = createStartButton("Start New 8 Player Game", 8);

        add(loadGame);
        add(Box.createVerticalStrut(20));
        add(startButton4);
        add(Box.createVerticalStrut(20));
        add(startButton6);
        add(Box.createVerticalStrut(20));
        add(startButton8);
    }

    private JButton createStartButton(String text, int players) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setMaximumSize(new Dimension(250, 50));
        button.addActionListener(e -> {
            Game game = new Game();
            Controller.startGame(game, players);
            controller.getView().showPanel("gameView");
        });
        return button;
    }
}
