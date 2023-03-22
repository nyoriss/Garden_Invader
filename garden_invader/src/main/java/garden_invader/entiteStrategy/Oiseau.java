package garden_invader.entiteStrategy;

import java.util.ArrayList;

public class Oiseau implements IEntiteStrategy {
    public Oiseau() {

    }

    @Override
    public boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox) {
        return false;
    }

    @Override
    public void setPositionX(int posX) {

    }

    @Override
    public void setPositionY(int posY) {

    }

    @Override
    public int getPositionX() {
        return 0;
    }

    @Override
    public int getPositionY() {
        return 0;
    }

    @Override
    public int getLargeur() {
        return 0;
    }

    @Override
    public int getHauteur() {
        return 0;
    }

    @Override
    public ArrayList<Integer> getHitBox() {
        return null;
    }
}
