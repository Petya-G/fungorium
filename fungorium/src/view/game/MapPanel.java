package view.game;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private final InsecterPop insecterPop;
    private final MushroomerPop mushroomerPop;

    public MapPanel() {
        setLayout(new BorderLayout());

        insecterPop = new InsecterPop();
        mushroomerPop = new MushroomerPop();

        setBackground(new Color(250, 250, 250));
        //TODO
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
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                if (selectedTecton != null) {
//                    selectedTecton.setX(e.getX());
//                    selectedTecton.setY(e.getY());
//                    repaint();
//                }
//            }
//        };
//
//        addMouseListener(mouseAdapter);
//        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //TODO
    }
}