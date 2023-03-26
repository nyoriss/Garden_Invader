package garden_invader.projectileObserver;

public interface EntiteObserver {

    boolean actualiser(int posX, int posY, int width, int height);

    boolean hurt(Projectile projectile);
}
