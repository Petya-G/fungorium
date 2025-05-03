package view;

import controller.Game;
import view.game.GamePanel;
import view.mainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    MainMenu mainMenu = new MainMenu();
    GamePanel gamePanel = new GamePanel();

    public Window() {
        super("Fungorium");
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
        cardLayout.show(cardPanel, name);
    }

    public static void main(String[] args) {
        Game game = new Game();
        Window window = new Window();
    }
}