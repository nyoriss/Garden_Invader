package garden_invader;

import garden_invader.entiteStrategy.Corbeau;
import garden_invader.entiteStrategy.Lapin;
import garden_invader.entiteStrategy.Martin_Pecheur;
import garden_invader.entiteStrategy.Pie;
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
    private int EnnemyRows = 3;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Entite joueur;
    ArrayList<Projectile> projectilesAllies;

    ArrayList<Entite> ennemis;
    ArrayList<Projectile> projectilesEnnemis;

    Partie partie;

    public int tick;
    int deplacementOiseauxTick;
    int vitesseDeplacementOiseaux;
    int vitesseDescenteOiseaux;
    int lastAttackTick;
    boolean winGame;
    boolean looseGame;

    ImageIcon victoire = new ImageIcon("asset/victoire.png");
    ImageIcon defaite = new ImageIcon("asset/defaite.png");

    BufferedImage game;
    {
        try {
            game = ImageIO.read(new File("asset/game.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GamePanel(Partie partie) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        //Entites
        this.joueur = new Entite(new Lapin(100, screenHeight-100, tileSize, tileSize));

        //Projectiles
        projectilesAllies = new ArrayList<>();
        projectilesEnnemis = new ArrayList<>();

        //difficulty setUp
        this.partie = partie;

        //autres mises en place
        tick = 0;
        deplacementOiseauxTick = 0;
        vitesseDeplacementOiseaux = partie.getEnnemiSpeed();
        vitesseDescenteOiseaux = partie.getEnnemiDescendSpeed();
        lastAttackTick = -100;

        //end game variables set
        winGame = false;
        looseGame = false;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        ennemis = createBirds();
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread!= null) {

            // UPDATE
            update();


            // DRAW
            repaint();

            if(looseGame) {
                System.out.println("win outside");

                //on attend quelque temps pour la compréhension
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable (false);
                windowGame.setTitle("Garden Invader");
                windowGame.add(new JLabel(defaite));
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                return;
            }

            if(winGame) {
                System.out.println("loose outside");

                //on attend quelque temps pour la compréhension
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable (false);
                windowGame.setTitle("Garden Invader");
                windowGame.add(new JLabel(victoire));
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
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

        joueur.update(this, keyHandler);


        //gestion des collisions entre oiseaux et projectiles
        for (int i = 0; i < projectilesAllies.size(); i++) {
            Projectile projectile = projectilesAllies.get(i);
            //si le projectile touche
            if (projectile.update(this)) {
                i--; // Décrémenter l'index pour compenser la suppression
            } else {
                //suppression des projectiles hors de l'écran
                if (projectilesAllies.get(i).getPositionY() + projectilesAllies.get(i).getHauteur() <= 0) {
                    projectilesAllies.remove(i);
                    System.out.println("projectile supprimé par sortie d'écran");
                    i--;
                }
            }
        }

        //S'il ne reste plus aucun ennemis
        if(ennemis.size()==0) {
            winGame = true;
            System.out.println("game win inside update");
            return;
        }

        //déplacement des oiseaux
        if (tick - deplacementOiseauxTick >= vitesseDeplacementOiseaux || tick/vitesseDeplacementOiseaux >= 10 * vitesseDeplacementOiseaux) {
            for (int i = 0; i < ennemis.size(); i++) {
                if (tick/vitesseDeplacementOiseaux% 2 == 0) {
                    ennemis.get(i).setPositionY(ennemis.get(i).getPositionY() + 5);
                } else {
                    ennemis.get(i).setPositionY(ennemis.get(i).getPositionY() - 5);
                }
                if(tick/vitesseDeplacementOiseaux % vitesseDescenteOiseaux == 0) {
                    ennemis.get(i).setPositionY(ennemis.get(i).getPositionY() + tileSize/2);
                }
                deplacementOiseauxTick = tick;
            }
        }

        //si un oiseau est descendu trop bas
        for (Entite ennemi: ennemis) {
            if(ennemi.getPositionY() + tileSize >= joueur.getPositionY()) {
                looseGame = true;
                System.out.println("game loosed inside update");
                return;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(game, 0, 0, this);
        //g2.fillRect(joueur.getHitBox().get(0), joueur.getHitBox().get(1), tileSize, tileSize);
        g2.drawImage(lapin,joueur.getHitBox().get(0), joueur.getHitBox().get(1), tileSize, tileSize, null);
        joueur.draw(this, g2);
        for(int i = 0; i < projectilesAllies.size(); i++) {
            //Déplacement des projectiles
            projectilesAllies.get(i).draw(this, g2);
        }

        for (Entite entite: ennemis) {
            entite.draw(this, g2);
        }
        g2.dispose();
    }


    //TODO à replacer ?
    public ArrayList<Entite> createBirds() {
        ArrayList<Entite> birds = partie.getBirds(this);
        int ecart = (screenWidth - (10 * tileSize)) / 11;
        int row = ecart;
        int column = ecart;
        int birdCount = 0;

        for (Entite bird : birds) {
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
        projectilesAllies.add(projectile);
        for (Entite entite: ennemis) {
            projectile.enregistrerObs(entite);
        }
    }

    public void SupprimeDesProjectilesAllies(Projectile projectile) {
        if(projectilesAllies.contains(projectile))
            projectilesAllies.remove(projectile);
    }

    public void SupprimeEntiteDesEnnemis(Entite entite) {
        if(ennemis.contains(entite))
            ennemis.remove(entite);
    }
}
