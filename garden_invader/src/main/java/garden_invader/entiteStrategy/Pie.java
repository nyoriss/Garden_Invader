package garden_invader.entiteStrategy;

import garden_invader.GamePanel;
import garden_invader.KeyHandler;
import garden_invader.projectileObserver.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pie extends Oiseau{

    private int pvMax;
    private int pvActuels;
    private String sprite;
    private Projectile typeProjectile; //TODO nÃ©cessaire ?
    private Color couleur;
    private BufferedImage dessin;
    {
        try {
            dessin = ImageIO.read(new File("asset/sprite/pie_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Construit une nouvelle instance de la classe "Pie" avec la position et la
    * taille spécifiées. Initialise les variables de points de vie de l'oiseau
    * avec une valeur maximale de 1, une valeur actuelle de 1, un sprite et une
    * couleur spécifiques.
    *
    * @param posX La position X de l'oiseau.
    * @param posY La position Y de l'oiseau.
    * @param largeur La largeur de l'oiseau.
    * @param hauteur La hauteur de l'oiseau.
    */
    public Pie(int posX, int posY, int largeur, int hauteur) {
        super(posX, posY, largeur, hauteur);
        this.pvMax = 1;
        this.pvActuels = pvMax;
        this.sprite = "../../assets/Pie";
        this.dessin = dessin;
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
    public boolean blesse(Projectile projectile) {
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
    * @param largeur   La largeur de l'autre objet.
    * @param hauteur   La hauteur de l'autre objet.
    *
    * @return          "true" si une collision est détectée, "false" sinon.
    */
    @Override
    public boolean collision(int posX, int posY, int largeur, int hauteur) {
        if (super.getPositionX() < posX + largeur &&
            super.getPositionX() + super.getLargeur() > posX &&
            super.getPositionY() < posY + hauteur &&
            super.getPositionY() + super.getHauteur() > posY) {
            return true; // il y a une collision
        }
        return false; // il n'y a pas de collision
    }

    @Override
    public void upDate(GamePanel gp, KeyHandler keyHandler) {

    }

    //TODO supprimer
    public BufferedImage getDessin() {
        return dessin;
    }
}
