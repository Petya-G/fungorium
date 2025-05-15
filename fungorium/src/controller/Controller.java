package controller;

import java.awt.List;

import model.Game;
import model.core.GameObject;
import view.View;
import view.game.MapPanel;
import view.game.contentpanel.ContentPanel;
import view.*;
import java.util.ArrayList;
public class Controller {
    private static Controller instance;
    private final Game game;
    private final MapPanel mapPanel;
    private final ContentPanel contentPanel;
    private final View view;

    private Action action;
    private GameObject selected;

    public Controller() {
        instance = this;
        this.game = new Game();
        this.contentPanel = new ContentPanel();
        this.mapPanel = new MapPanel(game);
        this.view = new View(game, mapPanel, contentPanel);
    }

    public static void refreshView() {
        for(IUpdateGUI e : instance.updateableGUIs) {
            e.GUIupdate();
        }
        instance.view.revalidate();
        instance.view.repaint();
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

    public static Action getAction() {
        return instance.action;
    }

    public static void setAction(Action action) {
        instance.action = action;
    }

    public static GameObject getSelected() {
        return instance.selected;
    }

    public static void setSelected(GameObject selected) {
        instance.selected = selected;
    }
    
    ArrayList<IUpdateGUI> updateableGUIs = new ArrayList<IUpdateGUI>();
    public static void subscribeGUIUpdate(IUpdateGUI e) {
        instance.updateableGUIs.add(e);
    }
}
