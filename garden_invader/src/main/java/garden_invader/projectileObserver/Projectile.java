package garden_invader.projectileObserver;

public interface Projectile {

    void enregistrerObs(EntiteObserver observer);

    void supprimerObs(EntiteObserver observer);

    void notifierObs();

}
