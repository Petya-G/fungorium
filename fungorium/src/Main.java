import controller.Controller;
import model.Game;
import view.View;
import view.mainMenu.MainMenu;

import javax.swing.*;

/**
 * A program belépési pontja.
 *
 * <p>A {@code main} metódus elindítja a Swing felhasználói felületet a megfelelő szálon, és
 * inicializálja a játék {@link Controller} példányát, amely létrehozza a teljes játékmenetet és nézetet.</p>
 */
public class Main {

    /**
     * A program belépési pontja.
     *
     * @param args parancssori argumentumok (jelenleg nem használt)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // A játék vezérlőjének inicializálása, ezzel az egész alkalmazás elindul
            Controller controller = Controller.getInstance();
        });
    }
}
