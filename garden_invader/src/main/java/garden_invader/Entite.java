package garden_invader;

import garden_invader.entiteStrategy.IEntiteStrategy;
import garden_invader.projectileObserver.EntiteObserver;

import java.util.ArrayList;

public class Entite implements EntiteObserver {
    private IEntiteStrategy entite;

    public Entite(IEntiteStrategy entite) {
        this.entite = entite;
    }

    @Override
    public void actualiser(ArrayList<Integer> hitBox) {

    }
}
