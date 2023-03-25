package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.ProjectileCarotte;

import java.awt.*;
import java.util.ArrayList;

public class Lapin implements IEntiteStrategy{

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;
    private int speed;
    private int lastAttackTick;


    public Lapin(int posX, int posY, int largeur, int hauteur) {
        this.positionX = posX;
        this.positionY = posY;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.speed = 4;
        this.lastAttackTick = 0;
    }

    @Override
    public boolean hitBoxChevauche(ArrayList<Integer> ProjectileHitBox) {
        return false;
    }

    public void setPositionX(int posX) {
        this.positionX = posX;
    }

    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public ArrayList<Integer> getHitBox() {
        ArrayList hitBox = new ArrayList();
        hitBox.add(positionX);
        hitBox.add(positionY);
        hitBox.add(largeur);
        hitBox.add(hauteur);

        return hitBox;
    }

    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (positionX < posX + largeur &&
                positionX + largeur > posX &&
                positionY < posY + hauteur &&
                positionY + hauteur > posY) {

            System.out.println("collision");
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }


    public boolean blesse(Projectile projectile) {
        //TODO
        return false;
    }

    @Override
    public void upDate(GamePanel gp, KeyHandler keyHandler) {
        if(keyHandler.leftPressed) {
            if(positionX - 8 * speed >= 0)
                positionX -= speed;
        } else {
            if (keyHandler.rightPressed) {
                if(positionX+largeur + 8 * speed< gp.screenWidth)
                    positionX += speed;
            }
        }

        if(keyHandler.spacePressed && gp.tick-lastAttackTick >=35) {
            Projectile projectile = new ProjectileCarotte(this, positionX, positionY - hauteur);

            gp.addProjectile(projectile);
            lastAttackTick = gp.tick;
        }
    }

    @Override
    public Color getCouleur() {
        return null;
    }

}
