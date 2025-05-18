package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

/**
 * A {@code ThreadsPanel} osztály a kiválasztott {@link Tecton} objektumhoz tartozó gombaszálak (threads) megjelenítésére szolgál.
 *
 * <p>Ha a tecton nem tartalmaz szálat, akkor egy "No threads" felirat jelenik meg.
 * Egyébként a szálakhoz tartozó gombokat jeleníti meg a {@link NewObjectVisitor} használatával.</p>
 */
public class ThreadsPanel extends BasePanel {

    /**
     * Létrehozza a {@code ThreadsPanel} példányt a "Threads" címmel.
     */
    public ThreadsPanel() {
        super("Threads");
    }

    /**
     * Frissíti a panel tartalmát a megadott {@code Tecton} objektum szálai alapján.
     *
     * @param tecton a tecton, amelyhez tartozó szálakat meg kell jeleníteni
     */
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
