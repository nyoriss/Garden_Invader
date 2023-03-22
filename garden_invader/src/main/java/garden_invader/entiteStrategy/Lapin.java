package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;

import java.util.ArrayList;

public class Lapin implements IEntiteStrategy{

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;

    public Lapin(int posX, int posY, int largeur, int hauteur) {
        this.positionX = posX;
        this.positionY = posY;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    @Override
    public boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox) {
        return false;
    }

    public void setPositionX(int posX) {
        this.positionX = posX;
    }

    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public ArrayList<Integer> getHitBox() {
        ArrayList hitBox = new ArrayList();
        hitBox.add(positionX);
        hitBox.add(positionY);
        hitBox.add(largeur);
        hitBox.add(hauteur);

        return hitBox;
    }

    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (positionX + largeur < posX || positionX > posX + largeur) {
            return false; // pas de collision horizontale
        }
        if (positionY + hauteur < posY || positionY > posY + hauteur) {
            return false; // pas de collision verticale
        }
        return true; // il y a une collision
    }


    public void blesse(Projectile projectile) {
        //TODO
    }

}
