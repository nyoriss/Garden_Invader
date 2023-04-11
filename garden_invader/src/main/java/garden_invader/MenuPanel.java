package garden_invader;

import garden_invader.partieBuilder.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel{

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile final int maxScreenCol = 16;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyHandler = new KeyHandler();
    JButton facileBouton = new JButton(new ImageIcon("asset/Facile.png"));

    JButton moyenBouton = new JButton(new ImageIcon("asset/moyen.png"));

    JButton difficileBouton = new JButton(new ImageIcon("asset/difficile.png"));

    GameDifficulty gameDifficultyBuilder;
    GamePanel gamePanel;

    BufferedImage gameImage;
    {
        try {
            gameImage = ImageIO.read(new File("asset/background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MenuPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        JPanel b2 = new JPanel();
        b2.setPreferredSize(new Dimension(screenWidth, 75));
        JPanel b4 = new JPanel();

        GridLayout gl = new GridLayout(1, 3);
        gl.setHgap(100);
        b2.setLayout(gl);

        b2.add(facileBouton, BorderLayout.CENTER);
        b2.add(moyenBouton);
        b2.add(difficileBouton);

        b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
        b4.add(b2);
        b4.setOpaque(true);
        this.add(b4);
        this.setVisible(true);

        facileBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable(false);
                windowGame.setTitle("Garden Invader");
                gameDifficultyBuilder = new Director(new EasyGameBuilder()).GameConstructor();
                gamePanel = new GamePanel(gameDifficultyBuilder);
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                //faire disparaitre le menu
                setVisible(false);
                //commencer la partie
                gamePanel.startGameThread();
            }
        });

        moyenBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable(false);
                windowGame.setTitle("Garden Invader");
                gameDifficultyBuilder = new Director(new IntermediateGameBuilder()).GameConstructor();
                gamePanel = new GamePanel(gameDifficultyBuilder);
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                //faire disparaitre le menu
                setVisible(false);
                //commencer la partie
                gamePanel.startGameThread();
            }
        });

        difficileBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable(false);
                windowGame.setTitle("Garden Invader");
                gameDifficultyBuilder = new Director(new DifficultGameBuilder()).GameConstructor();
                gamePanel = new GamePanel(gameDifficultyBuilder);
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                //faire disparaitre le menu
                setVisible(false);
                //commencer la partie
                gamePanel.startGameThread();
            }
        });
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(gameImage, 0, 0, this);
    }
}
