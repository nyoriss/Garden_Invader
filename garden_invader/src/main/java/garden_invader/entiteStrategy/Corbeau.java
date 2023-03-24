package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;

import java.awt.*;

public class Corbeau extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile; //TODO nÃ©cessaire ?
    private Color couleur;

    /**
     * Initialise une nouvelle instance de la classe Corbeau avec la position,
     * la largeur et la hauteur spécifiées, ainsi que les propriétés par défaut
     * pour un corbeau.
     *
     * @param posX      La position horizontale du corbeau.
     * @param posY      La position verticale du corbeau.
     * @param largeur   La largeur du corbeau.
     * @param hauteur   La hauteur du corbeau.
     */
    public Corbeau(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 2;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Corbeau";
        couleur = Color.gray;
    }

    /**
    * Diminue les points de vie actuels de l'oiseau en utilisant le projectile
    * spécifié. Si les points de vie actuels de l'oiseau sont inférieurs ou égaux
    * à zéro, l'oiseau est considéré comme mort.
    *
    * @param projectile Le projectile qui blesse l'oiseau.
    *
    * @return Vrai si l'oiseau est mort, faux sinon.
    */
    @Override
    public boolean blesse(Projectile projectile) {
        pvActuels--;
        if (pvActuels<=0) {
            System.out.println("oiseau mort");
        }
        return pvActuels<=0;
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
    @Override
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (super.getPositionX() < posX + largeur &&
                super.getPositionX() + super.getLargeur() > posX &&
                super.getPositionY() < posY + hauteur &&
                super.getPositionY() + super.getHauteur() > posY) {

            System.out.println("collision");
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    public Color getCouleur() {
        return couleur;
    }
}
