package view.game;

import controller.visitor.NewObjectVisitor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.insect.*;
import model.mushroom.*;
import model.mushroom.spore.*;
import model.tecton.*;
import view.game.buttons.TectonButton;

public class TectonContentPanel extends JPanel implements ActionListener{

    JLabel mainText;

    private final JPanel stem_panel;
    private final JPanel thread_panel;
    private final JPanel spore_panel;
    private final JPanel insect_panel;

    public TectonContentPanel() {
        setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Details"));
        setBackground(Color.WHITE);

        setLayout(new GridLayout(2, 2));

        stem_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        stem_panel.setBorder(new TitledBorder("Stems"));
        add(stem_panel);

        spore_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        spore_panel.setBorder(new TitledBorder("Spores"));
        add(spore_panel);

        insect_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        insect_panel.setBorder(new TitledBorder("Insects"));
        add(insect_panel);

        thread_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        thread_panel.setBorder(new TitledBorder("Threads"));
        add(thread_panel);
        
    }

    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }

    private void addThreadsPanel(Tecton t) {    
        thread_panel.removeAll();

        if (t.getThreads().isEmpty()) {
            thread_panel.add(new JLabel("No threads"));
        }

        NewObjectVisitor v= new NewObjectVisitor();
        for (MushroomThread th : t.getThreads()) {
            th.accept(v);
        }

        thread_panel.revalidate();
        thread_panel.repaint();
    }

    public void addThreadComponent(JButton button) {
        thread_panel.add(button);
    }


    private void addStemsPanel(Tecton t) {
        stem_panel.removeAll();

        if (t.getStems().isEmpty()) {
           stem_panel.add(new JLabel("No stems"));
        }
        
        NewObjectVisitor v= new NewObjectVisitor();
        for (MushroomStem st : t.getStems()) {
            st.accept(v);
        }
       
        stem_panel.revalidate();
        stem_panel.repaint();
    }

    public void addStemComponent(JButton button) {
        stem_panel.add(button);
    }


    private void addSporesPanel(Tecton t) {   
        spore_panel.removeAll();
        if (t.getSpores().isEmpty()) {
            spore_panel.add(new JLabel("No spores"));
            return;
        }
        NewObjectVisitor v= new NewObjectVisitor();
        for (Spore sp : t.getSpores()) {
            sp.accept(v);
        }
        spore_panel.revalidate();
        spore_panel.repaint();
    }

    public void addSporeComponent(JButton button) {
        spore_panel.add(button);
    }


    private void addInsectsPanel(Tecton t) {
        insect_panel.removeAll();

        if (t.getInsects().isEmpty()) {
            insect_panel.add(new JLabel("No insects"));
            return;
        }

        NewObjectVisitor v= new NewObjectVisitor();
        for (Insect i : t.getInsects()) {
            i.accept(v);
        }
        insect_panel.revalidate();
        insect_panel.repaint();
    }

    public void addInsectComponent(JButton button) {
        
        insect_panel.add(button);
    }
    
    /*
     * Tectonra való klikkelést kezelő függvény
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        TectonButton b = (TectonButton)e.getSource();
        Tecton t = b.tecton;

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