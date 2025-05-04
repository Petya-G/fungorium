package view.game.vitem;

public class VMushroomThread extends VItem {
    protected VMushroomThread(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "images/mushroomThread.png";
        loadImage();
    }
}
