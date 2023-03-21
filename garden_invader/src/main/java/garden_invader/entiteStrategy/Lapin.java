package garden_invader.entiteStrategy;

import java.util.ArrayList;

public class Lapin implements IEntiteStrategy{
    public Lapin() {

    }

    @Override
    public boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox) {
        return false;
    }
}
