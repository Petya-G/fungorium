package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Controller;
import model.mushroom.spore.Spore;

public class SporeButton extends GameButton {
    public Spore spore;

    public SporeButton(Spore spore, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.spore = spore;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialMousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                initialMousePoint = e.getPoint();

                if (Controller.getInstance().getSelectedButton() == Controller.ButtonPressed.EAT) {
                    System.out.println("A Eat gomb van kiválasztva! Ezt a spórát akarom megenni");
                    //TODO: megcsinálni
                }
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.DEFAULT);
            }

        };

        addMouseListener(mouseAdapter);
    }
}
