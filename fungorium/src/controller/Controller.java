package controller;

import java.awt.List;

import model.Game;
import model.core.GameObject;
import view.game.MapPanel;
import view.game.contentpanel.ContentPanel;
import view.game.sidebar.ChatLogPanel;
import view.*;
import java.util.ArrayList;

import controller.visitor.NewObjectVisitor;
import model.tecton.*;
public class Controller {
    private static Controller instance;
    private Game game;
    private final MapPanel mapPanel;
    private final ContentPanel contentPanel;
    private final View view;

    private Action action;
    private GameObject selected;

    private ChatLogPanel chatPanel = null;
    public static void setChatPanel(ChatLogPanel p) {
        instance.chatPanel = p;
    }

    public static void log(String s) {
        if (instance.chatPanel != null) {
            instance.chatPanel.addLine(s);
        }
    }

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

    public static void setGame(Game game) {
        instance.game = game;
        instance.mapPanel.setGame(game);

        NewObjectVisitor v = new NewObjectVisitor();
        for (Tecton t : game.getMap().tectons) {
            t.accept(v);
        }
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
