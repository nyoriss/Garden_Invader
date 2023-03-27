package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface IEntityStrategy {

    void setPositionX(int posX);

    void setPositionY(int posY);

    int getPositionX();

    int getPositionY();

    int getHeight();

    int getWidth();

    boolean collision(int posX, int posY, int width, int height);

    ArrayList<Integer> getHitBox();

    boolean hurt(Projectile projectile);

    void upDate(GamePanel gp, KeyHandler keyHandler);

    BufferedImage getSprite();

    void draw(GamePanel gp, Graphics2D g2);
}
