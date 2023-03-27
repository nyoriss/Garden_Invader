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

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile projectileType;
    private BufferedImage draw;

    /**
    * Construit une nouvelle instance de la classe "Pie" avec la position et la
    * taille spécifiées. Initialise les variables de points de vie de l'oiseau
    * avec une valeur maximale de 1, une valeur actuelle de 1, un sprite et une
    * couleur spécifiques.
    *
    * @param posX La position X de l'oiseau.
    * @param posY La position Y de l'oiseau.
    * @param width La largeur de l'oiseau.
    * @param height La hauteur de l'oiseau.
    */
    public Magpie(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.pvMax = 1;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Pie";
        try {
            draw = ImageIO.read(new File("asset/sprite/pie_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }

    /**
    * Diminue les points de vie actuels de l'oiseau en utilisant le projectile
    * spécifié. Si les points de vie actuels de l'oiseau sont inférieurs ou égaux
    * à zéro, l'oiseau est considéré comme mort.
    *
    * @param projectile Le projectile qui blesse l'oiseau.
    *
    * @return Vrai si l'oiseau est mort, faux sinon.
    */
    @Override
    public boolean hurt(Projectile projectile) {
        pvActuels--;
        if (pvActuels<=0) {
            System.out.println("oiseau mort");
        }
        return pvActuels<=0;
    }

    /**
    * Vérifie si l'objet actuel entre en collision avec un autre objet
    * spécifié par les paramètres posX, posY, largeur et hauteur.
    *
    * @param posX      La position horizontale de l'autre objet.
    * @param posY      La position verticale de l'autre objet.
    * @param width   La largeur de l'autre objet.
    * @param height   La hauteur de l'autre objet.
    *
    * @return          "true" si une collision est détectée, "false" sinon.
    */
    @Override
    public boolean collision(int posX, int posY, int width, int height) {
        if (super.getPositionX() < posX + width &&
            super.getPositionX() + super.getHeight() > posX &&
            super.getPositionY() < posY + height &&
            super.getPositionY() + super.getWidth() > posY) {
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    @Override
    public void upDate(GamePanel gp, KeyHandler keyHandler) {

    }

    @Override
    public BufferedImage getSprite() {
        return draw;
    }


    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(draw, super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth(), null);
    }
}
