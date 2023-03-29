package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Crow extends Bird {

    private int maxHp;
    private int currentHp;
    private String sprite;
    private Projectile projectileType;
    private BufferedImage draw;

    /**
     * Initialise une nouvelle instance de la classe Corbeau avec la position,
     * la largeur et la hauteur sp�cifi�es, ainsi que les propri�t�s par d�faut
     * pour un corbeau.
     *
     * @param posX      La position horizontale du corbeau.
     * @param posY      La position verticale du corbeau.
     * @param width   La largeur du corbeau.
     * @param height   La hauteur du corbeau.
     */
    public Crow(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.maxHp = 2;
        this.currentHp = maxHp;
        this.sprite = "../../assets/Corbeau";
        try {
            draw = ImageIO.read(new File("asset/sprite/corbo_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
    }

    /**
    * Diminue les points de vie actuels de l'oiseau en utilisant le projectile
    * sp�cifi�. Si les points de vie actuels de l'oiseau sont inf�rieurs ou �gaux
    * � z�ro, l'oiseau est consid�r� comme mort.
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


    @Override
    public void update(GamePanel gp, KeyHandler keyHandler) {

    }

    @Override
    public BufferedImage getSprite() {
        return draw;
    }

    @Override
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (super.getPositionX() < posX + largeur &&
                super.getPositionX() + super.getHeight() > posX &&
                super.getPositionY() < posY + hauteur &&
                super.getPositionY() + super.getWidth() > posY) {

            System.out.println("collision");
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(draw, super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth(), null);
    }
}
