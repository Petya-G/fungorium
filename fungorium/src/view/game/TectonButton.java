package view.game;

import model.tecton.Tecton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TectonButton extends JButton {
    static int size = 50;
    static int tectonWidth = 100;
    static int tectonHeight = 100;
    Tecton tecton;
    ImageIcon imageIcon;
    MapPanel parent;

    public TectonButton(Tecton tecton, ImageIcon imageIcon, MapPanel parent) {
        super(scaleImageIcon(imageIcon, size, size));
        this.imageIcon = imageIcon;
        this.parent = parent;
        this.tecton = tecton;
        setBounds(tectonCenterX((int) tecton.getPosX()), tectonCenterY((int) tecton.getPosY()), size, size);
        setPreferredSize(new Dimension(size, size));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialMousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                initialMousePoint = e.getPoint();
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

                    tecton.setPosX(inverseTectonX(newX + size / 2));
                    tecton.setPosY(inverseTectonY(newY + size / 2));

                    setLocation(newX, newY);

                    parent.repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private static ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        if (icon == null || icon.getImage() == null) {
            return null;
        }
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    double inverseTectonX(int x) {
        return (x - tectonWidth / 2.0) / (parent.getSize().getWidth() - tectonWidth);
    }

    double inverseTectonY(int y) {
        return (y - tectonHeight / 2.0) / (parent.getSize().getHeight() - tectonHeight);
    }

    int tectonCenterX(double coordX) {
        return (int) (coordX * (parent.getSize().getWidth() - tectonWidth) + (double) tectonWidth / 2);
    }

    int tectonCenterY(double coordY) {
        return (int) (coordY * (parent.getSize().getHeight() - tectonHeight) + (double) tectonHeight / 2);
    }

    // ez kell, ne szedd ki, map generalas egyszerubb mintha konstruktorban lenne ez, ha nem hiszed kerdezd meg. Akos
    public void refreshState() {
        setLocation(tectonCenterX(tecton.getPosX()) - size / 2, tectonCenterY(tecton.getPosY()) - size / 2);
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
