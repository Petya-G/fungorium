package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.Random;

public class Parser {

    static final Random r = new Random();

    Game game;

    public Parser(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void CMD_start(String[] args) {

    }

    public void CMD_load(String[] args) {
        if (args.length != 2) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            FileInputStream file = new FileInputStream(args[1]);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of an object
            game = (Game) in.readObject();

            in.close();
            file.close();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void CMD_save(String[] args) {
        if (args.length != 2) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            FileOutputStream file = new FileOutputStream(args[1]);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(game);

            out.close();
            file.close();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void CMD_exec(String[] args) {

    }

    public void CMD_rand(String[] args) {
        if (args.length != 2) {
            System.out.println("invalid argument count!");
            return;
        }
        if (Objects.equals(args[1], "-disable")) Game.random.setSeed(1);
        else if (Objects.equals(args[1], "-enable")) Game.random.setSeed(r.nextInt());
    }

    public void CMD_addplayers(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }
        game.addPlayers();
    }

    public void CMD_manualtrigger(String[] args) {

    }

    public void CMD_list(String[] args) {

    }

    public void CMD_map(String[] args) {

    }

    public void CMD_help(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }
        System.out.println("/start: Starts the game, after this point, no more players can be added");
        System.out.println("/load: Loads a saved game file, from the given path");
    }

    public void CMD_move(String[] args) {

    }

    public void CMD_eat(String[] args) {

    }

    public void CMD_cut(String[] args) {

    }

    public void CMD_grow(String[] args) {

    }

    public void CMD_plant(String[] args) {

    }

    public void CMD_throw(String[] args) {

    }

    public void CMD_endturn(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }
        game.endTurn();
    }


    public void parseCommand(String cmd) {
        String[] tokens = cmd.split(" ");

        switch (tokens[0]) {
            case "/start":
                CMD_start(tokens);
                break;
            case "/load":
                CMD_load(tokens);
                break;
            case "/save":
                CMD_save(tokens);
                break;
            case "/exec":
                CMD_exec(tokens);
                break;
            case "/rand":
                CMD_rand(tokens);
                break;
            case "/addplayers":
                CMD_addplayers(tokens);
                break;
            case "/manualtrigger":
                CMD_manualtrigger(tokens);
                break;
            case "/list":
                CMD_list(tokens);
                break;
            case "/map":
                CMD_map(tokens);
                break;
            case "/help":
                CMD_help(tokens);
                break;

            case "!move":
                CMD_move(tokens);
                break;
            case "!eat":
                CMD_eat(tokens);
                break;
            case "!cut":
                CMD_cut(tokens);
                break;
            case "!grow":
                CMD_grow(tokens);
                break;
            case "!plant":
                CMD_plant(tokens);
                break;
            case "!throw":
                CMD_throw(tokens);
                break;
            case "!endturn":
                CMD_endturn(tokens);
                break;
            case "/exit":
                System.exit(0);
                break;
            default:
                System.out.println("invalid command!");
                break;
        }
    }
}
