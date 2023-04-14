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
public class EasyGameBuilder extends GameBuilder {

    @Override
    public GameBuilder buildMagpieNb() {
        magpieNb = 30;
        return this;
    }

    @Override
    public GameBuilder buildCrowNb() {
        crowNb = 0;
        return this;
    }

    @Override
    public GameBuilder buildKingfisherNb() {
        kingfisherNb = 0;
        return this;
    }
}
