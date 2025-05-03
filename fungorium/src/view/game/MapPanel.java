package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private List<TectonView> tectons = new ArrayList<>();
    private  List<MushroomThreadView> threads = new ArrayList<>();
    private TectonView selectedTecton = null;
    private InsecterPop insecterPop;
    private MushroomerPop mushroomerPop;

    public MapPanel() {

        //setLayout(new FlowLayout());
        setLayout(new BorderLayout());

        insecterPop = new InsecterPop();
        mushroomerPop = new MushroomerPop();

        setBackground(new Color(250, 250, 250));

        tectons.add(new TectonView(640,360));
        tectons.add(new TectonView(150, 150));
        threads.add(new MushroomThreadView(tectons.get(0),tectons.get(1),Color.RED));
        threads.add(new MushroomThreadView(tectons.get(0),tectons.get(1),Color.YELLOW));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point click = e.getPoint();
                for (TectonView tecton : tectons) {
                    if (tecton.contains(click)) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            //TODO: Eldönteni, hogy mushroomer vagy insecter játszik annak az add függvényét meghívni
                            add(insecterPop,BorderLayout.SOUTH);
                            //add(mushroomerPop, BorderLayout.SOUTH);
                            revalidate(); // újrarenderelés
                            repaint();
                        } else {
                            selectedTecton = tecton; // bal klikk - mozgatás
                        }
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedTecton = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedTecton != null) {
                    selectedTecton.setX(e.getX());
                    selectedTecton.setY(e.getY());
                    repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (TectonView t : tectons) {
            t.draw(g2d);
        }
        for(MushroomThreadView t : threads){
            t.draw(g2d);
        }

    }
}