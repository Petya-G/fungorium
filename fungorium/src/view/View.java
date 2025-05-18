package view;

import model.Game;
import view.game.GamePanel;
import view.game.MapPanel;
import view.game.contentpanel.ContentPanel;
import view.game.winner.WinnerView;
import view.mainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    MainMenu mainMenu;
    GamePanel gamePanel;
    MapPanel mapPanel;
    WinnerView winnerView;

    public View(Game game, MapPanel mapPanel, ContentPanel contentPanel) {
        super("Fungorium");
        gamePanel = new GamePanel(game, mapPanel, contentPanel);
        this.mainMenu = new MainMenu();
        this.mapPanel = mapPanel;
        winnerView = new WinnerView();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        //TODO icon

        cardPanel.add("mainMenu", mainMenu);
        cardPanel.add("gameView", gamePanel);
        cardPanel.add("winnerView",winnerView);

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

        showPanel("mainMenu");
    }

    public void showPanel(String name) {
        System.out.println("showing panel " + name);
        cardLayout.show(cardPanel, name);
    }
}