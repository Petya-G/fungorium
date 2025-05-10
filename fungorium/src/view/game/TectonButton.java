package view.game;

import model.tecton.Tecton;
import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.*;

public class TectonButton extends JButton {
    static int size = 30;
    Tecton tecton;
    ImageIcon imageIcon;
    MapPanel parent;

    static int tectonWidth = 100;
    static int tectonHeight = 100;

    

    int tectonCenterX(double coordX) {
        return (int) (coordX * (parent.getSize().getWidth() - tectonWidth) + tectonWidth / 2);
    }

    int tectonCenterY(double coordY) {
        return (int) (coordY * (parent.getSize().getHeight() - tectonHeight) + tectonHeight / 2);
    }

    public TectonButton(Tecton tecton, ImageIcon imageIcon, MapPanel parent) {
        super(imageIcon);
        this.parent = parent;
        this.tecton = tecton;
        this.imageIcon = imageIcon;
        setBounds(tectonCenterX((int)tecton.getPosX()), tectonCenterY((int)tecton.getPosY()), size, size);
        setIcon(imageIcon);
    }

    // ez kell, ne szedd ki, map generalas egyszerubb mintha konstruktorban lenne ez, ha nem hiszed kerdezd meg. Akos
    public void refreshState() {
        
        setLocation(tectonCenterX(tecton.getPosX()) - size/2, tectonCenterY(tecton.getPosY()) - size/2);
    }

    
}
