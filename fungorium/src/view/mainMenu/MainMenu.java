package view.mainMenu;

import controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends JPanel {

    private BufferedImage backgroundImage;

    public MainMenu() {

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/icons/menu/MainMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));


        add(Box.createVerticalStrut(300));

        Dimension buttonSize = new Dimension(260, 50);

        add(new MenuButton("Load Game", buttonSize, e -> {
            System.out.println("Todo: Load Game");
            // TODO: Load game
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 4 Player Game", buttonSize, e -> {
            Controller.startGame(4);
            Controller.getView().showPanel("gameView");
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 6 Player Game", buttonSize, e -> {
            Controller.startGame(6);
            Controller.getView().showPanel("gameView");
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 8 Player Game", buttonSize, e -> {
            Controller.startGame(8);
            Controller.getView().showPanel("gameView");
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Exit Game", buttonSize, e -> {
            System.exit(0);
        }));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
