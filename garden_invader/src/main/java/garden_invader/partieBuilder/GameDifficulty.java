package garden_invader.partieBuilder;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.entiteStrategy.Crow;
import garden_invader.entiteStrategy.kingfisher;
import garden_invader.entiteStrategy.Magpie;

import java.util.ArrayList;

/**
 * Classe représentant la difficulté d'une partie.
 */
public class GameDifficulty {

    private int magpieNb;
    private int crowNb;
    private int kingfisherNb;
    private int ennemiSpeed;
    private int ennemiDescendSpeed;
    private int playerHealth;
    private ArrayList<Entity> birds;

    /**
     * Constructeur de la classe GameDifficulty.
     * @param magpieNb le nombre de pies
     * @param crowNb le nombre de corbeaux
     * @param kingfisherNb le nombre de martin-pêcheurs
     * @param ennemiSpeed la vitesse des ennemis
     * @param ennemiDescendSpeed la vitesse de descente des ennemis
     * @param playerHealth la santé du joueur
     */
    public GameDifficulty(int magpieNb, int crowNb, int kingfisherNb, int ennemiSpeed, int ennemiDescendSpeed, int playerHealth) {
        this.magpieNb = magpieNb;
        this.crowNb = crowNb;
        this.kingfisherNb = kingfisherNb;
        this.ennemiSpeed = ennemiSpeed;
        this.ennemiDescendSpeed = ennemiDescendSpeed;
        this.playerHealth = playerHealth;
        birds = new ArrayList<>();
    }

    /**
     * Renvoie la liste des oiseaux en fonction de la difficulté.
     * @param gp le GamePanel
     * @return la liste des oiseaux
     */
    public ArrayList<Entity> getBirds(GamePanel gp) {
        for(int i = 0; i < magpieNb + crowNb + kingfisherNb; i ++) {
            boolean assigned = false;
            if(i < kingfisherNb) {
                birds.add(new Entity(new kingfisher(0, 0, gp.tileSize, gp.tileSize)));
                assigned = true;
            }
            if((i < kingfisherNb + crowNb) & !assigned) {
                birds.add(new Entity(new Crow(0, 0, gp.tileSize, gp.tileSize)));
                assigned = true;
            }
            if (!assigned) {
                birds.add(new Entity(new Magpie(0, 0, gp.tileSize, gp.tileSize)));
            }
        }
        return birds;
    }

    /**
     * Renvoie la vitesse des ennemis.
     * @return la vitesse des ennemis
     */
    public int getEnnemiSpeed() {
        return ennemiSpeed;
    }

    /**
     * Renvoie la vitesse de descente des ennemis.
     * @return la vitesse de descente des ennemis
     */
    public int getEnnemiDescendSpeed() {
        return ennemiDescendSpeed;
    }
}
