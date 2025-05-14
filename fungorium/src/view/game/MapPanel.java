package view.game;


import controller.visitor.DrawVisitor;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Game;
import model.tecton.Tecton;
import view.game.buttons.TectonButton;
import view.game.contentpanel.ContentPanel;

public class MapPanel extends JPanel {
    private final InsecterPop insecterPop;
    private final MushroomerPop mushroomerPop;
    private final DrawVisitor drawVisitor = new DrawVisitor();
    public List<TectonButton> tectonButtons = new ArrayList<>();
    private final Game game;
    private ContentPanel contentPanel;

    public MapPanel(Game game) {
        this.game = game;
        setLayout(null);

        insecterPop = new InsecterPop();
        mushroomerPop = new MushroomerPop();

        setBackground(new Color(250, 250, 250));
    }

    public ContentPanel getTectonContentPanel() {
        return contentPanel;
    }

    public void setTectonContentPanel(ContentPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawVisitor.setParameters((Graphics2D) g, getSize());

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
        tectonButton.addActionListener(contentPanel);
        revalidate();
        repaint();
    }
}