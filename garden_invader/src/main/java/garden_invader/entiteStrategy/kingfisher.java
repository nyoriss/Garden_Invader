package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class kingfisher extends Bird {

    private int pvMax;
    private int pvActuels;
    private String nom;
    private Projectile projectileType;
    private BufferedImage draw;

    /**
    * Constructeur de la classe Martin_Pecheur.
    * @param posX      La position horizontale de l'objet.
    * @param posY      La position verticale de l'objet.
    * @param width   La largeur de l'objet.
    * @param height   La hauteur de l'objet.
    */
    public kingfisher(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.pvMax = 3;
        this.pvActuels = pvMax;
        this.nom = "Martin_Pecheur";
        try {
            draw = ImageIO.read(new File("asset/sprite/martin_pecheur_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
        System.out.println(nom+" blessÃ©");
        if (pvActuels<=0) {
            System.out.println(nom+" mort");
        }
        return pvActuels<=0;
    }

    @Override
    public void update(GamePanel gp, KeyHandler keyHandler) {

    }

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

    public BufferedImage getSprite() {
        return draw;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(draw, super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth(), null);
    }
}
