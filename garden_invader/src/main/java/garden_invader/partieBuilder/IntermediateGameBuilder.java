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
public class IntermediateGameBuilder extends GameBuilder {

    public GameBuilder buildMagpieNb() {
        magpieNb = 20;
        return this;
    }

    public GameBuilder buildCrowNb() {
        crowNb = 10;
        return this;
    }

    public GameBuilder buildKingfisherNb() {
        kingfisherNb = 0;
        return this;
    }

}
