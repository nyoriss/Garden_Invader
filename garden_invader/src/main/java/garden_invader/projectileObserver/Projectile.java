package garden_invader.projectileObserver;

import garden_invader.Entite;
import garden_invader.GamePanel;

import java.awt.*;

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

    void draw(GamePanel gp, Graphics2D g2);

}
