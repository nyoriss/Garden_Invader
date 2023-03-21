package garden_invader;

import garden_invader.entiteStrategy.IEntiteStrategy;

public class Entite {
    private IEntiteStrategy entite;

    public Entite(IEntiteStrategy entite) {
        this.entite = entite;
    }
}
