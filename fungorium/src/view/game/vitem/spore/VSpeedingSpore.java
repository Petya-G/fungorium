package view.game.vitem.spore;

public class VSpeedingSpore extends VSpore {
    public VSpeedingSpore(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "speeding-spore.png";
        loadImage();
    }
}
