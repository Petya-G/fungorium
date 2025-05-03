package view.game;

import model.mushroom.MushroomThread;

import java.awt.*;

public class MushroomThreadView {
    private TectonView start;
    private TectonView end;
    private Color color = Color.RED;
    private int thickness = 1;
    private int startPos;
    private int endPos;

    public MushroomThreadView(TectonView start, TectonView end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
        startPos = start.getThreads();
        endPos = end.getThreads();
        start.increaseThreads();
        end.increaseThreads();
    }

    private int isEven(int n){
        if(n%2 == 0)
            return 1;
        else
            return -1;
    }

    public void draw(Graphics2D g2d) {
        if (start == null || end == null) return;

        Stroke originalStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);

        g2d.drawLine(start.getX() + 5 * startPos * isEven(startPos), start.getY(), end.getX()+ 5* endPos * isEven(endPos), end.getY());

        g2d.setStroke(originalStroke); // visszaállítás
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public TectonView getStart() {
        return start;
    }

    public TectonView getEnd() {
        return end;
    }
}
