package garden_invader.entityStrategy;

import garden_invader.GamePanel;
import garden_invader.projectileObserver.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Crow extends Bird {

    private String spritePath;
    private Projectile projectileType;
    private BufferedImage draw;

    //statistiques
    private int attackDelay;

    /**
     * Initialise une nouvelle instance de la classe Corbeau avec la position,
     * la largeur et la hauteur specifiees, ainsi que les proprietes par defaut
     * pour un corbeau.
     *
     * @param posX      La position horizontale du corbeau.
     * @param posY      La position verticale du corbeau.
     * @param width     La largeur du corbeau.
     * @param height    La hauteur du corbeau.
     */
    public Crow(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.maxHP = 2;
        this.currentHP = maxHP;
        this.spritePath = "asset/sprite/corbo";
        this.attackDelay = 275;
        super.setAttackDelay(this.attackDelay);
    }

    @Override
    public BufferedImage getSprite() {
        return draw;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        //On r�cup�re l'image � afficher
        String currentSprite = spritePath +"_"+super.getSpriteNum()+".png";
        try {
            draw = ImageIO.read(new File(currentSprite));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(draw, super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth(), null);
    }
}
