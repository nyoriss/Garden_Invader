package garden_invader;

import garden_invader.entiteStrategy.Rabbit;
import garden_invader.partieBuilder.*;
import garden_invader.projectileObserver.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    Entity player;

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


    ImageIcon victoryImage = new ImageIcon("asset/victoire.png");
    ImageIcon defeatImage = new ImageIcon("asset/defaite.png");

    BufferedImage gameImage;
    {
        try {
            gameImage = ImageIO.read(new File("asset/game.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GamePanel(GameDifficulty gameDifficulty) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        //Entites
        this.player = new Entity(new Rabbit(100, screenHeight-100, tileSize, tileSize));

        //Projectiles
        ennemyProjectiles = new ArrayList<>();

        //difficulty setUp
        this.gameDifficulty = gameDifficulty;

        //autres mises en place
        tick = 0;
        birdMoveTick = 0;
        birdMoveSpeed = gameDifficulty.getEnnemiSpeed();
        birdDescendSpeed = gameDifficulty.getEnnemiDescendSpeed();
        lastAttackTick = -100;

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

        double drawInterval = 1000000000/FPS; //0,01666 secondes
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread!= null) {
            // UPDATE
            update();


            // DRAW
            repaint();

            checkGameEndCondition();

            if(gameState == winState || gameState == loseState) {
                return;
            }

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

        player.update(this, keyHandler);

        //deplacement des oiseaux
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(this, keyHandler);
        }
        if (tick - birdMoveTick >= birdMoveSpeed || tick/ birdMoveSpeed >= 10 * birdMoveSpeed) {
            birdMoveTick = tick;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Menu
        if(gameState == titleState) {
            //TODO tuto 17
        } else {
            // Decors
            g2.drawImage(gameImage, 0, 0, this);

            //Joueur
            player.draw(this, g2);

            //Ennemis
            for (Entity entity : enemies) {
                entity.draw(this, g2);
            }
        }

        g2.dispose();
    }

    public ArrayList<Entity> createBirds() {
        ArrayList<Entity> birds = gameDifficulty.getBirds(this);
        int ecart = (screenWidth - (10 * tileSize)) / 11;
        int row = ecart;
        int column = ecart;
        int birdCount = 0;

        for (Entity bird : birds) {
            bird.setPositionX(column);
            bird.setPositionY(row);

            birdCount++;
            if (birdCount % 10 == 0) {
                // Reached the end of a row, move to the next row
                row += tileSize + ecart;
                column = ecart;
            } else {
                // Move to the next column
                column += tileSize + ecart;
            }
        }

        return birds;
    }

    public void removeFromEnnemyProjectiles(Entity entity) {
        if(enemies.contains(entity))
            enemies.remove(entity);
    }


    public void checkGameEndCondition() {
        JFrame windowGame = new JFrame();

        //S'il ne reste plus aucun ennemis
        if(enemies.size()==0) {
            gameState = winState;
            windowGame.add(new JLabel(victoryImage));
            System.out.println("game win inside update");
        }

        //si un oiseau est descendu trop bas
        for (Entity ennemi: enemies) {
            if(ennemi.getPositionY() + tileSize >= player.getPositionY()) {
                gameState = loseState;
                windowGame.add(new JLabel(defeatImage));

                System.out.println("game loosed inside update");
            }
        }

        if(gameState == winState || gameState == loseState) {
            //on attend quelque temps pour la comprehension
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
}
