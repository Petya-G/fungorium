package view.game.entities.tecton;

import java.awt.*;

public class VTecton {
    private int x, y;
    int diameter = 50;
    private Color color = Color.BLUE;
    private int threads;

    public VTecton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
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

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getThreads() {
        return threads;
    }

    public void increaseThreads() {
        this.threads++;
    }
}