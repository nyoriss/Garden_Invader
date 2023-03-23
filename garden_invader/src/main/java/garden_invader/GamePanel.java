package garden_invader;

import garden_invader.entiteStrategy.Corbeau;
import garden_invader.entiteStrategy.Lapin;
import garden_invader.entiteStrategy.Martin_Pecheur;
import garden_invader.entiteStrategy.Pie;
import garden_invader.partieBuilder.PartieBuilder;
import garden_invader.partieBuilder.PartieDifficileBuilder;
import garden_invader.partieBuilder.PartieFacileBuilder;
import garden_invader.partieBuilder.PartieIntermediaireBuilder;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.ProjectileCarotte;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile final int maxScreenCol = 16;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Entite joueur;
    ArrayList<Projectile> projectilesAllies;

    ArrayList<Entite> ennemis;
    ArrayList<Projectile> projectilesEnnemis;

    PartieBuilder difficultePartie;

    int playerSpeed = 4;
    int tick;
    int lastAttackTick;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        //Entites
        this.joueur = new Entite(new Lapin(100, screenHeight-100, tileSize, tileSize));

        //Projectiles
        projectilesAllies = new ArrayList<>();
        projectilesEnnemis = new ArrayList<>();

        //Difficulté de la partie
        difficultePartie = new PartieDifficileBuilder();

        //Créer les ennemis
        ennemis = createOiseaux();

        //autres mises en place
        tick = 0;
        lastAttackTick = -100;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread!= null) {

            // UPDATE
            update();

            // DRAW
            repaint();


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tick ++;
        }
    }

    public void update() {
        if(keyHandler.leftPressed) {
            if(joueur.getPositionX() - 8 * playerSpeed >= 0)
                joueur.setPositionX(joueur.getPositionX() - playerSpeed);
        } else {
            if (keyHandler.rightPressed) {
                if(joueur.getPositionX()+joueur.getLargeur() + 8 * playerSpeed<screenWidth)
                    joueur.setPositionX(joueur.getPositionX() + playerSpeed);
            }
        }

        if(keyHandler.spacePressed && tick-lastAttackTick >=35) {
            Projectile projectile = new ProjectileCarotte(joueur, joueur.getPositionX(), joueur.getPositionY() - joueur.getHauteur());
            projectilesAllies.add(projectile);
            for (Entite entite: ennemis) {
                projectile.enregistrerObs(entite);
            }
            lastAttackTick = tick;
        }

        for (int i = 0; i < projectilesAllies.size(); i++) {
            Projectile projectile = projectilesAllies.get(i);
            projectile.setPositionY(projectile.getPositionY() - projectile.getSpeed());
            Entite entite = projectile.notifierObs();
            if (entite!=null) {
                if(entite.blesse(projectile)) { //TODO pourquoi le projectile disparait avant de toucher après une destruction d'oiseau
                    ennemis.remove(entite);
                }
                projectilesAllies.remove(i);
                System.out.println("projectile supprimé par hitBox");
                i--; // Décrémenter l'index pour compenser la suppression
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(joueur.getHitBox().get(0), joueur.getHitBox().get(1), tileSize, tileSize);
        for(int i = 0; i < projectilesAllies.size(); i++) {
            //Déplacement des projectiles
            g2.fillRect(projectilesAllies.get(i).getPositionX(), projectilesAllies.get(i).getPositionY(), projectilesAllies.get(i).getLargeur()*3, projectilesAllies.get(i).getHauteur()*3);

            //suppression des projectiles hors de l'écran //TODO déplacer ?
            if(projectilesAllies.get(i).getPositionY()+ projectilesAllies.get(i).getHauteur()<=0) {
                projectilesAllies.remove(i);
                System.out.println("projectile supprimé par sortie d'écran");
                i --;
            }
        }

        for (Entite entite: ennemis) {
            g2.setColor(entite.getCouleur());
            g2.fillRect(entite.getPositionX(), entite.getPositionY(), entite.getLargeur(), entite.getHauteur());
            g2.setColor(Color.white);
        }
        //System.out.println("position du joueur X : "+joueur.getHitBox().get(0)+" Y :"+ joueur.getHitBox().get(1));
        //System.out.println(ennemis.size());
        g2.dispose();
    }


    //TODO à placer
    public ArrayList<Entite> createOiseaux() {
        ArrayList<Entite> oiseaux = new ArrayList<>();
        for(int i = 20; i<=(screenWidth-20-tileSize);i += tileSize+20) {
            for( int j = 1; j <= 3; j++) {
                switch(j) {
                    case 1:
                        if(difficultePartie.getResult() instanceof PartieDifficileBuilder) {
                            System.out.println("martin pecheur");
                            oiseaux.add(new Entite(new Martin_Pecheur(i, 10*j + (j-1)*tileSize, tileSize, tileSize)));
                        } else if(difficultePartie.getResult() instanceof PartieIntermediaireBuilder) {
                            System.out.println("corbeau");
                            oiseaux.add(new Entite(new Corbeau(i, 10*j + (j-1)*tileSize, tileSize, tileSize)));
                        } else {
                            System.out.println("Pie");
                            oiseaux.add(new Entite(new Pie(i, 10 * j + (j - 1) * tileSize, tileSize, tileSize)));
                        }
                        break;
                    case 2:
                        if(difficultePartie.getResult() instanceof PartieDifficileBuilder) {
                            System.out.println("corbeau");
                            oiseaux.add(new Entite(new Corbeau(i, 10*j + (j-1)*tileSize, tileSize, tileSize)));
                        } else {
                            System.out.println("Pie");
                            oiseaux.add(new Entite(new Pie(i, 10 * j + (j - 1) * tileSize, tileSize, tileSize)));
                        }
                        break;
                    case 3:
                        System.out.println("Pie");
                        oiseaux.add(new Entite(new Pie(i, 10 * j + (j - 1) * tileSize, tileSize, tileSize)));
                        break;
                    default:
                        System.out.println("problème");
                }

                System.out.println(difficultePartie.getResult() instanceof PartieDifficileBuilder);


            }
        }
        return oiseaux;
    }
}
