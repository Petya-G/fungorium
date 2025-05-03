package view.game.entities.tecton;

import view.game.entities.VInsect;
import view.game.entities.VMushroomStem;
import view.game.entities.VMushroomThread;
import view.game.entities.spore.VSpore;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class VTecton {
    int diameter = 50;
    private int x, y;
    private final Color color = Color.BLUE;
    private final List<VMushroomThread> threads = new ArrayList<>();
    private final List<VInsect> insects = new ArrayList<>();
    private final List<VSpore> spores = new ArrayList<>();
    private VMushroomStem stem;

    public VTecton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addThread(VMushroomThread thread) {
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