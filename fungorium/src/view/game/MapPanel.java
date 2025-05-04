package view.game;

import view.game.vitem.VMushroomThreadConnection;
import view.game.vitem.tecton.VTecton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapPanel extends JPanel {
    private final List<VTecton> tectons = new ArrayList<>();
    private final List<VMushroomThreadConnection> threads = new ArrayList<>();
    private VTecton selectedTecton = null;
    private final InsecterPop insecterPop;
    private final MushroomerPop mushroomerPop;

    public MapPanel() {

        //setLayout(new FlowLayout());
        setLayout(new BorderLayout());

        insecterPop = new InsecterPop();
        mushroomerPop = new MushroomerPop();

        setBackground(new Color(250, 250, 250));

        //tectons.add(new TectonView(640,360));
        //tectons.add(new TectonView(150, 150));
        addTectonAtRandomPosition();
        addTectonAtRandomPosition();
        threads.add(new VMushroomThreadConnection(tectons.get(0), tectons.get(1), Color.RED));
        threads.add(new VMushroomThreadConnection(tectons.get(0), tectons.get(1), Color.YELLOW));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point click = e.getPoint();
                for (VTecton tecton : tectons) {
                    if (tecton.contains(click)) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            //TODO: Eldönteni, hogy mushroomer vagy insecter játszik annak az add függvényét meghívni
                            add(insecterPop, BorderLayout.SOUTH);
                            //add(mushroomerPop, BorderLayout.SOUTH);
                            revalidate();
                            repaint();
                        } else {
                            selectedTecton = tecton;
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

        for (VTecton t : tectons) {
            t.draw(g2d);
        }
        for (VMushroomThreadConnection t : threads) {
            t.draw(g2d);
        }

    }

    public void addTectonAtRandomPosition() {
        int x = ThreadLocalRandom.current().nextInt(50, 1020);
        int y = ThreadLocalRandom.current().nextInt(50, 550);

        VTecton newTecton = new VTecton(x, y, "tecton");
        tectons.add(newTecton);
        repaint();
    }
}