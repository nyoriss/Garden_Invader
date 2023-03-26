package garden_invader.partieBuilder;

import garden_invader.Entite;
import garden_invader.GamePanel;
import garden_invader.Main;
import garden_invader.entiteStrategy.Corbeau;
import garden_invader.entiteStrategy.IEntiteStrategy;
import garden_invader.entiteStrategy.Martin_Pecheur;
import garden_invader.entiteStrategy.Pie;

import java.util.ArrayList;

public class Partie {

    private int nbPies;
    private int nbCorbeaux;
    private int nbMartinPecheur;
    private int ennemiSpeedBooost;
    private ArrayList<Entite> birds;

    public Partie(int nbPies, int nbCorbeaux, int nbMartinPecheur, int ennemiSpeedBooost) {
        this.nbPies = nbPies;
        this.nbCorbeaux = nbCorbeaux;
        this.nbMartinPecheur = nbMartinPecheur;
        this.ennemiSpeedBooost = ennemiSpeedBooost;
        birds = new ArrayList<>();
    }

    public ArrayList<Entite> getBirds(GamePanel gp) {
        System.out.println("pies : "+nbPies+ " corbo : "+nbCorbeaux+ " martin pecheur : "+nbMartinPecheur);
        for(int i = 0; i < nbPies + nbCorbeaux + nbMartinPecheur; i ++) {
            boolean assigned = false;
            if(i < nbMartinPecheur) {
                birds.add(new Entite(new Martin_Pecheur(0, 0, gp.tileSize, gp.tileSize)));
                assigned = true;
            }
            if((i < nbMartinPecheur + nbCorbeaux) & !assigned) {
                birds.add(new Entite(new Corbeau(0, 0, gp.tileSize, gp.tileSize)));
                assigned = true;
            }
            if (!assigned) {
                birds.add(new Entite(new Pie(0, 0, gp.tileSize, gp.tileSize)));
            }
        }
        return birds;
    }

}
