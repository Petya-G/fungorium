import controller.Controller;
import model.Game;
import view.View;
import view.mainMenu.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = Controller.getInstance();
        });
    }
}