package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;

public class Pie extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile; //TODO nécessaire ?
    private Color couleur;

    public Pie(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 1;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Pie";
        couleur = Color.red;
    }

    @Override
    public boolean blesse(Projectile projectile) {
        pvActuels--;
        if (pvActuels<=0) {
            System.out.println("oiseau mort");
        }
        return pvActuels<=0;
    }

    @Override
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (super.getPositionX() < posX + largeur &&
            super.getPositionX() + super.getLargeur() > posX &&
            super.getPositionY() < posY + hauteur &&
            super.getPositionY() + super.getHauteur() > posY) {
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    @Override
    public void upDate(GamePanel gp, KeyHandler keyHandler) {

    }

    //TODO supprimer
    public Color getCouleur() {
        return couleur;
    }
}
