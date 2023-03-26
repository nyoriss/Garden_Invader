package garden_invader.partieBuilder;

import garden_invader.Entity;
import garden_invader.GamePanel;
import garden_invader.entiteStrategy.Crow;
import garden_invader.entiteStrategy.kingfisher;
import garden_invader.entiteStrategy.Magpie;

import java.util.ArrayList;

public class GameDifficulty {

    private int magpieNb;
    private int crowNb;
    private int kingfisherNb;
    private int ennemiSpeed;
    private int ennemiDescendSpeed;
    private int playerHealth;
    private ArrayList<Entity> birds;

    public GameDifficulty(int magpieNb, int crowNb, int kingfisherNb, int ennemiSpeed, int ennemiDescendSpeed, int playerHealth) {
        this.magpieNb = magpieNb;
        this.crowNb = crowNb;
        this.kingfisherNb = kingfisherNb;
        this.ennemiSpeed = ennemiSpeed;
        this.ennemiDescendSpeed = ennemiDescendSpeed;
        this.playerHealth = playerHealth;
        birds = new ArrayList<>();
    }

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

    public int getEnnemiSpeed() {
        return ennemiSpeed;
    }

    public int getEnnemiDescendSpeed() {
        return ennemiDescendSpeed;
    }
}
