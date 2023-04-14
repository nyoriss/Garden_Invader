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
        Bird bird1 = new kingfisher(10, 10, 48, 48);
        Projectile projectile = new CarrotProjectile(playerTest, 10, 15);

        //Il y a une collision entre l'oiseau et le projectile
        boolean collision = bird1.collision(projectile.getPositionX(), projectile.getPositionY(), projectile.getHeight(), projectile.getWidth());

        assertTrue(collision);
    }

    @Test
    void testCollision2() {
        Bird bird1 = new kingfisher(10, 10, 30, 30);
        Projectile projectile = new CarrotProjectile(playerTest, 50, 50);

        //Il n'y a pas de collisions entre l'oiseau et le projectile
        boolean collision = bird1.collision(projectile.getPositionX(), projectile.getPositionY(), projectile.getHeight(), projectile.getWidth());

        assertFalse(collision);
    }


    @Test
    void testBirdProjectileCollisionHurt() {
        Bird bird1 = new kingfisher(50, 40, 48, 48);
        Projectile projectile = new CarrotProjectile(playerTest, 50, 50);

        //L'oiseau n'est pas mort
        assertFalse(bird1.hurt(projectile));

        //Mais ses PV ont diminué
        assertTrue(bird1.currentHP == bird1.maxHP-1);
    }
}
