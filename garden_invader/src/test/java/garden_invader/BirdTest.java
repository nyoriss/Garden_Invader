package garden_invader;

import garden_invader.entityStrategy.*;

import garden_invader.projectileObserver.CarrotProjectile;
import garden_invader.projectileObserver.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
class BirdTest {

    Rabbit playerTest;

    @BeforeEach
    void testSetUp() {
        playerTest = new Rabbit(50, 50, 48, 48);
    }

    @Test
    void testMartinPecheur() {
        Bird mp = new kingfisher(10, 20, 30, 40);

        assertEquals(mp.getPositionX(), 10);
        assertEquals(mp.getPositionY(), 20);
        assertEquals(mp.getHeight(), 30);
        assertEquals(mp.getWidth(), 40);
    }

    @Test
    void testCorbeau() {

        Crow crow = new Crow(10, 20, 30, 40);

        assertEquals(crow.getPositionX(), 10);
        assertEquals(crow.getPositionY(), 20);
        assertEquals(crow.getHeight(), 30);
        assertEquals(crow.getWidth(), 40);
    }
    
    @Test
    void testPie() {
        Magpie magpie = new Magpie(10, 20, 30, 40);

        assertEquals(magpie.getPositionX(), 10);
        assertEquals(magpie.getPositionY(), 20);
        assertEquals(magpie.getHeight(), 30);
        assertEquals(magpie.getWidth(), 40);
    }
    
    @Test
    void testCollision() {
        Bird bird1 = new kingfisher(10, 20, 30, 40);
        Bird bird2 = new kingfisher(20, 30, 30, 40);

        boolean collision = bird1.collision(bird2.getPositionX(), bird2.getPositionY(), bird2.getHeight(), bird2.getWidth());

        assertTrue(collision);
    }

    @Test
    void testCollision2() {
        Bird bird1 = new kingfisher(10, 10, 30, 30);
        Bird bird2 = new kingfisher(60, 60, 30, 30);

        boolean collision = bird1.collision(bird2.getPositionX(), bird2.getPositionY(), bird2.getHeight(), bird2.getWidth());

        assertFalse(collision);
    }

    @Test
    void testBirdProjectileCollision() {
        Bird bird1 = new kingfisher(50, 40, 48, 48);
        Projectile projectile = new CarrotProjectile(playerTest, 50, 50);

        boolean collision = bird1.collision(projectile.getPositionX(), projectile.getPositionY(), projectile.getHeight(), projectile.getWidth());

        assertFalse(collision);
    }
}
