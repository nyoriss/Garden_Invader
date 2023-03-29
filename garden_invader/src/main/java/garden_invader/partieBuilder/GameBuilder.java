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
/**
 * GameBuilder est une classe abstraite pour la construction de parties de jeu. Les classes concrètes
 * qui étendent cette classe abstraite doivent implémenter les méthodes abstraites pour configurer la
 * difficulté du jeu en définissant les nombres de magpies, de crows et de kingfishers, ainsi que
 * les vitesses de déplacement et de descente des ennemis et la santé du joueur. La classe contient
 * également des méthodes utilitaires pour la construction de parties de jeu et la réinitialisation des valeurs
 * par défaut. La classe GameDifficulty est utilisée pour stocker les valeurs configurées par le constructeur de jeu
 * et les fournir à GamePanel pour la création d'une nouvelle partie.
 */
public abstract class GameBuilder {

    int magpieNb = 30;
    int crowNb = 0;
    int kingfisherNb = 0;
    int ennemiSpeed = 50;
    int ennemiDescentSpeed = 9;
    int playerHealth = 3;

    /**
     * Cette méthode abstraite doit être implémentée par les classes concrètes pour définir le nombre de magpies
     * dans la partie de jeu en cours.
     *
     * @return une instance de GameBuilder pour la configuration de la partie de jeu.
     */
    abstract GameBuilder buildMagpieNb();

    /**
     * Cette méthode abstraite doit être implémentée par les classes concrètes pour définir le nombre de crows
     * dans la partie de jeu en cours.
     *
     * @return une instance de GameBuilder pour la configuration de la partie de jeu.
     */
    abstract GameBuilder buildCrowNb();


    /**
     * Cette méthode abstraite doit être implémentée par les classes concrètes pour définir le nombre de kingfishers
     * dans la partie de jeu en cours.
     *
     * @return une instance de GameBuilder pour la configuration de la partie de jeu.
     */
    abstract GameBuilder buildKingfisherNb();

    /**
     * Cette méthode définit la santé du joueur pour la partie en cours.
     *
     * @param health La santé du joueur.
     * @return une instance de GameBuilder pour la configuration de la partie de jeu.
     */
    GameBuilder builPlayerHealth(int health) {
        this.playerHealth = health;
        return this;
    }

    /**
     * Cette méthode définit la vitesse de déplacement des ennemis pour la partie en cours.
     *
     * @param speed La vitesse de déplacement des ennemis.
     * @return une instance de GameBuilder pour la configuration de la partie de jeu.
     */
    GameBuilder buildEnemyMovementSpeed(int speed) {
        this.ennemiSpeed = speed;
        return this;
    }

    /**
     * Cette méthode définit la vitesse de descente des ennemis pour la partie en cours.
     *
     * @param speed La vitesse de descente des ennemis.
     * @return une instance de GameBuilder pour la configuration de la partie de jeu.
     */
    GameBuilder buildEnemyDescendSpeed(int speed) {
        this.ennemiDescentSpeed = speed;
        return this;
    }

    /**
     * Crée une instance de GameDifficulty à partir des paramètres de construction actuels.
     * @return l'instance de GameDifficulty créée avec les paramètres actuels.
     */
    GameDifficulty buildGame() {
        return new GameDifficulty(magpieNb, crowNb, kingfisherNb, ennemiSpeed, ennemiDescentSpeed, playerHealth);
    }

    /**
     * Réinitialise les paramètres de construction aux valeurs par défaut :
     * magpieNb = 30, crowNb = 0, kingfisherNb = 0, ennemiSpeed = 50, ennemiDescentSpeed = 10.
     */
    void reset() {
        magpieNb = 30;
        crowNb = 0;
        kingfisherNb = 0;
        ennemiSpeed = 50;
        ennemiDescentSpeed = 10;
    }

}
