/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garden_invader;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author louis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame windowMenu = new JFrame();
        JButton startButton = new JButton("Lancer partie");
        windowMenu.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        windowMenu.setResizable (false);
        windowMenu.setTitle("Garden Invader");
        GamePanel gamePanel = new GamePanel();

        //Ajouter le bouton
        windowMenu.add(startButton);

        windowMenu.pack();

        windowMenu.setLocationRelativeTo(null);
        windowMenu.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame windowGame = new JFrame();
                windowGame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                windowGame.setResizable (false);
                windowGame.setTitle("Garden Invader");
                windowGame.add(gamePanel);
                windowGame.pack();
                windowGame.setLocationRelativeTo(null);
                windowGame.setVisible(true);
                gamePanel.startGameThread();
            }
        });
    }
    
}