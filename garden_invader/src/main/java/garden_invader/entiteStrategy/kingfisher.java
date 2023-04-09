package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.projectileObserver.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class kingfisher extends Bird {

    private String spritePath;
    private Projectile projectileType;
    private BufferedImage draw;

    //statistiques
    private int attackDelay;

    /**
    * Constructeur de la classe Martin_Pecheur.
    * @param posX     La position horizontale de l'objet.
    * @param posY     La position verticale de l'objet.
    * @param width    La largeur de l'objet.
    * @param height   La hauteur de l'objet.
    */
    public kingfisher(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.maxHP = 3;
        this.currentHP = maxHP;
        this.spritePath = "asset/sprite/martin_pecheur";
        this.attackDelay = 200;
        super.setAttackDelay(this.attackDelay);
    }

    /**
    * Diminue les points de vie actuels de l'oiseau en utilisant le projectile
    * specifie. Si les points de vie actuels de l'oiseau sont inferieurs ou egaux
    * a zero, l'oiseau est considere comme mort.
    *
    * @param projectile Le projectile qui blesse l'oiseau.
    *
    * @return Vrai si l'oiseau est mort, faux sinon.
    */
    @Override
    public boolean hurt(Projectile projectile) {
        currentHP--;
        return currentHP <=0; //TODO récupérer le nombre de PV après
    }

    @Override
    public boolean collision(int posX, int posY, int width, int height) {
        if (super.getPositionX() < posX + width &&
            super.getPositionX() + super.getHeight() > posX &&
            super.getPositionY() < posY + height &&
            super.getPositionY() + super.getWidth() > posY)
        {
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    public BufferedImage getSprite() {
        return draw;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        String currentSprite = spritePath +"_"+super.getSpriteNum()+".png";
        try {
            draw = ImageIO.read(new File(currentSprite));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(draw, super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth(), null);
        super.drawProjectiles(gp, g2);
    }
}
