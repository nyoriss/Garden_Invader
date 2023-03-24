package garden_invader.projectileObserver;

import garden_invader.Entite;
import garden_invader.GamePanel;

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

    boolean update(GamePanel gp);

}
