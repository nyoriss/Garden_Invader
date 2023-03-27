package garden_invader;

import garden_invader.entiteStrategy.IEntityStrategy;
import garden_invader.projectileObserver.EntityObserver;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity implements EntityObserver {
    private IEntityStrategy entite;

    public Entity(IEntityStrategy entite) {
        this.entite = entite;
    }


    @Override
    public boolean actualiser(int posX, int posY, int largeur, int hauteur) {
        return entite.collision(posX, posY, largeur, hauteur);
    }


    public void setPositionX(int posX) {
        entite.setPositionX(posX);
    }

    public void setPositionY(int posY) {
        entite.setPositionY(posY);
    }

    public int getPositionX() {
        return entite.getPositionX();
    }

    public int getPositionY() {
        return entite.getPositionY();
    }

    public int getLargeur() {
        return entite.getHeight();
    }

    public int getHauteur() {
        return entite.getWidth();
    }

    public BufferedImage getSprite() {
        return entite.getSprite();
    }

    public void draw(GamePanel gp, Graphics2D g2){entite.draw(gp, g2);}

    public boolean hurt(Projectile projectile) {
        return entite.hurt(projectile);
    }

    public void update(GamePanel gp, KeyHandler keyHandler) {
        entite.upDate(gp, keyHandler);
    }
}
