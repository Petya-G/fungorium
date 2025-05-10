package view.game;


import controller.DrawVisitor;
import model.Game;
import model.tecton.Tecton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private final InsecterPop insecterPop;
    private final MushroomerPop mushroomerPop;
    public List<TectonButton> tectonButtons = new ArrayList<>();
    Game game;
    Tecton zoom = null;

    public MapPanel(Game game) {
        this.game = game;
        setLayout(null);

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
        DrawVisitor drawVisitor = new DrawVisitor((Graphics2D) g, new Dimension(10, 10));

        for (Tecton t : game.getMap().tectons) {
            t.accept(drawVisitor);
        }
    }

    public void addTectonButton(TectonButton tectonButton) {
        tectonButtons.add(tectonButton);
        add(tectonButton);
        revalidate();
        repaint();
    }
}