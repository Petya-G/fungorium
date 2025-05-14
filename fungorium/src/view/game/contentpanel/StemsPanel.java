package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

public class StemsPanel extends BasePanel {
    public StemsPanel() {
        super("Stems");
    }

    @Override
    public void addPanel(Tecton tecton) {
        removeAll();
        if (tecton.getStems().isEmpty()) {
            add(new JLabel("No stems"));
            return;
        }

        NewObjectVisitor v = new NewObjectVisitor();
        tecton.getStems().forEach(i -> i.accept(v));
    }
}
