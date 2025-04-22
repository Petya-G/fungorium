import java.util.Scanner;
import controller.*;
public class Main {
    
    static Parser parser = new Parser(new Game());
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            parser.parseCommand(s.nextLine());
        }
    }
}