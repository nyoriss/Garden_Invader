/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garden_invader;
import javax.swing.*;
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
        windowMenu.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        windowMenu.setResizable (false);
        windowMenu.setTitle("Garden Invader");
        PartiePanel partiePanel = new PartiePanel();

        //Ajouter le bouton
        windowMenu.add(partiePanel);

        windowMenu.pack();

        windowMenu.setLocationRelativeTo(null);
        windowMenu.setVisible(true);
    }
    
}