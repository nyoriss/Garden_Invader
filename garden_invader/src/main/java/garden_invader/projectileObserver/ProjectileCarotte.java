package garden_invader.projectileObserver;

import java.util.ArrayList;

public class ProjectileCarotte implements Projectile{
    private EntiteObserver entiteObs;

    private int positionX;
    private int positionY;
    private int largeur;
    private int hauteur;

    @Override
    public void enregistrerObs(EntiteObserver observer) {

    }

    @Override
    public void supprimerObs(EntiteObserver observer) {

    }

    @Override
    public void notifierObs() {

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
