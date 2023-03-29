package garden_invader.projectileObserver;

import garden_invader.Entity;
import garden_invader.GamePanel;

import java.awt.*;

/**
 * Interface pour les projectiles tirés par les entités du jeu.
 */
public interface Projectile {

    /**
     * Enregistre un observateur de collision pour le projectile.
     * @param observer l'observateur à enregistrer
     */
    void enregistrerObs(EntityObserver observer);

    /**
     * Supprime un observateur de collision pour le projectile.
     * @param observer l'observateur à supprimer
     */
    void supprimerObs(EntityObserver observer);

    /**
     * Notifie les observateurs de collision si une collision a eu lieu.
     * @return l'entité touchée par le projectile ou null s'il n'y a pas de collision
     */
    Entity notifierObs();

    /**
     * Modifie la position Y du projectile.
     * @param posY la nouvelle position Y du projectile
     */
    void setPositionY(int posY);

    /**
     * Retourne la position X du projectile.
     * @return la position X du projectile
     */
    int getPositionX();

    /**
     * Retourne la position Y du projectile.
     * @return la position Y du projectile
     */
    int getPositionY();

    /**
     * Retourne la largeur du projectile.
     * @return la largeur du projectile
     */
    int getWidth();

    /**
     * Retourne la hauteur du projectile.
     * @return la hauteur du projectile
     */
    int getHeight();

    /**
     * Retourne la vitesse du projectile.
     * @return la vitesse du projectile
     */
    int getSpeed();

    /**
     * Met à jour la position du projectile et gère les collisions.
     * @param gp le GamePanel du jeu
     * @return true si le projectile a été supprimé, false sinon
     */
    boolean update(GamePanel gp);

    /**
     * Dessine le projectile sur le GamePanel du jeu.
     * @param gp le GamePanel du jeu
     * @param g2 le contexte graphique pour dessiner le projectile
     */
    void draw(GamePanel gp, Graphics2D g2);

}
