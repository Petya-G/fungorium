package effect;

import insect.*;

public class FastEffect extends Effect {

    public FastEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setSpeedModifier(2);
        duration--;
        System.out.println("Gyorsító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setSpeedModifier(0);
        duration--;
        System.out.println("Gyorsító hatás lejárt");
    }
}
