package garden_invader.projectileObserver;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.entiteStrategy.Rabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Cette classe représente un projectile de carotte tiré par un lapin.
 * Elle implémente l'interface Projectile.
 */
public class CarrotProjectile implements Projectile{

    private ArrayList<EntityObserver> entiteObs;

    private Rabbit owner;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    private int speed;
    BufferedImage carrot;


    /**
     * Constructeur de la classe CarrotProjectile.
     * @param owner Le lapin qui tire la carotte.
     * @param positionX La position x de la carotte.
     * @param positionY La position y de la carotte.
     */
    public CarrotProjectile(Rabbit owner, int positionX, int positionY) {
        this.width = 6*3;
        this.height = 12*4;
        this.speed = 4;
        this.positionX = positionX + owner.getHeight()/2 - this.width /2;
        this.positionY = positionY;
        this.entiteObs = new ArrayList<>();

        this.owner = owner;
        try {
            carrot = ImageIO.read(new File("asset/sprite/carotte_tir_crop.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permet d'ajouter un observateur à la liste des observateurs.
     * @param observer L'observateur à ajouter.
     */
    @Override
    public void enregistrerObs(EntityObserver observer) {
        entiteObs.add(observer);
    }

    /**
     * Permet de supprimer un observateur de la liste des observateurs.
     * @param observer L'observateur à supprimer.
     */
    @Override
    public void supprimerObs(EntityObserver observer) {
        entiteObs.remove(observer);
    }

    /**
     * Notifie les observateurs de la présence de la carotte.
     * @return l'entité touchée par le projectile, null si aucune entité n'a été touchée.
     */
    @Override
    public Entity notifierObs() { //Entite
        if (!(entiteObs.size()<=0)) {
            for (int i = 0; i < entiteObs.size(); i++) {
                if (entiteObs.get(i).actualiser(positionX, positionY, width, height)) {
                    return (Entity) entiteObs.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Change la position y de la carotte.
     * @param posY La nouvelle position y de la carotte.
     */
    @Override
    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    /**
     * Retourne la position x de la carotte.
     * @return La position x de la carotte.
     */
    @Override
    public int getPositionX() {
        return positionX;
    }

    /**
     * Retourne la position y de la carotte.
     * @return La position y de la carotte.
     */
    @Override
    public int getPositionY() {
        return positionY;
    }

    /**
     * Retourne la largeur de la carotte.
     * @return La largeur de la carotte.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur de la carotte.
     * @return La hauteur de la carotte.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Retourne la vitesse de la carotte.
     * @return La vitesse de la carotte.
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Met à jour la position de la carotte en fonction de sa vitesse.
     * @param gp Le GamePanel sur lequel la carotte est dessinée.
     * @return true si la carotte a touché une entité et doit être supprimée, false sinon.
     */
    @Override
    public boolean update(GamePanel gp) {
        positionY -= speed;
        Entity entity = notifierObs();
        if (entity !=null) {
            //Blesse l'entité et vérifie si l'entité est supprimée
            if(entity.hurt(this)) { //TODO pourquoi le projectile disparait avant de toucher après une destruction d'oiseau
                gp.removeFromEnemies(entity);
            }
            owner.removeFromAlliedProjectiles(this);
            System.out.println("projectile supprimé par hitBox");
            return true;
        }
        return false;
    }

    /**
     * Dessine la carotte sur le GamePanel.
     * @param gp Le GamePanel sur lequel la carotte est dessinée.
     * @param g2 Le contexte graphique.
     */
    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(carrot,positionX, positionY, width, height, null);
    }

    /**
     * Permet de définir la hitbox de la carotte à partir d'un tableau d'entiers.
     * @param hitBox La hitbox à dé
     * */
    public void setHitBox(ArrayList<Integer> hitBox) {
        if(hitBox.size()==4) {
            positionX = hitBox.get(0);
            positionY = hitBox.get(1);
            width = hitBox.get(2);
            height = hitBox.get(3);
        } else {
            System.out.println("Taille de hitBox invalide");
        }
    }
}
