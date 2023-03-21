package garden_invader.entiteStrategy;

import java.util.ArrayList;

public class Oiseau implements IEntiteStrategy {
    public Oiseau() {

    }

    @Override
    public boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox) {
        return false;
    }
}
