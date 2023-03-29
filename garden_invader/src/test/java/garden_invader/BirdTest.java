package garden_invader;

import garden_invader.entiteStrategy.Crow;
import garden_invader.entiteStrategy.kingfisher;
import garden_invader.entiteStrategy.Bird;
import garden_invader.entiteStrategy.Magpie;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class BirdTest extends TestCase {
    
    @Test
    public void testMartinPecheur() {
        Bird mp = new kingfisher(10, 20, 30, 40);

        assertEquals(mp.getPositionX(), 10);
        assertEquals(mp.getPositionY(), 20);
        assertEquals(mp.getHeight(), 30);
        assertEquals(mp.getWidth(), 40);
    }
    
    @Test
    public void testCorbeau() {

        Crow crow = new Crow(10, 20, 30, 40);

        assertEquals(crow.getPositionX(), 10);
        assertEquals(crow.getPositionY(), 20);
        assertEquals(crow.getHeight(), 30);
        assertEquals(crow.getWidth(), 40);
    }
    
    @Test
public void testPie() {
    Magpie magpie = new Magpie(10, 20, 30, 40);

    assertEquals(magpie.getPositionX(), 10);
    assertEquals(magpie.getPositionY(), 20);
    assertEquals(magpie.getHeight(), 30);
    assertEquals(magpie.getWidth(), 40);
}
    
    @Test
    public void testCollision() {
        Bird bird1 = new kingfisher(10, 20, 30, 40);
        Bird bird2 = new kingfisher(20, 30, 30, 40);

        boolean collision = bird1.collision(bird2.getPositionX(), bird2.getPositionY(), bird2.getHeight(), bird2.getWidth());

        assertTrue(collision);
    }

}
