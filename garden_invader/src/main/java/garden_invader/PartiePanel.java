package garden_invader;

import garden_invader.partieBuilder.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PartiePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile final int maxScreenCol = 16;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyHandler = new KeyHandler();
    JButton facileBouton = new JButton("Facile");

    JButton moyenBouton = new JButton("Moyen");

    JButton difficileBouton = new JButton("Difficile");

    Partie partieBuilder;
    GamePanel gamePanel;

    ImageIcon icon = new ImageIcon("asset/background.png");

    public PartiePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        JPanel b1 = new JPanel();
        b1.setPreferredSize(new Dimension(screenWidth, 250));
        b1.add(new JLabel(icon));
        b1.setBackground(Color.black);
        JPanel b2 = new JPanel();
        b2.setBackground(new java.awt.Color(34, 177, 76));
        b2.setPreferredSize(new Dimension(screenWidth, 75));
        JPanel b3 = new JPanel();
        b3.setBackground(new java.awt.Color(34, 177, 76));
        b3.setPreferredSize(new Dimension(screenWidth, 250));
        JPanel b4 = new JPanel();

        GridLayout gl =new GridLayout(1, 3);
        gl.setHgap(100);
        b2.setLayout(gl);

        b2.add(facileBouton, BorderLayout.CENTER);

        b2.add(moyenBouton);

        b2.add(difficileBouton);

        b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
        b4.add(b1);
        b4.add(b2);
        b4.add(b3);
        this.add(b4);
        this.setVisible(true);

        facileBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable (false);
                windowGame.setTitle("Garden Invader");
                partieBuilder = new Director(new PartieFacileBuilder()).ConstructorPartie();
                gamePanel = new GamePanel(partieBuilder);
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                gamePanel.startGameThread();
            }
        });

        moyenBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable (false);
                windowGame.setTitle("Garden Invader");
                partieBuilder = new Director(new PartieIntermediaireBuilder()).ConstructorPartie();
                gamePanel = new GamePanel(partieBuilder);
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                gamePanel.startGameThread();
            }
        });

        difficileBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable (false);
                windowGame.setTitle("Garden Invader");
                partieBuilder = new Director(new PartieDifficileBuilder()).ConstructorPartie();
                gamePanel = new GamePanel(partieBuilder);
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                gamePanel.startGameThread();
            }
        });
    }
    @Override
    public void run() {

    }
}
