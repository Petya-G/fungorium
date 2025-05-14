package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

public class SporesPanel extends BasePanel {
    public SporesPanel() {
        super("Spores");
    }

    @Override
    public void addPanel(Tecton tecton) {
        removeAll();
        if (tecton.getSpores().isEmpty()) {
            add(new JLabel("No spores"));
            return;
        }

        NewObjectVisitor v = new NewObjectVisitor();
        tecton.getSpores().forEach(s -> s.accept(v));
    }
}
