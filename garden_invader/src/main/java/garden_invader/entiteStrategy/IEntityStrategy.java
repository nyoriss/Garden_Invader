package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * L'interface IEntityStrategy définit les méthodes nécessaires pour toutes les entités du jeu.
 * Ces méthodes permettent d'obtenir et de modifier la position, la taille, l'image et les hitboxes de l'entité.
 * Elle permet également de détecter les collisions et de gérer les dégâts infligés à l'entité.
 */
public interface IEntityStrategy {

    /**
     * Définit la position horizontale de l'entité.
     * @param posX la position horizontale de l'entité
     */
    void setPositionX(int posX);

    /**
     * Définit la position verticale de l'entité.
     * @param posY la position verticale de l'entité
     */
    void setPositionY(int posY);

    /**
     * Renvoie la position horizontale actuelle de l'entité.
     * @return la position horizontale de l'entité
     */
    int getPositionX();

    /**
     * Renvoie la position verticale actuelle de l'entité.
     * @return la position verticale de l'entité
     */
    int getPositionY();

    /**
     * Renvoie la hauteur de l'entité.
     * @return la hauteur de l'entité
     */
    int getHeight();

    /**
     * Renvoie la largeur de l'entité.
     * @return la largeur de l'entité
     */
    int getWidth();

    /**
     * Vérifie si l'entité entre en collision avec un autre objet.
     * @param posX la position horizontale de l'objet à tester
     * @param posY la position verticale de l'objet à tester
     * @param width la largeur de l'objet à tester
     * @param height la hauteur de l'objet à tester
     * @return true si l'entité entre en collision avec l'objet testé, sinon false
     */
    boolean collision(int posX, int posY, int width, int height);

    /**
     * Renvoie les hitboxes de l'entité.
     * @return les hitboxes de l'entité
     */
    ArrayList<Integer> getHitBox();

    /**
     * Gère les dégâts infligés à l'entité par un projectile.
     * @param projectile le projectile infligeant les dégâts
     * @return true si l'entité est détruite, sinon false
     */
    boolean hurt(Projectile projectile);

    /**
     * Met à jour l'entité en fonction des entrées utilisateur et du contexte de jeu.
     * @param gp le GamePanel dans lequel l'entité est affichée
     * @param keyHandler le KeyHandler gérant les entrées utilisateur
     */
    void upDate(GamePanel gp, KeyHandler keyHandler);

    /**
     * Renvoie l'image de l'entité.
     * @return l'image de l'entité
     */
    BufferedImage getSprite();

    /**
     * Affiche l'entité sur le GamePanel donné en argument.
     * @param gp le GamePanel sur lequel afficher l'entité
     * @param g2 le contexte graphique 2D sur lequel dessiner l'entité
     */
    void draw(GamePanel gp, Graphics2D g2);
}
