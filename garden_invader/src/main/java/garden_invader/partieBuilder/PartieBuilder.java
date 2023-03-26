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
public abstract class PartieBuilder {

    int nbPies = 33;
    int nbCorbeaux = 0;
    int nbMartinPecheur = 0;
    int ennemiSpeed = 50;
    int ennemiDescentSpeed = 10;
    int health = 3;

    abstract PartieBuilder buildNombrePie();
    abstract PartieBuilder buildNombreCorbeau();
    abstract PartieBuilder buildNombreMartinPecheur();


    PartieBuilder builPlayerHealth(int health) {
        this.health = health;
        return this;
    }


    PartieBuilder buildVitesseDeplacementEnnemi(int speed) {
        this.ennemiSpeed = speed;
        return this;
    }

    PartieBuilder buildVitesseDescenteEnnemi(int speed) {
        this.ennemiDescentSpeed = speed;
        return this;
    }

    Partie buildGame() {
        return new Partie(nbPies, nbCorbeaux, nbMartinPecheur, ennemiSpeed);
    }

    void reset() {
        nbPies = 33;
        nbCorbeaux = 0;
        nbMartinPecheur = 0;
        ennemiSpeed = 50;
        ennemiDescentSpeed = 10;
    }

}
