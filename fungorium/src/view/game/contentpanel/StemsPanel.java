package view.game.contentpanel;

import controller.visitor.NewObjectVisitor;
import model.tecton.Tecton;

import javax.swing.*;

/**
 * A {@code StemsPanel} osztály a kiválasztott {@link Tecton} objektumhoz tartozó gombatörzsek (stems) megjelenítésére szolgál.
 *
 * <p>Ha a tectonhoz nem tartozik törzs, akkor a panel egy "No stems" feliratot jelenít meg.
 * Ellenkező esetben a {@link NewObjectVisitor} segítségével a törzsekhez tartozó gombokat adja a panelhez.</p>
 */
public class StemsPanel extends BasePanel {

    /**
     * Létrehozza a {@code StemsPanel} példányt "Stems" címmel.
     */
    public StemsPanel() {
        super("Stems");
    }

    /**
     * Frissíti a panel tartalmát a megadott {@code Tecton} alapján,
     * megjelenítve annak gombatörzseit, ha vannak.
     *
     * @param tecton a tecton, amelyhez tartozó törzseket meg kell jeleníteni
     */
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
