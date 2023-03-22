package garden_invader.projectileObserver;

public interface Projectile {

    void enregistrerObs(EntiteObserver observer);

    void supprimerObs(EntiteObserver observer);

    boolean notifierObs();

    void setPositionY(int posY);

    int getPositionX();

    int getPositionY();

    int getLargeur();

    int getHauteur();

    int getSpeed();

}
