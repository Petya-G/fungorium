import java.util.Scanner;
import controller.*;
public class Main {
    
    static Parser parser = new Parser(new Game());
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            if (parser.getGame().getCurrentPlayer() != null) {
                System.out.println("current turn: " + parser.getGame().getTurn() + " (" + parser.getGame().getCurrentPlayer().getId() + "): " + parser.getGame().getCurrentPlayer());
            }
            parser.parseCommand(s.nextLine());
        }
    }
}