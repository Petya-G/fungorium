package view.game.vitem;

public class VInsect extends VItem {
    public VInsect(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "images/insect.png";
        loadImage();
    }
}
