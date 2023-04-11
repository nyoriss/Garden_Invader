package garden_invader.entiteStrategy;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.CarrotProjectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe representant le personnage jouable, un lapin.
 */
public class Rabbit implements IEntityStrategy {

    private int positionX;
    private int positionY;
    private int width;
    private int height;

    //movement parameters
    private int speed;

    //attack parameters
    private int lastAttackTick;
    private int attackDelay;

    // animations parameters
    private String movement;
    private boolean shooting;
    private int spriteNum;
    private int spriteCounter;
    private String spritePath;

    //projectiles
    private ArrayList<Projectile> alliedProjectiles;

    //Health
    private int maxHP;
    private int currentHP;

    /**
     * Constructeur de la classe Rabbit.
     *
     * @param posX la position X initiale du lapin
     * @param posY la position Y initiale du lapin
     * @param width la largeur du lapin
     * @param height la hauteur du lapin
     */
    public Rabbit(int posX, int posY, int width, int height) {
        this.positionX = posX;
        this.positionY = posY;
        this.height = height;
        this.width = width;
        this.speed = 4;
        this.lastAttackTick = 0;
        this.attackDelay = 35;
        this.spriteCounter = 0;
        this.spriteNum = 1;
        this.spritePath = "asset/sprite/rabbit/lapin";
        this.alliedProjectiles = new ArrayList<>();
        this.maxHP = 3;
        this.currentHP = maxHP;
    }

    public void setPositionX(int posX) {
        this.positionX = posX;
    }

    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    @Override
    public void setNextAttackTick(int nextAttackTick) {
        //not used now
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getHeight() {
        return width;
    }

    public int getWidth() {
        return height;
    }

    public ArrayList<Integer> getHitBox() {
        ArrayList hitBox = new ArrayList();
        hitBox.add(positionX);
        hitBox.add(positionY);
        hitBox.add(width);
        hitBox.add(height);

        return hitBox;
    }

    public boolean collision(int posX, int posY, int width, int height) {
        if (positionX < posX + width &&
                positionX + this.width > posX &&
                positionY < posY + height &&
                positionY + this.height > posY) {
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    public boolean hurt(Projectile projectile) {
        this.currentHP --;
        return currentHP<=0;
    }

    @Override
    public void update(GamePanel gp, KeyHandler keyHandler) {
        //Mouvement vers la gauche
        if(keyHandler.leftPressed) {
            if(positionX >= 8*speed) {
                movement = "movement";
                positionX -= speed;
            }
        }
        //Mouvement vers la droite
        else if (keyHandler.rightPressed) {
            if(positionX+ width < gp.screenWidth - 8*speed) {
                movement = "movement";
                positionX += speed;
            }
        } else {
            //Pas de mouvement
            movement = "stand";
        }

        //Gestion de tir
        if(keyHandler.spacePressed && gp.tick-lastAttackTick >= attackDelay) {
            shooting = true;
            Projectile projectile = new CarrotProjectile(this, positionX, positionY - height);

            addProjectile(projectile, gp);
            lastAttackTick = gp.tick;
        }

        //Arrête de l'animation de tir
        if(gp.tick-lastAttackTick>= attackDelay/2) {
            shooting = false;
        }

        //Gestion des collisions entre oiseaux et projectiles
        for (int i = 0; i < alliedProjectiles.size(); i++) {
            Projectile projectile = alliedProjectiles.get(i);
            //Si le projectile touche
            if (projectile.update(gp)) {
                i--; //Decrementer l'index pour compenser la suppression
            }
        }

        //Afin d'actualiser les sprites
        spriteCounter ++;
        if (spriteCounter>10) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        String currentSpritePath = spritePath;

        BufferedImage image;

        //s'il y a un mouvement
        if(!movement.equals("stand")) {
            currentSpritePath += "_"+movement+spriteNum;
        }

        //si le lapin tire
        if(shooting) {
            currentSpritePath += "_shooting";
        }

        try {
            image = ImageIO.read(new File(currentSpritePath+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image,positionX, positionY, gp.tileSize, gp.tileSize, null);
        for(int i = 0; i < alliedProjectiles.size(); i++) {
            //Deplacement des projectiles
            alliedProjectiles.get(i).draw(gp, g2);
        }
    }

    /**
     * Ajoute un projectile à la liste des projectiles allies et enregistre les entites ennemies comme observateurs.
     *
     * @param projectile le projectile à ajouter
     * @param gp le GamePanel contenant les entites ennemies
     */
    public void addProjectile(Projectile projectile, GamePanel gp) {
        alliedProjectiles.add(projectile);
        for (Entity entity : gp.getEnemies()) {
            projectile.enregistrerObs(entity);
        }
    }

    /**
     * Supprime un projectile de la liste des projectiles allies s'il y est present.
     *
     * @param projectile le projectile à supprimer
     */
    public void removeFromAlliedProjectiles(Projectile projectile) {
        if(alliedProjectiles.contains(projectile))
            alliedProjectiles.remove(projectile);
    }


    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }
}
