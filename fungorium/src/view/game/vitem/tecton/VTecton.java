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
    int diameter = 50;
    private int x, y;
    private final Color color = Color.BLUE;
    private final List<VMushroomThreadConnection> threads = new ArrayList<>();
    private final List<VInsect> insects = new ArrayList<>();
    private final List<VSpore> spores = new ArrayList<>();
    private VMushroomStem stem;

    public VTecton(int x, int y, String toolTipText) {
        super(x, y, toolTipText);
        imagePath = "resources/images/mushroom.png";
        loadImage();
    }


    public void addThread(VMushroomThreadConnection thread) {
        if (thread != null) threads.add(thread);
    }

    public void addInsect(VInsect insect) {
        if (insect != null) insects.add(insect);
    }

    public void addSpore(VSpore spore) {
        if (spore != null) spores.add(spore);
    }

    public void setStem(VMushroomStem mushroomStem) {
        if (mushroomStem != null) this.stem = mushroomStem;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
    }

    public void drawContent(){

    }

    public boolean contains(Point p) {
        int dx = p.x - x;
        int dy = p.y - y;
        int radius = diameter / 2;
        return dx * dx + dy * dy <= radius * radius;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getThreads() {
        return threads.size();
    }
}