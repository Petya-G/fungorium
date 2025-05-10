package view.game;


import controller.DrawVisitor;
import model.Game;
import model.tecton.Tecton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class MapPanel extends JPanel{
    private final InsecterPop insecterPop;
    private final MushroomerPop mushroomerPop;
    public List<TectonButton> tectonButtons = new ArrayList<>();
    Game game;
    Tecton zoom = null;

    DrawVisitor drawVisitor = new DrawVisitor();

    private TectonContentPanel tectonContentPanel;
    public void setTectonContentPanel(TectonContentPanel tectonContentPanel) {
        this.tectonContentPanel = tectonContentPanel;
    }

    public MapPanel(Game game) {
        this.game = game;
        this.tectonContentPanel = tectonContentPanel;
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
        drawVisitor.setParameters((Graphics2D)g, getSize());

        for (TectonButton b : tectonButtons) {
            b.refreshState();
        }

        for (Tecton t : game.getMap().tectons) {
            t.accept(drawVisitor);
        }
    }

    public void addTectonButton(TectonButton tectonButton) {
        tectonButtons.add(tectonButton);
        add(tectonButton);
        tectonButton.addActionListener(tectonContentPanel);
        revalidate();
        repaint();
    }

    
}