package garden_invader;

import garden_invader.entiteStrategy.IEntiteStrategy;
import garden_invader.projectileObserver.EntiteObserver;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.ProjectileCarotte;

import java.util.ArrayList;

public class Entite implements EntiteObserver {
    private IEntiteStrategy entite;

    public int positionX;
    public int positionY;
    private int largeur;
    private int hauteur;

    public Entite(IEntiteStrategy entite) {
        this.entite = entite;
    }


    @Override
    public boolean actualiser(int posX, int posY, int largeur, int hauteur) {
        return entite.collision(posX, posY, largeur, hauteur);
    }


    public void setPositionX(int posX) {
        entite.setPositionX(posX);
    }

    public void setPositionY(int posY) {
        entite.setPositionY(posY);
    }

    public int getPositionX() {
        return entite.getPositionX();
    }

    public int getPositionY() {
        return entite.getPositionY();
    }

    public int getLargeur() {
        return entite.getLargeur();
    }

    public int getHauteur() {
        return entite.getHauteur();
    }

    public void blesse(Projectile projectile) {
        entite.blesse(projectile);
    }

    public ArrayList<Integer> getHitBox() {
        return entite.getHitBox();
    }
}
