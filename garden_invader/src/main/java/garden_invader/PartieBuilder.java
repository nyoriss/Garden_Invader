/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garden_invader;

/**
 *
 * @author louis
 */
public interface PartieBuilder {
    void reset(); 
    
    void buildNombreVieJoueur(); 
    
    void buildVitesseDeplacementEnnemi();
    
    void buildNombrePie();
}
