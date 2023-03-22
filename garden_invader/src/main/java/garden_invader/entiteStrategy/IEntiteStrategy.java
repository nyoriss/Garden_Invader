package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;

import java.util.ArrayList;

public interface IEntiteStrategy {
    boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox);

    void setPositionX(int posX);

    void setPositionY(int posY);
    int getPositionX();

    int getPositionY();

    int getLargeur();

    int getHauteur();

    boolean collision(int posX, int posY, int largeur, int hauteur);

    ArrayList<Integer> getHitBox();

    void blesse(Projectile projectile);
}
