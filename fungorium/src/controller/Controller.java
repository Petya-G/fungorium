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

/**
 * A Controller osztály a játék MVC architektúrájának vezérlő (controller) eleme.
 *
 * Egyetlen példányban (Singleton) létezik, és központi szerepet tölt be a játéklogika,
 * a nézet frissítése, a kiválasztott objektumok és a felhasználói műveletek kezelésében.
 */
public class Controller {

    /** Az osztály singleton példánya. */
    private static Controller instance;

    /** A játékmodell példánya. */
    private Game game;

    /** A játéktér (térkép) megjelenítéséért felelős panel. */
    private final MapPanel mapPanel;

    /** Az aktuális tecton tartalom megjelenítéséért felelős panel. */
    private final ContentPanel contentPanel;

    /** A fő nézet, amely tartalmazza az összes GUI elemet. */
    private final View view;

    /** A felhasználó által kiválasztott művelet típusa. */
    private Action action;

    /** A felhasználó által kiválasztott objektum. */
    private GameObject selected;

    /** A GUI chatpanel (ha van). */
    private ChatLogPanel chatPanel = null;

    /** GUI frissítésre képes komponensek listája. */
    ArrayList<IUpdateGUI> updateableGUIs = new ArrayList<>();

    /**
     * Beállítja a GUI chat panelt.
     *
     * @param p a beállítandó chat panel
     */
    public static void setChatPanel(ChatLogPanel p) {
        instance.chatPanel = p;
    }

    /**
     * Sor hozzáadása a chat naplóhoz.
     *
     * @param s a kiírandó szöveg
     */
    public static void log(String s) {
        if (instance.chatPanel != null) {
            instance.chatPanel.addLine(s);
        }
    }

    /**
     * A Controller osztály konstruktora.
     * Inicializálja a játékot és a GUI elemeket.
     */
    public Controller() {
        instance = this;
        this.game = new Game();
        this.contentPanel = new ContentPanel();
        this.mapPanel = new MapPanel(game);
        this.view = new View(game, mapPanel, contentPanel);
    }

    /**
     * Frissíti a teljes nézetet, újrarajzolja a GUI-t.
     */
    public static void refreshView() {
        for (IUpdateGUI e : instance.updateableGUIs) {
            e.GUIupdate();
        }
        instance.view.revalidate();
        instance.view.repaint();
    }

    /**
     * Visszaadja a Controller singleton példányát.
     *
     * @return az aktuális Controller példány
     */
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    /**
     * Visszaadja az aktuális játékmodellt.
     *
     * @return a játék példány
     */
    public static Game getGame() {
        return instance.game;
    }

    /**
     * Beállítja az aktuális játékot, és újrarendereli a térképet.
     *
     * @param game az új játék példány
     */
    public static void setGame(Game game) {
        instance.game = game;
        instance.mapPanel.setGame(game);

        NewObjectVisitor v = new NewObjectVisitor();
        for (Tecton t : game.getMap().tectons) {
            t.accept(v);
        }
    }

    /**
     * Visszaadja a fő nézetet.
     *
     * @return a View példány
     */
    public static View getView() {
        return instance.view;
    }

    /**
     * Visszaadja a játéktér (térkép) paneljét.
     *
     * @return a MapPanel példány
     */
    public static MapPanel getMapPanel() {
        return instance.mapPanel;
    }

    /**
     * Visszaadja a tecton tartalom paneljét.
     *
     * @return a ContentPanel példány
     */
    public static ContentPanel getTectonContent() {
        return instance.contentPanel;
    }

    /**
     * Visszaadja az aktuálisan kiválasztott akciót.
     *
     * @return a kiválasztott Action
     */
    public static Action getAction() {
        return instance.action;
    }

    /**
     * Beállítja az aktuális műveletet.
     *
     * @param action a kiválasztott akció
     */
    public static void setAction(Action action) {
        instance.action = action;
    }

    /**
     * Visszaadja az aktuálisan kiválasztott játékelemet.
     *
     * @return a kiválasztott GameObject
     */
    public static GameObject getSelected() {
        return instance.selected;
    }

    /**
     * Beállítja az aktuálisan kiválasztott játékelemet.
     *
     * @param selected a kiválasztott objektum
     */
    public static void setSelected(GameObject selected) {
        instance.selected = selected;
    }

    /**
     * GUI frissítésre képes elem regisztrálása.
     *
     * @param e a regisztrálandó GUI komponens
     */
    public static void subscribeGUIUpdate(IUpdateGUI e) {
        instance.updateableGUIs.add(e);
    }
}
