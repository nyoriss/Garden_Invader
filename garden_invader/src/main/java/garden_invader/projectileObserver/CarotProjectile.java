package garden_invader.projectileObserver;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.entiteStrategy.IEntiteStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CarotProjectile implements Projectile{

    private ArrayList<EntiteObserver> entiteObs;

    private int positionX;
    private int positionY;
    private int width;
    private int height;

    private int speed;
    BufferedImage carrot;


    //TODO hitbox décalée vers la gauche et pas liée à l'image
    public CarotProjectile(IEntiteStrategy proprietaire, int positionX, int positionY) {
        this.width = 5;
        this.height = 10;
        this.speed = 4;
        this.positionX = positionX + proprietaire.getHeight()/2 - this.width /2;
        this.positionY = positionY;
        this.entiteObs = new ArrayList<>();

        try {
            carrot = ImageIO.read(new File("asset/sprite/carotte_tir3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enregistrerObs(EntiteObserver observer) {
        entiteObs.add(observer);
    }

    @Override
    public void supprimerObs(EntiteObserver observer) {

    }

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
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean update(GamePanel gp) {
        positionY -= speed;
        Entity entity = notifierObs();
        if (entity !=null) {
            //Blesse l'entité et vérifie si l'entité est supprimée
            if(entity.hurt(this)) { //TODO pourquoi le projectile disparait avant de toucher après une destruction d'oiseau
                gp.SupprimeEntiteDesEnnemis(entity);
            }
            gp.SupprimeDesProjectilesAllies(this);
            System.out.println("projectile supprimé par hitBox");
            return true;
        }
        return false;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(carrot,positionX, positionY, width * 5, height * 5, null);
    }

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
