package view.game.vitem;

public class VMushroomStem extends VItem {
    public VMushroomStem(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "images/mushroomStem.png";
        loadImage();
    }
}
