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
public abstract class GameBuilder {

    int magpieNb = 30;
    int crowNb = 0;
    int kingfisherNb = 0;
    int ennemiSpeed = 50;
    int ennemiDescentSpeed = 9;
    int playerHealth = 3;

    abstract GameBuilder buildMagpieNb();
    abstract GameBuilder buildCrowNb();
    abstract GameBuilder buildKingfisherNb();


    GameBuilder builPlayerHealth(int health) {
        this.playerHealth = health;
        return this;
    }


    GameBuilder buildVitesseDeplacementEnnemi(int speed) {
        this.ennemiSpeed = speed;
        return this;
    }

    GameBuilder buildVitesseDescenteEnnemi(int speed) {
        this.ennemiDescentSpeed = speed;
        return this;
    }

    GameDifficulty buildGame() {
        return new GameDifficulty(magpieNb, crowNb, kingfisherNb, ennemiSpeed, ennemiDescentSpeed, playerHealth);
    }

    void reset() {
        magpieNb = 30;
        crowNb = 0;
        kingfisherNb = 0;
        ennemiSpeed = 50;
        ennemiDescentSpeed = 10;
    }

}
