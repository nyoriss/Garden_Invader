package garden_invader.projectileObserver;

public interface EntiteObserver {

    boolean actualiser(int posX, int posY, int largeur, int hauteur);

    boolean blesse(Projectile projectile);
}
