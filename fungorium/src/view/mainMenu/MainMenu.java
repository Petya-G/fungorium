package view.mainMenu;

import controller.Controller;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private JFrame frame;

    public MainMenu() {
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setVisible(true);

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
        loadGame.addActionListener((ActionEvent e) -> {
            System.out.println("Todo:Load Game");
            //TODO: Load game
        });

        JButton startButton4 = new JButton("Start New 4 Player Game");
        startButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton4.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton4.setMaximumSize(new Dimension(200, 50));
        startButton4.addActionListener((ActionEvent e) -> {
            frame.dispose();
            Game game = new Game();
            Controller.startGame(game,4);
        });

        JButton startButton6 = new JButton("Start New 6 Player Game");
        startButton6.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton6.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton6.setMaximumSize(new Dimension(200, 50));
        startButton6.addActionListener((ActionEvent e) -> {
            frame.dispose();
            Game game = new Game();
            Controller.startGame(game,6);
        });

        JButton startButton8 = new JButton("Start New 8 Player Game");
        startButton8.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton8.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton8.setMaximumSize(new Dimension(200, 50));
        startButton8.addActionListener((ActionEvent e) -> {
            frame.dispose();
            Game game = new Game();
            Controller.startGame(game,8);
        });

        add(loadGame);
        add(Box.createVerticalStrut(20));
        add(startButton4);
        add(Box.createVerticalStrut(20));
        add(startButton6);
        add(Box.createVerticalStrut(20));
        add(startButton8);
    }
}
