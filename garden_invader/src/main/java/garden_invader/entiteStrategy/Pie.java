package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.ProjectileCarotte;

public class Pie extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile;

    public Pie(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 1;
        this.pvActuels = pvMax;
        this.sprite = "../assets/Pie";
        //this.typeProjectile = new Projectile(); //
    }
}
