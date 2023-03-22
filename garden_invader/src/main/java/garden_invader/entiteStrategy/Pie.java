package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.ProjectileCarotte;

public class Pie extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile; //TODO nécessaire ?

    public Pie(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 1;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Pie";
    }

    @Override
    public void blesse(Projectile projectile) {
        pvActuels--;
        if (pvActuels<=0) {
            System.out.println("oiseau mort");
        }
    }

    @Override
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (super.getPositionX() + largeur < posX || super.getPositionX() > posX + largeur) {
            System.out.println("pas collision");
            return false; // pas de collision horizontale
        }
        if (super.getPositionY() + hauteur < posY || super.getPositionY() > posY + hauteur) {
            System.out.println("pas collision");
            return false; // pas de collision verticale
        }
        System.out.println("collision");
        return true; // il y a une collision
    }
}
