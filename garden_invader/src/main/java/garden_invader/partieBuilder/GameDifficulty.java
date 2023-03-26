package garden_invader.partieBuilder;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.entiteStrategy.Crow;
import garden_invader.entiteStrategy.kingfisher;
import garden_invader.entiteStrategy.Magpie;

import java.util.ArrayList;

public class GameDifficulty {

    private int nbPies;
    private int nbCorbeaux;
    private int nbMartinPecheur;
    private int ennemiSpeed;
    private int ennemiDescendSpeed;
    private int playerHealth;
    private ArrayList<Entity> birds;

    public GameDifficulty(int nbPies, int nbCorbeaux, int nbMartinPecheur, int ennemiSpeed, int ennemiDescendSpeed, int playerHealth) {
        this.nbPies = nbPies;
        this.nbCorbeaux = nbCorbeaux;
        this.nbMartinPecheur = nbMartinPecheur;
        this.ennemiSpeed = ennemiSpeed;
        this.ennemiDescendSpeed = ennemiDescendSpeed;
        this.playerHealth = playerHealth;
        birds = new ArrayList<>();
    }

    public ArrayList<Entity> getBirds(GamePanel gp) {
        System.out.println("pies : "+nbPies+ " corbo : "+nbCorbeaux+ " martin pecheur : "+nbMartinPecheur);
        for(int i = 0; i < nbPies + nbCorbeaux + nbMartinPecheur; i ++) {
            boolean assigned = false;
            if(i < nbMartinPecheur) {
                birds.add(new Entity(new kingfisher(0, 0, gp.tileSize, gp.tileSize)));
                assigned = true;
            }
            if((i < nbMartinPecheur + nbCorbeaux) & !assigned) {
                birds.add(new Entity(new Crow(0, 0, gp.tileSize, gp.tileSize)));
                assigned = true;
            }
            if (!assigned) {
                birds.add(new Entity(new Magpie(0, 0, gp.tileSize, gp.tileSize)));
            }
        }
        return birds;
    }

    public int getEnnemiSpeed() {
        return ennemiSpeed;
    }

    public int getEnnemiDescendSpeed() {
        return ennemiDescendSpeed;
    }
}
