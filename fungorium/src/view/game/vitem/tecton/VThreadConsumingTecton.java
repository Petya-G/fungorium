package view.game.vitem.tecton;

public class VThreadConsumingTecton extends VTecton{
    public VThreadConsumingTecton(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "images/tecton/thread_consuming.png";
        loadImage();
    }
}
