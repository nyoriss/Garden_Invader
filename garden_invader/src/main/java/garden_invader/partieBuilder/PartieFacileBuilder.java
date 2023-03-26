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
public class PartieFacileBuilder extends PartieBuilder {

    public PartieBuilder buildNombrePie() {
        nbPies = 30;
        return this;
    }

    public PartieBuilder buildNombreCorbeau() {
        nbCorbeaux = 0;
        return this;
    }

    public PartieBuilder buildNombreMartinPecheur() {
        nbMartinPecheur = 0;
        return this;
    }

}
