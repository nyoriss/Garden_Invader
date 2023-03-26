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
    private PartieBuilder partie;
    
    public Director(PartieBuilder partie) {
        this.partie = partie;
    }
    
    public Partie ConstructorPartie() {
        return partie.buildNombrePie()
                .buildNombreCorbeau()
                .buildNombreMartinPecheur()
                .builPlayerHealth(3)
                .buildVitesseDeplacementEnnemi(50).buildGame();
    }
}
