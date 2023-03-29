/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garden_invader.partieBuilder;

/**
 *
 * @author louis
 * La classe Director est responsable de la construction de la difficulté du jeu en utilisant le GameBuilder spécifié.
 * Elle possède une méthode qui construit la difficulté du jeu en appelant les méthodes de construction du GameBuilder.
 */
public class Director {
    private GameBuilder partie;

    /**
     * Constructeur de la classe Director. Prend un GameBuilder en paramètre pour construire la difficulté du jeu.
     * @param partie Le GameBuilder spécifié pour construire la difficulté du jeu.
     */
    public Director(GameBuilder partie) {
        this.partie = partie;
    }

    /**
     * Méthode qui construit la difficulté du jeu en appelant les méthodes de construction du GameBuilder spécifié.
     * @return Un objet GameDifficulty représentant la difficulté du jeu construite.
     */
    public GameDifficulty GameConstructor() {
        return partie.buildMagpieNb()
                .buildCrowNb()
                .buildKingfisherNb()
                .builPlayerHealth(3)
                .buildEnemyMovementSpeed(50).buildGame();
    }
}
