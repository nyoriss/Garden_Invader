package garden_invader.entityStrategy;

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

    public BufferedImage getSprite() {
        return draw;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        //On récupère l'image à afficher
        String currentSprite = spritePath +"_"+super.getSpriteNum()+".png";
        try {
            draw = ImageIO.read(new File(currentSprite));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(draw, super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth(), null);
    }
}
