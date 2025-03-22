package effect;

import insect.*;

public class ClawParalyzeEffect extends Effect {

    public ClawParalyzeEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
       i.setClawParalyzed(true);
       duration--;
       System.out.println("Rágó kábító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setClawParalyzed(false);
        System.out.println("Rágó kábító hatás lejárt");
    }
}
