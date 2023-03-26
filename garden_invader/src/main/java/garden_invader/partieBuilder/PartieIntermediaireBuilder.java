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
public class PartieIntermediaireBuilder extends PartieBuilder {

    public PartieBuilder buildNombrePie() {
        nbPies = 20;
        return this;
    }

    public PartieBuilder buildNombreCorbeau() {
        nbCorbeaux = 10;
        return this;
    }

    public PartieBuilder buildNombreMartinPecheur() {
        nbMartinPecheur = 0;
        return this;
    }

}
