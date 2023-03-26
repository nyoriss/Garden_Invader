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

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Entity player;
    ArrayList<Projectile> alliedProjectiles;

    ArrayList<Entity> enemies;
    ArrayList<Projectile> ennemyProjectiles;

    GameDifficulty gameDifficulty;

    public int tick;
    int birdMoveTick;
    int birdMoveSpeed;
    int birdDescendSpeed;
    int lastAttackTick;
    boolean winGame;
    boolean loseGame;

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
        alliedProjectiles = new ArrayList<>();
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
        winGame = false;
        loseGame = false;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        enemies = createBirds();
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread!= null) {

            // UPDATE
            update();


            // DRAW
            repaint();

            checkGameEndCondition();

            if(winGame || loseGame) {
                return;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tick ++;
        }
    }

    public void update() {

        player.update(this, keyHandler);


        //gestion des collisions entre oiseaux et projectiles
        for (int i = 0; i < alliedProjectiles.size(); i++) {
            Projectile projectile = alliedProjectiles.get(i);
            //si le projectile touche
            if (projectile.update(this)) {
                i--; // Décrémenter l'index pour compenser la suppression
            } else {
                //suppression des projectiles hors de l'écran
                if (alliedProjectiles.get(i).getPositionY() + alliedProjectiles.get(i).getHeight() <= 0) {
                    alliedProjectiles.remove(i);
                    System.out.println("projectile supprimé par sortie d'écran");
                    i--;
                }
            }
        }



        //déplacement des oiseaux
        if (tick - birdMoveTick >= birdMoveSpeed || tick/ birdMoveSpeed >= 10 * birdMoveSpeed) {
            for (int i = 0; i < enemies.size(); i++) {
                if (tick/ birdMoveSpeed % 2 == 0) {
                    enemies.get(i).setPositionY(enemies.get(i).getPositionY() + 5);
                } else {
                    enemies.get(i).setPositionY(enemies.get(i).getPositionY() - 5);
                }
                if(tick/ birdMoveSpeed % birdDescendSpeed == 0) {
                    enemies.get(i).setPositionY(enemies.get(i).getPositionY() + tileSize/2);
                }
                birdMoveTick = tick;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(gameImage, 0, 0, this);
        //g2.fillRect(joueur.getHitBox().get(0), joueur.getHitBox().get(1), tileSize, tileSize);
        player.draw(this, g2);
        for(int i = 0; i < alliedProjectiles.size(); i++) {
            //Déplacement des projectiles
            alliedProjectiles.get(i).draw(this, g2);
        }

        for (Entity entity : enemies) {
            entity.draw(this, g2);
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

    public void addProjectile(Projectile projectile) {
        alliedProjectiles.add(projectile);
        for (Entity entity : enemies) {
            projectile.enregistrerObs(entity);
        }
    }

    public void SupprimeDesProjectilesAllies(Projectile projectile) {
        if(alliedProjectiles.contains(projectile))
            alliedProjectiles.remove(projectile);
    }

    public void SupprimeEntiteDesEnnemis(Entity entity) {
        if(enemies.contains(entity))
            enemies.remove(entity);
    }


    public void checkGameEndCondition() {
        JFrame windowGame = new JFrame();

        //S'il ne reste plus aucun ennemis
        if(enemies.size()==0) {
            winGame = true;
            windowGame.add(new JLabel(victoryImage));
            System.out.println("game win inside update");
        }

        //si un oiseau est descendu trop bas
        for (Entity ennemi: enemies) {
            if(ennemi.getPositionY() + tileSize >= player.getPositionY()) {
                loseGame = true;
                windowGame.add(new JLabel(defeatImage));

                System.out.println("game loosed inside update");
            }
        }

        if(winGame || loseGame) {
            //on attend quelque temps pour la compréhension
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
}
