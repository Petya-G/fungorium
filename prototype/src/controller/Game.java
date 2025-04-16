package controller;

import model.Map;
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

import java.util.LinkedList;
import java.util.Queue;

public class Game implements ITurn, IRound {
    private Map map = new Map();
    private Queue<Player> players = new LinkedList<>();

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
        //TODO
    }
}
