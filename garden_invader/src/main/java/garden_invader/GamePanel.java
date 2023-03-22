package garden_invader;

import garden_invader.entiteStrategy.Lapin;
import garden_invader.entiteStrategy.Pie;
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
        ennemis = new ArrayList<>();
        for(int i = 20; i<=(screenWidth-20-tileSize);i += tileSize+20) {
            ennemis.add(new Entite(new Pie(i, 10, tileSize, tileSize))); //TODO les créer dans une liste
        }
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
            if (projectile.notifierObs()) {
                projectilesAllies.remove(i);
                System.out.println("projectile supprimé");
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
                i --;
            }
        }

        for (Entite entite: ennemis) {
            g2.setColor(Color.red);
            g2.fillRect(entite.getPositionX(), entite.getPositionY(), entite.getLargeur(), entite.getHauteur());
            g2.setColor(Color.white);
        }
        //System.out.println("position du joueur X : "+joueur.getHitBox().get(0)+" Y :"+ joueur.getHitBox().get(1));
        //System.out.println(ennemis.size());
        g2.dispose();
    }
}
