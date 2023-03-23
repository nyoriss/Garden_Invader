package garden_invader.projectileObserver;

import garden_invader.Entite;

public interface Projectile {

    void enregistrerObs(EntiteObserver observer);

    void supprimerObs(EntiteObserver observer);

    Entite notifierObs();

    void setPositionY(int posY);

    int getPositionX();

    int getPositionY();

    int getLargeur();

    int getHauteur();

    int getSpeed();

}
