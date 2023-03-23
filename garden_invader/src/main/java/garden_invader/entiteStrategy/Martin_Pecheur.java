package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;

public class Martin_Pecheur extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile; //TODO nécessaire ?

    public Martin_Pecheur(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 3;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Martin_Pecheur";
    }

    @Override
    public boolean blesse(Projectile projectile) {

        return false;
    }
}
