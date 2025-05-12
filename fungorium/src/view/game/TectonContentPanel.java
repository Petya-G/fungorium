package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import model.tecton.*;
import model.mushroom.*;
import model.mushroom.spore.*;
import model.insect.*;
import view.game.buttons.TectonButton;

public class TectonContentPanel extends JPanel implements ActionListener{

    JLabel mainText;
    public TectonContentPanel() {
        setLayout(new FlowLayout());
        setBorder(createTitledBorder("Details"));
        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(getSize().width,200));

        add(new JLabel("Please select a tecton to view."), BorderLayout.NORTH);
    }

    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }

    private void addThreadsPanel(Tecton t) {
        // threads panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        
        panel.setBorder(new TitledBorder("Threads"));
        add(panel);

        for (MushroomThread th : t.getThreads()) {
            JButton button = new JButton(th.getName());
            
            panel.add(button);
        }
        if (t.getThreads().size() == 0) {
            panel.add(new JLabel("No threads"));
        }
    }

    private void addStemsPanel(Tecton t) {
        // stems panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        
        panel.setBorder(new TitledBorder("Stems"));
        add(panel);

        for (MushroomStem st : t.getStems()) {
            JButton button = new JButton(st.getName());
            
            panel.add(button);
        }
        if (t.getStems().size() == 0) {
            panel.add(new JLabel("No stems"));
        }
    }

    private void addSporesPanel(Tecton t) {
        // stems panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        
        panel.setBorder(new TitledBorder("Spores"));
        add(panel);

        for (Spore sp : t.getSpores()) {
            JButton button = new JButton(sp.getName());
            
            panel.add(button);
        }
        if (t.getSpores().size() == 0) {
            panel.add(new JLabel("No spores"));
        }
    }

    private void addInsectsPanel(Tecton t) {
        // stems panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        
        panel.setBorder(new TitledBorder("Insects"));
        add(panel);

        for (Insect i : t.getInsects()) {
            JButton button = new JButton(i.getName());
            
            panel.add(button);
        }
        if (t.getInsects().size() == 0) {
            panel.add(new JLabel("No insects"));
        }
    }

    /*
     * Tectonra való klikkelést kezelő függvény
     */
    public void actionPerformed(ActionEvent e) {
        removeAll();

        TectonButton b = (TectonButton)e.getSource();
        Tecton t = b.tecton;

        // Add Label
        TitledBorder border = (TitledBorder)getBorder();
        border.setTitle("Details (" + t.getName() + ")");

        addThreadsPanel(t);
        addStemsPanel(t);
        addSporesPanel(t);
        addInsectsPanel(t);
        

        repaint();
        validate();
    }
}