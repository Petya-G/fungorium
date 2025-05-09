import controller.*;
import model.Game;
import view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() ->{
            Game game = new Game();
            game.startGame(4);
            View view = new View(game);
            
            Controller controller = new Controller(game, view);
        });
        
    }
}