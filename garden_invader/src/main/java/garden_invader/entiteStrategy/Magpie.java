package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Magpie extends Bird {

    private int maxHp;
    private int currentHp;
    private String spritePath;
    private Projectile projectileType;
    private BufferedImage draw;

    //statistiques
    private int attackDelay;

    /**
    * Construit une nouvelle instance de la classe "Pie" avec la position et la
    * taille specifiees. Initialise les variables de points de vie de l'oiseau
    * avec une valeur maximale de 1, une valeur actuelle de 1, un sprite et une
    * couleur specifiques.
    *
    * @param posX La position X de l'oiseau.
    * @param posY La position Y de l'oiseau.
    * @param width La largeur de l'oiseau.
    * @param height La hauteur de l'oiseau.
    */
    public Magpie(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.maxHp = 1;
        this.currentHp = maxHp;
        this.spritePath = "asset/sprite/pie";
        this.attackDelay = 200;
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
        currentHp--;
        return currentHp <=0;
    }

    /**
    * Verifie si l'objet actuel entre en collision avec un autre objet
    * specifie par les parametres posX, posY, largeur et hauteur.
    *
    * @param posX      La position horizontale de l'autre objet.
    * @param posY      La position verticale de l'autre objet.
    * @param width   La largeur de l'autre objet.
    * @param height   La hauteur de l'autre objet.
    *
    * @return          "true" si une collision est detectee, "false" sinon.
    */
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

    @Override
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
