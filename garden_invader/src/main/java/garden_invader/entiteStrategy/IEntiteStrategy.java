package garden_invader.entiteStrategy;

import java.util.ArrayList;

public interface IEntiteStrategy {
    boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox);

    void setPositionX(int posX);

    void setPositionY(int posY);
    int getPositionX();

    int getPositionY();

    int getLargeur();

    int getHauteur();


    ArrayList<Integer> getHitBox();
}
