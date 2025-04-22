package controller;

public class Parser {

    Game game;
    public Parser(Game game) {
        this.game = game;
    }

    public void CMD_start(String[] args) {

    }
    public void CMD_load(String[] args) {

    }
    public void CMD_save(String[] args) {

    }

    public void CMD_exec(String[] args) {

    }
    public void CMD_rand(String[] args) {

    }
    public void CMD_addplayers(String[] args) {

    }
    public void CMD_manualtrigger(String[] args) {

    }
    public void CMD_list(String[] args) {

    }
    public void CMD_map(String[] args) {

    }
    public void CMD_help(String[] args) {

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

    }
    

    public void parseCommand(String cmd) {
        String[] tokens = cmd.split(" ");

        switch(tokens[0]) {
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
            default:
                System.out.println("invalid command!");
                break;
        }
    }
}
