/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garden_invader.partieBuilder;

/**
 *
 * @author louis
 */
public class Director {
    private GameBuilder partie;
    
    public Director(GameBuilder partie) {
        this.partie = partie;
    }
    
    public GameDifficulty ConstructorPartie() {
        return partie.buildMagpieNb()
                .buildCrowNb()
                .buildKingfisherNb()
                .builPlayerHealth(3)
                .buildVitesseDeplacementEnnemi(50).buildGame();
    }
}
