package controller;

import model.Map;
import model.core.Entity;
import model.core.IRound;
import model.core.ITurn;
import model.core.Player;
import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Game implements ITurn, IRound {
    private Map map = new Map();
    private final int maxTurn = 10;
    private boolean ended = false;
    int id = 0;
    private List<Player> players = new ArrayList<>();
    private int turn = 0;

    private boolean hasCurrentTurn(Entity entity) {
        return getCurrentPlayer().equals(entity.getOwner());
    }

    private boolean hasCurrentTurn(Player player) {
        return getCurrentPlayer().equals(player);
    }

    public Player getCurrentPlayer() {
        return players.get(turn % players.size());
    }

    public Player getWinner() {
        return players.stream().max(Comparator.comparingInt(Player::getScore)).orElse(null);
    }

    public boolean move(Insect insect, Tecton location) {
        if (!hasCurrentTurn(insect))
            return false;
        return ((Insecter) insect.getOwner()).move(insect, location);
    }

    public boolean eat(Insect insect, Spore spore) {
        if (!hasCurrentTurn(insect))
            return false;
        return ((Insecter) insect.getOwner()).eat(insect, spore);
    }

    public boolean cut(Insect insect, MushroomThread mushroomThread) {
        if (!hasCurrentTurn(insect))
            return false;
        return ((Insecter) insect.getOwner()).cut(insect, mushroomThread);

    }

    public boolean plantMushroomStem(Mushroomer mushroomer, Tecton location) {
        if (!hasCurrentTurn(mushroomer))
            return false;
        return mushroomer.plantMushroomstem(location);
    }

    public boolean growThread(Mushroomer mushroomer, Tecton location) {
        if (!hasCurrentTurn(mushroomer))
            return false;
        return mushroomer.growMushroomthread(location);
    }

    public boolean eat(MushroomThread mushroomThread, Insect insect) {
        if (!hasCurrentTurn(mushroomThread))
            return false;
        return ((Mushroomer) mushroomThread.getOwner()).eat(mushroomThread, insect);
    }

    public boolean levelUp(MushroomStem mushroomStem) {
        if (!hasCurrentTurn(mushroomStem))
            return false;
        return ((Mushroomer) mushroomStem.getOwner()).levelUp(mushroomStem);
    }

    @Override
    public void endRound() {
        map.endRound();
    }

    @Override
    public void endTurn() {
        turn++;
        if (turn % players.size() == 0)
            ended = true;
        if (turn == maxTurn)
            ended = true;
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
