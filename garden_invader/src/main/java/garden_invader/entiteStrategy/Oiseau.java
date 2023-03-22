package garden_invader.entiteStrategy;

import java.util.ArrayList;

public  abstract class Oiseau implements IEntiteStrategy {

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;

    public Oiseau(int posX, int posY, int largeur, int hauteur) {
        this.positionX = posX;
        this.positionY = posY;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    @Override
    public boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox) {
        return false;
    }

    @Override
    public void setPositionX(int posX) {
        this.positionX = posX;
    }

    @Override
    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

    @Override
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

}
