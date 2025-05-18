package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.tecton.Tecton;
import view.game.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TectonButton extends GameButton {
    public Tecton tecton;

    public TectonButton(Tecton tecton, ImageIcon imageIcon, MapPanel parent) {
        super(imageIcon, parent);
        this.tecton = tecton;
        setBounds(CenterX((int) tecton.getPosX()), CenterY((int) tecton.getPosY()), size, size);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialMousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                initialMousePoint = e.getPoint();

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (Controller.getAction() == Action.MOVE) {
                        if (Controller.getGame().move((Insect) Controller.getSelected(), tecton)) {
                            Controller.log("moved successfully");
                        } else {
                            Controller.log("unable to move");
                        }
                        Controller.setAction(Action.NONE);
                    }

                    if (Controller.getAction() == Action.THROW_SPORE) {
                        if (Controller.getGame().throwSpore((MushroomStem) Controller.getSelected(), tecton)) {
                            Controller.log("spore succesfully thrown");
                        } else {
                            Controller.log("unable to throw spore");
                        }
                        Controller.setAction(Action.NONE);
                    }

                    if (Controller.getAction() == Action.GROW_THREAD) {
                        if (Controller.getGame().growThread(tecton)) {
                            Controller.log("thread grown successfully");
                        } else {
                            Controller.log("unable to grow thread");
                        }
                        Controller.setAction(Action.NONE);
                    }
                }
                Controller.refreshView();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (initialMousePoint != null) {
                    int deltaX = e.getX() - initialMousePoint.x;
                    int deltaY = e.getY() - initialMousePoint.y;

                    Point buttonLocation = getLocation();
                    int newX = buttonLocation.x + deltaX;
                    int newY = buttonLocation.y + deltaY;

                    int parentWidth = parent.getWidth();
                    int parentHeight = parent.getHeight();

                    newX = Math.max(0, Math.min(newX, parentWidth - size));
                    newY = Math.max(0, Math.min(newY, parentHeight - size));

                    tecton.setPosX(invCenterX(newX + size / 2));
                    tecton.setPosY(invCenterY(newY + size / 2));

                    setLocation(newX, newY);

                    parent.repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private boolean hasHighlight = false;
    public void highlight (boolean b) {
        hasHighlight = b;
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if (hasHighlight) {
            g.setColor(new Color(255,0,0));
            g2d.setStroke(new BasicStroke(10));
            g.drawRect(0, 0, size, size);
        }
    }

    public void refreshState() {
        setLocation(CenterX(tecton.getPosX()) - size / 2, CenterY(tecton.getPosY()) - size / 2);
    }

    protected double invCenterX(int x) {
        return (x - size) / (parent.getSize().getWidth() - 2 * size);
    }

    protected double invCenterY(int y) {
        return (y - size) / (parent.getSize().getHeight() - 2 * size);
    }

    protected int CenterX(double coordX) {
        return (int) (coordX * (parent.getSize().getWidth() - 2 * size) + size);
    }

    protected int CenterY(double coordY) {
        return (int) (coordY * (parent.getSize().getHeight() - 2 * size) + size);
    }

}
