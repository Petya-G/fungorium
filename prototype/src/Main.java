import java.util.Scanner;
import controller.*;
public class Main {
    
    static Game game = new Game();
    static Parser parser = new Parser(game);
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            parser.parseCommand(s.nextLine());
        }
    }
}