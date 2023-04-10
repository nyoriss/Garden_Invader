package garden_invader;

import garden_invader.entiteStrategy.Rabbit;
import garden_invader.partieBuilder.*;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile final int maxScreenCol = 16;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    private int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    ArrayList<Entity> players;

    ArrayList<Entity> enemies;
    ArrayList<Projectile> ennemyProjectiles;

    GameDifficulty gameDifficulty;

    public int tick;
    public int birdMoveTick;
    public int birdMoveSpeed;
    public int birdDescendSpeed;
    int lastAttackTick;

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int winState = 3;
    public final int loseState = 4;

    private Random rand = new Random();

    //UI
    private UI ui;

    //Images
    ImageIcon victoryImage;
    ImageIcon defeatImage;
    BufferedImage gameImage;

    public GamePanel(GameDifficulty gameDifficulty) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        ui = new UI(this);
        ui.setGameImages();

        //Entites
        players = new ArrayList<>();
        players.add(new Entity(new Rabbit(100, screenHeight-100, tileSize, tileSize)));

        //Projectiles
        ennemyProjectiles = new ArrayList<>();

        //difficulty setUp
        this.gameDifficulty = gameDifficulty;

        //autres mises en place
        tick = 0;
        birdMoveTick = 0;
        birdMoveSpeed = gameDifficulty.getEnnemiSpeed();
        birdDescendSpeed = gameDifficulty.getEnnemiDescendSpeed();
        lastAttackTick = Integer.MIN_VALUE;

        //end game variables set
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        enemies = createBirds();
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0,01666 secondes soit 60 FPS
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread!= null) {
            // UPDATE
            update();

            // DRAW
            repaint();

            //On vérifie les conditions de fin de jeu
            checkGameEndCondition();

            //On quite le jeu s'il est terminé
            if(gameState == winState || gameState == loseState) {
                return;
            }

            //On attend le temps nécessaire pour garder les 60 FPS
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nextDrawTime += drawInterval;
            tick ++;
        }
    }

    public void update() {

        //Update du joueur
        players.get(0).update(this, keyHandler);

        //Deplacement des oiseaux
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(this, keyHandler);
        }

        //Update des prjectiles ennemis
        for (int i = 0; i < ennemyProjectiles.size(); i++) {
            ennemyProjectiles.get(i).update(this);
        }

        //Si les oiseaux oont bougé
        if (tick - birdMoveTick >= birdMoveSpeed || tick/ birdMoveSpeed >= 10 * birdMoveSpeed) {
            birdMoveTick = tick;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Menu
        if(gameState == titleState) {
            //TODO ui.drawMenu
        } else {
            //Decors
            g2.drawImage(gameImage, 0, 0, this);

            //Joueur
            players.get(0).draw(this, g2);

            //Ennemis
            for (Entity entity : enemies) {
                entity.draw(this, g2);
            }

            //projectiles
            for (Projectile projectile: ennemyProjectiles ) {
                projectile.draw(this, g2);
            }
        }
        ui.draw(g2);
        g2.dispose();
    }

    public ArrayList<Entity> createBirds() {
        ArrayList<Entity> birds = gameDifficulty.getBirds(this);
        int birdsPerRow = 10;
        int ecart = (screenWidth - (birdsPerRow * tileSize)) / (birdsPerRow+1);
        int row = ecart;
        int column = ecart;
        int birdCount = 1;

        //liste pour la réparrtition des attaques entre les oiseaux
        ArrayList<Integer> delays = new ArrayList<>();
        for(int i = 1; i <= birds.size(); i++) {
            delays.add(birds.size()*i);
        }

        //Placement des oiseaux
        for (Entity bird : birds) {
            bird.setPositionX(column);
            bird.setPositionY(row);
            //Répartition des attaques entre les oiseaux
            bird.setNextAttackTick(delays.remove(rand.nextInt(delays.size())));

            if (birdCount % birdsPerRow == 0) {
                //Fin de ligne donc on change de ligne
                row += tileSize + ecart;
                column = ecart;
            } else {
                //Changement de colonne
                column += tileSize + ecart;
            }
            birdCount++;
        }

        return birds;
    }

    public void removeFromEnemies(Entity entity) {
        if(enemies.contains(entity)) {
            //Déplacement de l'oiseau pour ne pas géner
            entity.setPositionX(-100);
            entity.setPositionY(-100);
            enemies.remove(entity);
        }
    }

    public void addToEnnemyProjectiles(Projectile projectile) {
        this.ennemyProjectiles.add(projectile);
        for (Entity entity : getPlayers()) {
            projectile.enregistrerObs(entity);
        }
    }

    public void removeFromEnnemyProjectiles(Projectile projectile) {
        if(ennemyProjectiles.contains(projectile)) {
            this.ennemyProjectiles.remove(projectile);
        }
    }

    public void checkGameEndCondition() {
        JFrame windowGame = new JFrame();

        //S'il ne reste plus aucun ennemis
        if(enemies.size()==0) {
            gameState = winState;
        }

        //si un oiseau est descendu trop bas
        for (Entity ennemi: enemies) {
            if(ennemi.getPositionY() + tileSize >= players.get(0).getPositionY()) {
                gameState = loseState;
            }
        }

        //Points de Vie du joueur à 0
        if(players.get(0).getCurrentHP()<=0) {
            gameState = loseState;
        }

        //Si le joueur a gagné
        if(gameState == winState) {
            //on attend quelque temps pour la comprehension
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //création de la page de victoire
            windowGame.add(new JLabel(victoryImage));
            windowGame.setResizable (false);
            windowGame.setTitle("Garden Invader");
            windowGame.pack();
            windowGame.setLocationRelativeTo(null);
            windowGame.setVisible(true);
            return;
        }

        //Si le joueur a perdu
        if(gameState == loseState) {
            //on attend quelque temps pour la comprehension
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //création de la page de défaite
            windowGame.add(new JLabel(defeatImage));
            windowGame.setResizable (false);
            windowGame.setTitle("Garden Invader");
            windowGame.pack();
            windowGame.setLocationRelativeTo(null);
            windowGame.setVisible(true);
            return;
        }
    }

    public ArrayList<Entity> getEnemies() {
        return enemies;
    }

    public ArrayList<Entity> getPlayers() {
        return players;
    }

    public void setGameImages(ImageIcon victory, ImageIcon defeat, BufferedImage backgroud) {
        this.victoryImage = victory;
        this.defeatImage = defeat;
        this.gameImage = backgroud;
    }

}
