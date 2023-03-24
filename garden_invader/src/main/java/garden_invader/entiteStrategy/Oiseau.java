package garden_invader.entiteStrategy;

import java.util.ArrayList;

public  abstract class Oiseau implements IEntiteStrategy {

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;

    /**
    * Constructeur de la classe Oiseau.
    *
    * @param posX      La position horizontale de l'objet.
    * @param posY      La position verticale de l'objet.
    * @param largeur   La largeur de l'objet.
    * @param hauteur   La hauteur de l'objet.
    */
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

    /**
    * Vérifie si l'objet actuel entre en collision avec un autre objet
    * spécifié par les paramètres posX, posY, largeur et hauteur.
    *
    * @param posX      La position horizontale de l'autre objet.
    * @param posY      La position verticale de l'autre objet.
    * @param largeur   La largeur de l'autre objet.
    * @param hauteur   La hauteur de l'autre objet.
    *
    * @return          "true" si une collision est détectée, "false" sinon.
    */
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (positionX < posX + largeur &&
            positionX + this.largeur > posX &&
            positionX < posY + hauteur &&
            positionX + this.hauteur > posY) {
            System.out.println("collision");
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

}
