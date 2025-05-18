package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

/**
 * A {@code SporesPanel} osztály a kiválasztott {@link Tecton} objektumhoz tartozó spórák megjelenítésére szolgál.
 *
 * <p>Amennyiben a tectonhoz nem tartozik spóra, a panelen egy "No spores" felirat jelenik meg.
 * Ha vannak spórák, akkor azokhoz tartozó gombokat jeleníti meg a {@link NewObjectVisitor} használatával.</p>
 */
public class SporesPanel extends BasePanel {

    /**
     * Létrehozza a {@code SporesPanel} példányt az "Spores" címmel.
     */
    public SporesPanel() {
        super("Spores");
    }

    /**
     * Frissíti a panel tartalmát a megadott {@code Tecton} objektumhoz tartozó spórák alapján.
     *
     * @param tecton a tecton, amelyhez tartozó spórákat meg kell jeleníteni
     */
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
