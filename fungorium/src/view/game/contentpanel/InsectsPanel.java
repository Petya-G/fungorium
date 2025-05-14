package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

public class InsectsPanel extends BasePanel {
    public InsectsPanel() {
        super("Insects");
    }

    @Override
    public void addPanel(Tecton tecton) {
        removeAll();
        if (tecton.getInsects().isEmpty()) {
            add(new JLabel("No insects"));
            return;
        }

        NewObjectVisitor v = new NewObjectVisitor();
        tecton.getInsects().forEach(i -> i.accept(v));

    }

}
