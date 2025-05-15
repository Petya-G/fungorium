package view.game;

import javax.swing.*;

import controller.Controller;
import model.Game;
import model.insect.Insecter;
import model.mushroom.Mushroomer;
import view.IUpdateGUI;
import view.ImageManager;

import java.awt.*;

public class TurnOrderPanel extends JPanel implements IUpdateGUI {

    JLabel gameInfoLabel;
    JLabel img;
    public TurnOrderPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        // Add Game Info Label
        gameInfoLabel = new JLabel("Game Information");
        gameInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gameInfoLabel.setForeground(new Color(60, 60, 60)); // Dark gray
        add(gameInfoLabel);

        img = new JLabel(new ImageIcon());
        //img.setSize(20, 20);
        add(img);

        Controller.subscribeGUIUpdate(this);
    }

    public void GUIupdate() {
        Game game = Controller.getGame();
        String text = "[turn " + game.getTurn() + "] " +  game.getCurrentPlayer().getName();

        ImageIcon ii;
        if (game.getCurrentPlayer().getClass() == Mushroomer.class) {
            int playerId = ((Mushroomer) game.getCurrentPlayer()).getShroomerID();
            ii = new ImageIcon(ImageManager.getIcon("mushroom_stem_" + ((playerId) % 4 + 1)).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } else {
            int playerId = ((Insecter) game.getCurrentPlayer()).getInsecterID();
            String p = "normal_";
            ii = new ImageIcon(ImageManager.getIcon("insect_" + p + ((playerId + 1) % 4 + 1)).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }

        gameInfoLabel.setText(text);
        img.setIcon(ii);
       
    }
}