package view.game.vitem.tecton;

import view.game.vitem.VInsect;
import view.game.vitem.VItem;
import view.game.vitem.VMushroomStem;
import view.game.vitem.VMushroomThreadConnection;
import view.game.vitem.spore.VSpore;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class VTecton extends VItem {
    public VTecton(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "resources/images/mushroom.png";
        loadImage();
    }

    public void drawContent(){

    }
}