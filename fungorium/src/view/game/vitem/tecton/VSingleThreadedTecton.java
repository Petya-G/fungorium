package view.game.vitem.tecton;

public class VSingleThreadedTecton extends VTecton{
    public VSingleThreadedTecton(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "images/tecton/single_threaded.png";
        loadImage();
    }
}
