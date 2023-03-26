package garden_invader.projectileObserver;

import garden_invader.Entite;
import garden_invader.GamePanel;
import garden_invader.entiteStrategy.IEntiteStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectileCarotte implements Projectile{

    private ArrayList<EntiteObserver> entiteObs;

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;

    private int speed;
    BufferedImage carotte;

    public ProjectileCarotte(IEntiteStrategy proprietaire, int positionX, int positionY) {
        this.largeur = 4;
        this.hauteur = 10;
        this.speed = 4;
        this.positionX = positionX + proprietaire.getLargeur()/2 - this.largeur/2;
        this.positionY = positionY;
        this.entiteObs = new ArrayList<>();

        try {
            carotte = ImageIO.read(new File("asset/sprite/carotte_tir.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enregistrerObs(EntiteObserver observer) {
        entiteObs.add(observer);
    }

    @Override
    public void supprimerObs(EntiteObserver observer) {

    }

    @Override
    public Entite notifierObs() { //Entite
        if (!(entiteObs.size()<=0)) {
            for (int i = 0; i < entiteObs.size(); i++) {
                if (entiteObs.get(i).actualiser(positionX, positionY, largeur, hauteur)) {
                    return (Entite) entiteObs.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public void setPositionY(int posY) {
        this.positionY = posY;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean update(GamePanel gp) {
        positionY -= speed;
        Entite entite = notifierObs();
        if (entite!=null) {
            //Blesse l'entité et vérifie si l'entité est supprimée
            if(entite.blesse(this)) { //TODO pourquoi le projectile disparait avant de toucher après une destruction d'oiseau
                gp.SupprimeEntiteDesEnnemis(entite);
            }
            gp.SupprimeDesProjectilesAllies(this);
            System.out.println("projectile supprimé par hitBox");
            return true;
        }
        return false;
    }

    @Override
    public void draw(GamePanel gp, Graphics2D g2) {
        g2.drawImage(carotte,positionX, positionY, largeur*5, hauteur*5, null);
    }

    public void setHitBox(ArrayList<Integer> hitBox) {
        if(hitBox.size()==4) {
            positionX = hitBox.get(0);
            positionY = hitBox.get(1);
            largeur = hitBox.get(2);
            hauteur = hitBox.get(3);
        } else {
            System.out.println("Taille de hitBox invalide");
        }
    }
}
