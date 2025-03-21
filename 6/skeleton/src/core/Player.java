package core;

public abstract class Player implements ITurn {
    protected Integer score;

    public abstract Integer getScore();
    public abstract void addScore(Integer score);

    @Override
    public abstract void endTurn();
}