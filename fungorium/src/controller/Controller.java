package controller;

import model.Game;
import view.View;
import view.game.MapPanel;
import view.game.contentpanel.ContentPanel;


public class Controller {
    private ButtonPressed selectedButton;

    public enum ButtonPressed {
        THROW_SPORE,
        GROW_THREAD,
        EAT_INSECT,
        MOVE,
        EAT,
        CUT,
        DEFAULT,
    }

    private static Controller instance;
    private final Game game;
    private final MapPanel mapPanel;
    private final ContentPanel contentPanel;
    private final View view;

    public Controller() {
        this.game = new Game();
        this.contentPanel = new ContentPanel();
        this.mapPanel = new MapPanel(game);
        this.view = new View(game, mapPanel, contentPanel);
    }

    public static Controller getInstance() {
        if (instance == null) instance = new Controller();

        return instance;
    }

    public static Game getGame() {
        return instance.game;
    }

    public static View getView() {
        return instance.view;
    }

    public static MapPanel getMapPanel() {
        return instance.mapPanel;
    }

    public static ContentPanel getTectonContent() {
        return instance.contentPanel;
    }

    public void handleButtonPress(ButtonPressed button) {
        this.selectedButton = button;

    }
    public ButtonPressed getSelectedButton() {
        return selectedButton;
    }

}
