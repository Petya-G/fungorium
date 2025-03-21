package effect;
import insect.*;

public abstract class Effect {
    int duration;

    public int getDuration() {
        return duration;
    }
    public abstract void apply(Insect I);
    public abstract void remove(Insect I);
}
