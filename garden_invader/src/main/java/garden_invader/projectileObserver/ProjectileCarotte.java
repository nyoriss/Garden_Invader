package garden_invader.projectileObserver;

import garden_invader.Entite;

import java.util.ArrayList;

public class ProjectileCarotte implements Projectile{

    private EntiteObserver entiteObs;

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;

    private int speed;

    public ProjectileCarotte(Entite proprietaire, int positionX, int positionY) {
        this.largeur = 4;
        this.hauteur = 8;
        this.speed = 8;
        this.positionX = positionX + proprietaire.getLargeur()/2 - this.largeur/2;
        System.out.println(proprietaire.getLargeur());
        this.positionY = positionY;
    }

    @Override
    public void enregistrerObs(EntiteObserver observer) {

    }

    @Override
    public void supprimerObs(EntiteObserver observer) {

    }

    @Override
    public void notifierObs() {

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

    public ArrayList<Integer> getHitBox() {
        ArrayList hitBox = new ArrayList();
        hitBox.add(positionX);
        hitBox.add(positionY);
        hitBox.add(largeur);
        hitBox.add(hauteur);

        return hitBox;
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
