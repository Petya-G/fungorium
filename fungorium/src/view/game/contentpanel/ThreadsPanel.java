package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

public class ThreadsPanel extends BasePanel {
    public ThreadsPanel() {
        super("Threads");
    }

    @Override
    public void addPanel(Tecton tecton) {
        removeAll();
        if (tecton.getThreads().isEmpty()) {
            add(new JLabel("No threads"));
            return;
        }

        NewObjectVisitor v = new NewObjectVisitor();
        tecton.getThreads().forEach(s -> s.accept(v));
    }
}
