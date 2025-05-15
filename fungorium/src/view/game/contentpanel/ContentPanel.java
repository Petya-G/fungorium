package view.game.contentpanel;

import model.tecton.Tecton;
import view.IUpdateGUI;
import view.game.buttons.TectonButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentPanel extends JPanel implements ActionListener, IUpdateGUI {
    private final StemsPanel stemsPanel = new StemsPanel();
    private final ThreadsPanel threadsPanel = new ThreadsPanel();
    private final SporesPanel sporesPanel = new SporesPanel();
    private final InsectsPanel insectPanel = new InsectsPanel();

    public ContentPanel() {
        setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Details"));
        setBackground(Color.WHITE);

        setLayout(new GridLayout(2, 2));

        add(stemsPanel);
        add(sporesPanel);
        add(threadsPanel);
        add(insectPanel);

        Controller.subscribeGUIUpdate(this);
    }

    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)), title);
    }

    public BasePanel getSporesPanel() {
        return sporesPanel;
    }

    public BasePanel getThreadsPanel() {
        return threadsPanel;
    }

    public BasePanel getStemsPanel() {
        return stemsPanel;
    }

    public BasePanel getInsectPanel() {
        return insectPanel;
    }

    Tecton tecton = null;

    /*
     * Tectonra való klikkelést kezelő függvény
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        tecton = ((TectonButton) e.getSource()).tecton;

        ((TitledBorder) getBorder()).setTitle("Details (" + tecton.getName() + ")");

        threadsPanel.addPanel(tecton);
        sporesPanel.addPanel(tecton);
        stemsPanel.addPanel(tecton);
        insectPanel.addPanel(tecton);

        repaint();
        validate();
    }

    public void GUIupdate() {
        if (tecton != null) {
            threadsPanel.addPanel(tecton);
            sporesPanel.addPanel(tecton);
            stemsPanel.addPanel(tecton);
            insectPanel.addPanel(tecton);
        }
    }

    /*public void clear() {
        tecton = null;
    }*/
}