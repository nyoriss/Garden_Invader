package garden_invader.entiteStrategy;

import garden_invader.projectileObserver.Projectile;

public class Corbeau extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile; //TODO nécessaire ?

    public Corbeau(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 2;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Corbeau";
    }

    @Override
    public void blesse(Projectile projectile) {

    }
}
