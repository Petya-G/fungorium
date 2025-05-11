package controller;

import model.Game;
import view.View;
import view.game.MapPanel;
import view.game.MiddlePanel;
import view.mainMenu.MainMenu;

import javax.swing.*;

public class Controller {
    private static Controller instance;
    public Game game;
    public MapPanel mapPanel;
    public View view;
    private JFrame frame;

    public Controller(Game game) {
        instance = this;
        this.game = game;
        this.frame = new JFrame("Fungorium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        this.mapPanel = new MapPanel(game);
        view = new View(game, mapPanel,instance);

        //frame.setContentPane(view);
    }

    public static Controller getInstance() {
        return instance;
    }

    public static void startGame(Game game,int playerCount) {
        instance = new Controller(game);
        game.startGame(playerCount);
        instance.frame.setVisible(true);
    }

    public static void startMainMenu() {
        Controller controller = new Controller(null);
        instance = controller;
        controller.showMainMenu();
    }

    private void showMainMenu() {
        MainMenu mainMenu = new MainMenu(this);
        frame.setContentPane(mainMenu);
        frame.setVisible(true);
    }
    public View getView() {
        return view;
    }

}
