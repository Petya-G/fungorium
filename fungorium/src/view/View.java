package view;

import controller.Controller;
import model.*;
import view.game.GamePanel;
import view.game.MapPanel;
import view.mainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Game game;
    CardLayout cardLayout = new CardLayout();
    private Controller controller;
    JPanel cardPanel = new JPanel(cardLayout);
    MainMenu mainMenu ;
    GamePanel gamePanel;
    MapPanel mapPanel;

    public View(Game game, MapPanel mapPanel,Controller controller) {
        super("Fungorium");
        gamePanel = new GamePanel(game, mapPanel);
        this.controller = controller;
        this.game = game;
        this.mainMenu = new MainMenu(controller);
        this.mapPanel = mapPanel;

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

    public MapPanel getMapPanel()
    {
        return mapPanel;
    }

}