package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;

public class Martin_Pecheur extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String nom;
    private Projectile typeProjectile; //TODO nécessaire ?
    private Color couleur;

    public Martin_Pecheur(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 3;
        this.pvActuels = pvMax;
        this.nom = "Martin_Pecheur";
        couleur = Color.blue;
    }

    @Override
    public boolean blesse(Projectile projectile) {
        pvActuels--;
        System.out.println(nom+" blessé");
        if (pvActuels<=0) {
            System.out.println(nom+" mort");
        }
        return pvActuels<=0;
    }

    @Override
    public void upDate(GamePanel gp, KeyHandler keyHandler) {

    }

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
