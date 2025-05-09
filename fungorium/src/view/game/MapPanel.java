package view.game;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



import controller.DrawVisitor;
import model.tecton.Tecton;

import model.*;
public class MapPanel extends JPanel {
    private final InsecterPop insecterPop;
    private final MushroomerPop mushroomerPop;
    Game game;
    Tecton zoom = null;

    public List<TectonButton> tectonButtons = new ArrayList<>();
    
    

    public MapPanel(Game game) {
        this.game = game;
        setLayout(null);

        insecterPop = new InsecterPop();
        mushroomerPop = new MushroomerPop();

        //TectonButton tb = new TectonButton(new Tecton());
        //this.add(tb);

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

        DrawVisitor visitor = new DrawVisitor();

        visitor.setSize(getSize());
        visitor.setGraphics(g2d);
        //tectonButtons.clear();
        //visitor.setTectonButtons(tectonButtons);
        for(Tecton t : game.getMap().tectons) {
            //System.out.print(t.getId() + "\t");
            t.accept(visitor);
            //this.add(tectonButtons.getLast());
        }
        
        //System.out.println();
    }
}