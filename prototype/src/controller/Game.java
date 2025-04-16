package controller;

import model.Map;
import model.core.Entity;
import model.core.IRound;
import model.core.ITurn;
import model.core.Player;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game implements ITurn, IRound {
    private Map map = new Map();
    private final int maxTurn = 10;
    private final boolean ended = false;
    int id = 0;
    private List<Player> players = new ArrayList<>();
    private int turn = 0;

    public int getNextId() {
        return id++;
    }

    private boolean hasCurrentTurn(Entity entity) {
        return false;
    }

    public Player getCurrentPlayer() {
        return players.get(turn % players.size());
    }

    public Player getWinner() {
        //TODO
        return new Mushroomer();
    }

    public boolean move(Insect insect, Tecton location) {
        //TODO
        return false;
    }

    public boolean eat(Insect insect, Spore spore) {
        //TODO
        return false;
    }

    public boolean cut(Insect insect, MushroomThread mushroomThread) {
        //TODO
        return false;
    }

    public boolean plantStem(Mushroomer mushroomer, Tecton location) {
        //TODO
        return false;
    }

    public boolean growThread(Mushroomer mushroomer, Tecton location) {
        //TODO
        return false;
    }

    public boolean eat(MushroomThread mushroomThread, Insect insect) {
        //TODO
        return false;
    }

    public boolean levelUp(MushroomStem mushroomStem) {
        //TODO
        return false;
    }

    @Override
    public void endRound() {
        //TODO
    }

    @Override
    public void endTurn() {
        turn++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return turn == game.turn && ended == game.ended && id == game.id && Objects.equals(map, game.map) && Objects.equals(players, game.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, players, turn, maxTurn, ended, id);
    }
}
