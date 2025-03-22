package effect;

import insect.*;

public class ParalyzeEffect extends Effect {

    public ParalyzeEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setParalyzed(true);
        duration--;
        System.out.println("Kábító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setParalyzed(false);
        System.out.println("Kábító hatás lejárt");
    }
}
