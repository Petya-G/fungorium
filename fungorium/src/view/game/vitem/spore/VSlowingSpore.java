package view.game.vitem.spore;

public class VSlowingSpore extends VSpore {
    public VSlowingSpore(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "spore/slowing_spore.png";
        loadImage();
    }
}
