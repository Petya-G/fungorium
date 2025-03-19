package core;

public abstract class Player implements ITurn {
    protected Integer id;
    protected Integer score;

    public abstract Integer getId();
    public abstract Integer getScore();
    public abstract void setScore(Integer score);

    @Override
    public abstract void endTurn();
}