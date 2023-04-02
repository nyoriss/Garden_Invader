# Garden_Invader

| Auteur 1                         | Auteur 2                          |
|----------------------------------|-----------------------------------|
| Noah SIGOIGNE                    | Louis GODFROI                     |
| Groupe 1                         | Groupe 1                          |
| noah.sigoigne@etu.univ-nantes.fr | louis.godfroi@etu.univ-nantes.fr  |


# Installation
JDK : corretto-15<br>
JUnit : junit 5

# Présentation du jeu
Garden Invader reprend le principe de l'inévitable shoot 'em up "Space Invader" et vous laisse incarner Camille, un lapin prêt à tout pour défendre son jardin des oiseaux sauvages ! <br>
Il s'arme donc de ses carottes pour les envoyer sur ces vils volatiles

### Contrôles :
- Q et D : se déplacer latéralement
- Barre espace : tirer des projectiles en ligne droite

### But du jeu :
- Éliminer ces parasites volants <br>
- Ne les laissez pas atteindre vos cultures !

## Lancer le jeu
- Sous linux : <br>
Dans le repertoire Garden_invader/garden_invader, réalisez dans un terminal : 
```bash
./run.bin
```

- Sous windows : <br>
Bon courage<br>

# Design Patterns
Nous avons choisi d'utiliser 3 design patterns : 
- Strategy
- Observer
- Builder

### Entity'Strategy'
Pour la gestion des entités (ennemis ou joueur), nous avons implémenté un design pattern 'Strategy' afin de pouvoir définir des méthodes à implémenter pour chaque type d'entité.<br>
La classe "Bird" permet de factoriser le code des oiseaux tout en permettant d'en avoir de plusieurs types grâce à l'héritage.<br>
![Image](Design%20Patterns/EntityStrategy.png)<br>

### Entity'Observer'
Pour la gestion des collisions entre projectiles et entités, nous avons utilisé le pattern 'Observer', qui va permettre à une entité de s'abonner aux projectiles de l'autre faction et pouvoir être notifié de leur position à chaque déplacement afin de détecter les collisions.<br>
![Image](Design%20Patterns/EntityObserver.png)<br>

### Game'Builder'
La gestion des différentes difficultés ont été gérées avec un builder, ce qui va permettre de créer plusieurs types de parties avec plusieurs options et attributs, dont certains optionnels.<br>
![Image](Design%20Patterns/EntityObserver.png)<br>

### Diagramme de classe complet
![Image](Design%20Patterns/Design_patterns.png)
