package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
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

    boolean blesse(Projectile projectile);

    void upDate(GamePanel gp, KeyHandler keyHandler);

    Color getCouleur();
}
