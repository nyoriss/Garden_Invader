package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * L'interface IEntityStrategy definit les methodes necessaires pour toutes les entites du jeu.
 * Ces methodes permettent d'obtenir et de modifier la position, la taille, l'image et les hitboxes de l'entite.
 * Elle permet egalement de detecter les collisions et de gerer les degâts infliges à l'entite.
 */
public interface IEntityStrategy {


    /**
     * Definit la position horizontale de l'entite.
     * @param posX la position horizontale de l'entite
     */
    void setPositionX(int posX);

    /**
     * Definit la position verticale de l'entite.
     * @param posY la position verticale de l'entite
     */
    void setPositionY(int posY);

    void setNextAttackTick(int nextAttackTick);

    /**
     * Renvoie la position horizontale actuelle de l'entite.
     * @return la position horizontale de l'entite
     */
    int getPositionX();

    /**
     * Renvoie la position verticale actuelle de l'entite.
     * @return la position verticale de l'entite
     */
    int getPositionY();

    /**
     * Renvoie la hauteur de l'entite.
     * @return la hauteur de l'entite
     */
    int getHeight();

    /**
     * Renvoie la largeur de l'entite.
     * @return la largeur de l'entite
     */
    int getWidth();

    /**
     * Verifie si l'entite entre en collision avec un autre objet.
     * @param posX la position horizontale de l'objet à tester
     * @param posY la position verticale de l'objet à tester
     * @param width la largeur de l'objet à tester
     * @param height la hauteur de l'objet à tester
     * @return true si l'entite entre en collision avec l'objet teste, sinon false
     */
    boolean collision(int posX, int posY, int width, int height);

    /**
     * Renvoie les hitboxes de l'entite.
     * @return les hitboxes de l'entite
     */
    ArrayList<Integer> getHitBox();

    /**
     * Gère les degâts infliges à l'entite par un projectile.
     * @param projectile le projectile infligeant les degâts
     * @return true si l'entite est detruite, sinon false
     */
    boolean hurt(Projectile projectile);

    /**
     * Met à jour l'entite en fonction des entrees utilisateur et du contexte de jeu.
     * @param gp le GamePanel dans lequel l'entite est affichee
     * @param keyHandler le KeyHandler gerant les entrees utilisateur
     */
    void update(GamePanel gp, KeyHandler keyHandler);

    /**
     * Renvoie l'image de l'entite.
     * @return l'image de l'entite
     */
    BufferedImage getSprite();

    /**
     * Affiche l'entite sur le GamePanel donne en argument.
     * @param gp le GamePanel sur lequel afficher l'entite
     * @param g2 le contexte graphique 2D sur lequel dessiner l'entite
     */
    void draw(GamePanel gp, Graphics2D g2);

    int getCurrentHP();
}
