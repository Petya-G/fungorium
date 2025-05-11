package view.mainMenu;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));

        JLabel fungorium = new JLabel("FUNGORIUM", SwingConstants.CENTER);
        fungorium.setFont(new Font("Arial", Font.BOLD, 40));
        fungorium.setForeground(Color.WHITE);
        fungorium.setOpaque(true);
        fungorium.setBackground(new Color(0, 0, 100));
        fungorium.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(50));
        add(fungorium);
        add(Box.createVerticalStrut(100));

        Dimension buttonSize = new Dimension(250, 50);

        add(new MenuButton("Load Game", buttonSize, e -> {
            System.out.println("Todo: Load Game");
            // TODO: Load game
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 4 Player Game", buttonSize, e -> {
            Controller.getInstance().startGame(4);
            Controller.getInstance().getView().showPanel("gameView");
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 6 Player Game", buttonSize, e -> {
            Controller.getInstance().startGame(6);
            Controller.getInstance().getView().showPanel("gameView");
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 8 Player Game", buttonSize, e -> {
            Controller.getInstance().startGame(8);
            Controller.getInstance().getView().showPanel("gameView");
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Exit Game", buttonSize, e -> {
            System.exit(0);
        }));
    }
}
