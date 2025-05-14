package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import controller.Controller;
import model.insect.Insect;
import view.game.PopUps.InsectPop;

public class InsectButton extends GameButton {
    public Insect insect;

    public InsectButton(Insect insect, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.insect = insect;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialMousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                initialMousePoint = e.getPoint();

                //TODO: megcsinálni, hogy csak akkor jelenjen meg ha rovarász játszik és a saját rovarjára kattint

                InsectPop popupPanel = new InsectPop();
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(popupPanel);

                popupMenu.show(InsectButton.this, InsectButton.this.getWidth(), -20);

                if (Controller.getInstance().getSelectedButton() == Controller.ButtonPressed.EAT_INSECT) {
                    System.out.println("A Eat insect gomb van kiválasztva! Ezt a rovart akarom megenni");
                }
            }

        };

        addMouseListener(mouseAdapter);
    }
}
