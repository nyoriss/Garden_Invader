package garden_invader;

import garden_invader.entityStrategy.IEntityStrategy;
import garden_invader.projectileObserver.EntityObserver;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity implements EntityObserver {
    private IEntityStrategy entity;

    public Entity(IEntityStrategy entity) {
        this.entity = entity;
    }


    @Override
    public boolean actualiser(int posX, int posY, int largeur, int hauteur) {
        return entity.collision(posX, posY, largeur, hauteur);
    }


    public void setPositionX(int posX) {
        entity.setPositionX(posX);
    }

    public void setPositionY(int posY) {
        entity.setPositionY(posY);
    }

    public void setNextAttackTick(int nextAttackTick) {
        entity.setNextAttackTick(nextAttackTick);
    }

    public int getPositionX() {
        return entity.getPositionX();
    }

    public int getPositionY() {
        return entity.getPositionY();
    }

    public int getLargeur() {
        return entity.getHeight();
    }

    public int getHauteur() {
        return entity.getWidth();
    }

    public BufferedImage getSprite() {
        return entity.getSprite();
    }

    public void draw(GamePanel gp, Graphics2D g2){
        entity.draw(gp, g2);}

    public boolean hurt(Projectile projectile) {
        return entity.hurt(projectile);
    }

    public void update(GamePanel gp, KeyHandler keyHandler) {
        entity.update(gp, keyHandler);
    }

    public int getCurrentHP() {
        return entity.getCurrentHP();
    }
}
