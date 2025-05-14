package view.game.buttons;

import controller.Controller;
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

                if (Controller.getInstance().getSelectedButton() == Controller.ButtonPressed.MOVE) {
                    System.out.println("A MOVE gomb van kiválasztva! Erre  Tectonra akarok mozogni");
                    //TODO: megcsinálni
                }
                if (Controller.getInstance().getSelectedButton() == Controller.ButtonPressed.THROW_SPORE) {
                    System.out.println("A Throw SPore gomb van kiválasztva! Erre  Tectonra akarok sporát dobni");
                    //TODO: megcsinálni
                }
                if (Controller.getInstance().getSelectedButton() == Controller.ButtonPressed.GROW_THREAD) {
                    System.out.println("A Grow Thread gomb van kiválasztva! Erre  Tectonra akarok növeszteni threadet");
                    //TODO: megcsinálni
                }
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.DEFAULT);
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

    // ez kell, ne szedd ki, map generalas egyszerubb mintha konstruktorban lenne ez, ha nem hiszed kerdezd meg. Akos
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


    //
//        MouseAdapter mouseAdapter = new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                Point click = e.getPoint();
//                for (VTecton tecton : tectons) {
//                    if (tecton.contains(click)) {
//                        if (SwingUtilities.isRightMouseButton(e)) {
//                            //TODO: Eldönteni, hogy mushroomer vagy insecter játszik annak az add függvényét meghívni
//                            add(insecterPop, BorderLayout.SOUTH);
//                            //add(mushroomerPop, BorderLayout.SOUTH);
//                            revalidate();
//                            repaint();
//                        } else {
//                            selectedTecton = tecton;
//                        }
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                selectedTecton = null;
//            }
//

    //        };
//
}
