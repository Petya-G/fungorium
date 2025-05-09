package view;

import model.*;
import view.game.GamePanel;
import view.mainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Game game;
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    MainMenu mainMenu = new MainMenu();
    GamePanel gamePanel;

    public View(Game game) {
        super("Fungorium");
        gamePanel = new GamePanel(game);

        this.game = game;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        //TODO icon

        cardPanel.add("mainMenu", mainMenu);
        cardPanel.add("gameView", gamePanel);

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

        showPanel("gameView");
    }

    public void showPanel(String name) {
        System.out.println("showing panel " + name);
        cardLayout.show(cardPanel, name);
    }
}