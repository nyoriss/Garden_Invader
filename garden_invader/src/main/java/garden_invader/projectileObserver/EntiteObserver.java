package garden_invader.projectileObserver;

public interface EntiteObserver {

    boolean actualiser(int posX, int posY, int largeur, int hauteur);

    void blesse(Projectile projectile);
}
