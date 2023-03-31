package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;

import java.util.ArrayList;

public  abstract class Bird implements IEntityStrategy {

    private int positionX;
    private int positionY;
    private int height;
    private int width;

    //animations
    private int spriteNum;
    private int spriteCounter;

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
    * Vérifie si l'objet actuel entre en collision avec un autre objet
    * spécifié par les paramètres posX, posY, largeur et hauteur.
    *
    * @param posX      La position horizontale de l'autre objet.
    * @param posY      La position verticale de l'autre objet.
    * @param largeur   La largeur de l'autre objet.
    * @param hauteur   La hauteur de l'autre objet.
    *
    * @return          "true" si une collision est détectée, "false" sinon.
    */
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (positionX < posX + largeur &&
            positionX + this.height > posX &&
            positionX < posY + hauteur &&
            positionX + this.width > posY) {
            System.out.println("collision");
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    @Override
    public void update(GamePanel gp, KeyHandler keyHandler) {
        if (gp.tick - gp.birdMoveTick >= gp.birdMoveSpeed || gp.tick/ gp.birdMoveSpeed >= 10 * gp.birdMoveSpeed) {
            if (gp.tick / gp.birdMoveSpeed % 2 == 0) {
                positionY += 5;
            } else {
                positionY -= 5;
            }
            if (gp.tick / gp.birdMoveSpeed % gp.birdDescendSpeed == 0) {
                positionY += gp.tileSize/2;
            }
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

}
