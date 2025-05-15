package view.mainMenu;

import controller.Controller;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel {

    private BufferedImage backgroundImage;

    public MainMenu() {

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/icons/menu/MainMenu2.png"));
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
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 4 Player Game", buttonSize, e -> {
            Controller.getGame().startGame(4);
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 6 Player Game", buttonSize, e -> {
            Controller.getGame().startGame(6);
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));

        add(new MenuButton("Start New 8 Player Game", buttonSize, e -> {
            Controller.getGame().startGame(8);
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));

        //TODO ki lehet venni, megjelenites teszt
        add(Box.createVerticalStrut(20));
        add(new MenuButton("Start test game", buttonSize, e -> {
            Controller.getGame().startTestGame2();;
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
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
