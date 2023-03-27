package garden_invader.entiteStrategy;

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

public class Rabbit implements IEntiteStrategy{

    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private int speed;
    private int lastAttackTick;

    private BufferedImage rabbit;


    public Rabbit(int posX, int posY, int width, int height) {
        this.positionX = posX;
        this.positionY = posY;
        this.height = height;
        this.width = width;
        this.speed = 4;
        this.lastAttackTick = 0;


        try {
            rabbit = ImageIO.read(new File("asset/sprite/lapin.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public boolean collision(int posX, int posY, int width, int hauteur) {
        if (positionX < posX + width &&
                positionX + width > posX &&
                positionY < posY + hauteur &&
                positionY + hauteur > posY) {

            System.out.println("collision");
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }


    public boolean hurt(Projectile projectile) {
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
                if(positionX+ width + 8 * speed< gp.screenWidth)
                    positionX += speed;
            }
        }

        if(keyHandler.spacePressed && gp.tick-lastAttackTick >=35) {
            Projectile projectile = new CarrotProjectile(this, positionX, positionY - height);

            gp.addProjectile(projectile);
            lastAttackTick = gp.tick;
        }
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(rabbit,positionX, positionY, gp.tileSize, gp.tileSize, null);
    }

}
