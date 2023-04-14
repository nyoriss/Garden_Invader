/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garden_invader.gameBuilder;

/**
 *
 * @author louis
 */
public class DifficultGameBuilder extends GameBuilder {

    @Override
    public GameBuilder buildMagpieNb() {
        magpieNb = 10;
        return this;
    }

    @Override
    public GameBuilder buildCrowNb() {
        crowNb = 10;
        return this;
    }

    @Override
    public GameBuilder buildKingfisherNb() {
        kingfisherNb = 10;
        return this;
    }
}
