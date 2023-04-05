package garden_invader.entiteStrategy;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.CarrotProjectile;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.RockProjectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public  abstract class Bird implements IEntityStrategy {

    private int positionX;
    private int positionY;
    private int height;
    private int width;

    //animations
    private int spriteNum;
    private int spriteCounter;

    //positionnement
    private int position;

    //statistiques
    private int attackDelay;

    private int nextAttackTick;

    private Random rand;

    //projectiles
    private ArrayList<Projectile> projectiles;

    /**
    * Constructeur de la classe Oiseau.
    *
    * @param posX      La position horizontale de l'objet.
    * @param posY      La position verticale de l'objet.
    * @param height   La largeur de l'objet.
    * @param width   La hauteur de l'objet.
    */
    public Bird(int posX, int posY, int height, int width) {
        this.positionX = posX;
        this.positionY = posY;
        this.width = width;
        this.height = height;
        this.spriteNum = 1;
        this.attackDelay = 200;
        this.rand = new Random();
        this.nextAttackTick = 500;
        projectiles = new ArrayList<>();
    }

    @Override
    public void setPositionX(int posX) {
        this.positionX = posX;
    }

    @Override
    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNextAttackTick(int nextAttackTick) {
        this.nextAttackTick = nextAttackTick;
        System.out.println("next attack tick dans bird"+nextAttackTick);
    }

    @Override
    public ArrayList<Integer> getHitBox() {
        ArrayList hitBox = new ArrayList();
        hitBox.add(positionX);
        hitBox.add(positionY);
        hitBox.add(height);
        hitBox.add(width);

        return hitBox;
    }

    /**
    * Verifie si l'objet actuel entre en collision avec un autre objet
    * specifie par les parametres posX, posY, largeur et hauteur.
    *
    * @param posX      La position horizontale de l'autre objet.
    * @param posY      La position verticale de l'autre objet.
    * @param largeur   La largeur de l'autre objet.
    * @param hauteur   La hauteur de l'autre objet.
    *
    * @return          "true" si une collision est detectee, "false" sinon.
    */
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (positionX < posX + largeur &&
            positionX + this.height > posX &&
            positionX < posY + hauteur &&
            positionX + this.width > posY) {
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    @Override
    public void update(GamePanel gp, KeyHandler keyHandler) {
        if (gp.tick - gp.birdMoveTick >= gp.birdMoveSpeed || gp.tick/ gp.birdMoveSpeed >= 10 * gp.birdMoveSpeed) {
            // TODO remettre dans GamePanel et appeler l'oiseau pour le faire monter/descendre

            positionY += gp.tick / gp.birdMoveSpeed % 2 == 0? 5 : -5;

            if (gp.tick / gp.birdMoveSpeed % gp.birdDescendSpeed == 0) {
                positionY += gp.tileSize/2;
            }
        }

        if(gp.tick%nextAttackTick==0 && gp.tick!=0) {
            Projectile projectile = new RockProjectile(this, positionX, positionY);

            addProjectile(projectile, gp);

            nextAttackTick = gp.tick + attackDelay + rand.nextInt(attackDelay/20)-attackDelay/10;
            System.out.println("tir à : "+gp.tick);
            System.out.println("next attack tick "+nextAttackTick);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update(gp);
        }

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

    public void addProjectile(Projectile projectile, GamePanel gp) {
        projectiles.add(projectile);
        for (Entity entity : gp.getPlayers()) {
            projectile.enregistrerObs(entity);
        }
    }

    public void removeFromProjectiles(Projectile projectile) {
        if(projectiles.contains(projectile))
            projectiles.remove(projectile);
    }

    public void drawProjectiles(GamePanel gp, Graphics2D g2) {
        for(int i = 0; i < projectiles.size(); i++) {
            //Deplacement des projectiles
            projectiles.get(i).draw(gp, g2);
        }
    }
}
