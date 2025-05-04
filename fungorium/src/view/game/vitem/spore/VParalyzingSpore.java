package view.game.vitem.spore;

public class VParalyzingSpore extends VSpore {
    public VParalyzingSpore(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "images/spore/paralyzing_spore.png";
        loadImage();
    }
}
