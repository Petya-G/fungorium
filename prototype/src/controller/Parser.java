package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import model.core.Identifiable;
import model.insect.Insect;
import model.tecton.Tecton;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.spore.Spore;

public class Parser {

    static final Random r = new Random();
    static final String[] helpGuide = {"-----------------------------------------SYSTEM COMMANDS-----------------------------------------",
            "/start: Starts the game, after this point, no more players can be added",
            "/load PATH: Loads a saved game file, from the given path",
            "/save PATH: Saves the current state of the game to a file, at the given path",
            "/exec PATH: Executes the commands in the given file",
            "/rand: Enables or disables the randomness of the random elements (-disable/-enable)",
            "/addplayers: Adds a Mushroomer and an Insecter player to the game",
            "/manualtrigger: Forces the execution of the given game command",
            "/list: Lists all objects of the given type (-player, -mushroomstem, -mushroomthread, -insect, -spore)",
            "/map: Prints the Map (All tectons with their neighbours)",
            "/help: Prints this guide",
            "------------------------------------------GAME COMMANDS------------------------------------------",
            "!move INSECTID TECTONID: Moves the insect to another tecton, if it's legal",
            "!eat INSECTID: The given insect eats a random (or not) spore on its tecton, if it can",
            "!cut INSECTID: The given insect cuts a random (or not) mushroom thread on its tecton, if it can",
            "!grow TECTONID: Grows a mushroom thread on the given tecton, if it's legal",
            "!plant TECTONID: Plants a mushroom stem on the given tecton, if it's legal",
            "!throw MUSHROOMSTEMID TECTONID: The given mushroom stem throws a spore to the given tecton, if it can",
            "!endturn: Ends the current players turn, if all of its obligatory actions have been taken",
            "/exit: exits the game (DOES NOT SAVE)"
    };

    Game game;

    public Parser(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void CMD_start(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }
        if (!game.startGame()) System.out.println("THhe game couldn't have been started (maybe it already has...)");
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
        if (args.length != 2) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            File myObj = new File(args[1]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                parseCommand(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
        if (!game.addPlayers()) System.out.println("Players couldn't have been added");
    }

    public void CMD_manualtrigger(String[] args) {

    }

    public void CMD_list(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }

        for (int i = 0; i <= Identifiable.getMaxId(); i++) {
            System.out.println("(" + i + ") : " + game.findObject(i));
        }
    }

    public void CMD_map(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }
        game.printMap();
    }

    public void CMD_help(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid argument count!");
            return;
        }
        for (String item : helpGuide) System.out.println(item);


    }

    public void CMD_move(String[] args) {
        if (args.length != 3) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            if (!game.move((Insect) Identifiable.findObject(Integer.parseInt(args[1])), (Tecton) Identifiable.findObject(Integer.parseInt(args[2])))) System.out.println("Couldn't move");
        } catch (Exception e) {
            System.out.println("Invalid ID(s)");
        }
    }

    public void CMD_eat(String[] args) {
        if (args.length != 3) {
            System.out.println("invalid argument count!");
            return;
        }
        boolean oneEx = false;
        try {
            if (game.eat((Insect) Identifiable.findObject(Integer.parseInt(args[1])), (Spore) Identifiable.findObject(Integer.parseInt(args[2])))) return;   
        } catch (Exception e) {
            oneEx = true;
        }

        try {
            if (game.eat((MushroomThread) Identifiable.findObject(Integer.parseInt(args[1])), (Insect) Identifiable.findObject(Integer.parseInt(args[2])))) return;            
        } catch (Exception e) {
            if (oneEx) System.out.println("Invalid ID(s)");
        }
        System.out.println("Couldn't eat");
    }

    public void CMD_cut(String[] args) {
        if (args.length != 3) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            if (!game.cut((Insect) Identifiable.findObject(Integer.parseInt(args[1])), (MushroomThread) Identifiable.findObject(Integer.parseInt(args[2]))))
                System.out.println("Couldn't cut");
        } catch (Exception e) {
            System.out.println("Invalid ID(s)");
        }
    }

    public void CMD_grow(String[] args) {
        if (args.length != 2) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            if (!game.growThread((Tecton) Identifiable.findObject(Integer.parseInt(args[1])))) System.out.println("Couldn't grow");
        } catch (Exception e) {
            System.out.println("Invalid ID(s)");
        }
    }

    public void CMD_plant(String[] args) {
        if (args.length != 2) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            if (!game.plantMushroomStem((Tecton) Identifiable.findObject(Integer.parseInt(args[1])))) System.out.println("Couldn't grow");
        } catch (Exception e) {
            System.out.println("Invalid ID(s)");
        }
    }

    public void CMD_throw(String[] args) {
        if (args.length != 3) {
            System.out.println("invalid argument count!");
            return;
        }
        try {
            if (!game.throwSpore((MushroomStem) Identifiable.findObject(Integer.parseInt(args[1])), (Tecton) Identifiable.findObject(Integer.parseInt(args[2]))))
                System.out.println("Couldn't throw");
        } catch (Exception e) {
            System.out.println("Invalid ID(s)");
        }
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
