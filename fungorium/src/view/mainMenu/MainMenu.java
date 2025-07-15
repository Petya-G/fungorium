package view.mainMenu;

import controller.Controller;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A {@code MainMenu} osztály a játék főmenüjét valósítja meg, ahol a játékos új játékot indíthat, mentést tölthet be vagy kiléphet.
 *
 * <p>A menü vizuális elemei gombok formájában jelennek meg egy háttérképen, BoxLayout elrendezésben.
 * A gombok különböző játékállapotokat indítanak el a {@link Controller} segítségével.</p>
 */
public class MainMenu extends JPanel {

    /** A főmenü háttérképe. */
    private BufferedImage backgroundImage;

    /**
     * Létrehozza a főmenü panelt, betölti a háttérképet, és elhelyezi a vezérlőgombokat.
     */
    public MainMenu() {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/icons/menu/MainMenu2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230)); // Világoskék háttér

        // Függőleges térköz a gombok felett
        add(Box.createVerticalStrut(300));

        Dimension buttonSize = new Dimension(360, 50);

        // Játék betöltése gomb
        add(new MenuButton("Load Game", buttonSize, e -> {
            JFileChooser j = new JFileChooser();
            j.showOpenDialog(null);

            try {
                FileInputStream file = new FileInputStream(j.getSelectedFile());
                ObjectInputStream in = new ObjectInputStream(file);

                Controller.setGame((model.Game) in.readObject());

                in.close();
                file.close();

                Controller.getView().showPanel("gameView");
                Controller.refreshView();
            } catch (Exception exc) {
                System.out.println(exc);
            }
        }));
        add(Box.createVerticalStrut(20));

        // 4 játékos új játék
        add(new MenuButton("Start New 4 Player Game", buttonSize, e -> {
            Controller.getGame().startGame(4);
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));

        // 6 játékos új játék
        add(new MenuButton("Start New 6 Player Game", buttonSize, e -> {
            Controller.getGame().startGame(6);
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));

        // 8 játékos új játék
        add(new MenuButton("Start New 8 Player Game", buttonSize, e -> {
            Controller.getGame().startGame(8);
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));
/*
        // Tesztjáték (ideiglenes funkció)
        add(new MenuButton("Start test game", buttonSize, e -> {
            Controller.getGame().startTestGame2();
            Controller.getView().showPanel("gameView");
            Controller.refreshView();
        }));
        add(Box.createVerticalStrut(20));
*/
        // Kilépés gomb
        add(new MenuButton("Exit Game", buttonSize, e -> {
            System.exit(0);
        }));
    }

    /**
     * A háttérkép kirajzolása a teljes panel méretében.
     *
     * @param g a grafikus kontextus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
