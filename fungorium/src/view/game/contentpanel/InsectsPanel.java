package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

/**
 * Az {@code InsectsPanel} egy részpanel, amely egy adott {@link Tecton} objektumhoz tartozó rovarokat jeleníti meg.
 *
 * <p>Ha a tectonhoz nem tartozik rovar, egy "No insects" felirat jelenik meg.
 * Ellenkező esetben a rovarokhoz tartozó gombok (pl. {@code InsectButton}) kerülnek megjelenítésre,
 * a {@link NewObjectVisitor} segítségével.</p>
 */
public class InsectsPanel extends BasePanel {

    /**
     * Létrehozza az {@code InsectsPanel} példányt az "Insects" címmel.
     */
    public InsectsPanel() {
        super("Insects");
    }

    /**
     * Frissíti a panel tartalmát a megadott {@code Tecton} alapján.
     *
     * @param tecton a kiválasztott tecton, amelyhez tartozó rovarokat meg kell jeleníteni
     */
    @Override
    public void addPanel(Tecton tecton) {
        removeAll();

        if (tecton.getInsects().isEmpty()) {
            add(new JLabel("No insects"));
            return;
        }

        NewObjectVisitor v = new NewObjectVisitor();
        tecton.getInsects().forEach(i -> i.accept(v));  // Megjeleníti a rovarokat megfelelő gombokkal
    }
}
