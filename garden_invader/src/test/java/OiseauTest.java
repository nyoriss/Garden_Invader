
import garden_invader.entiteStrategy.Corbeau;
import garden_invader.entiteStrategy.Martin_Pecheur;
import garden_invader.entiteStrategy.Oiseau;
import garden_invader.entiteStrategy.Pie;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.ProjectileCarotte;
import java.awt.Color;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class OiseauTest extends TestCase {
    
    @Test
    public void testMartinPecheur() {
        Oiseau mp = new Martin_Pecheur(10, 20, 30, 40);

        assertEquals(mp.getCouleur(), Color.blue);
        assertEquals(mp.getPositionX(), 10);
        assertEquals(mp.getPositionY(), 20);
        assertEquals(mp.getLargeur(), 30);
        assertEquals(mp.getHauteur(), 40);
    }
    
    @Test
    public void testCorbeauConstructor() {

        Corbeau corbeau = new Corbeau(10, 20, 30, 40);

        assertEquals(corbeau.getPositionX(), 10);
        assertEquals(corbeau.getPositionY(), 20);
        assertEquals(corbeau.getLargeur(), 30);
        assertEquals(corbeau.getHauteur(), 40);
        assertEquals(corbeau.getCouleur(), Color.gray);
    }
    
    @Test
public void testPieConstructeur() {
    Pie pie = new Pie(10, 20, 30, 40);

    assertEquals(pie.getPositionX(), 10);
    assertEquals(pie.getPositionY(), 20);
    assertEquals(pie.getLargeur(), 30);
    assertEquals(pie.getHauteur(), 40);
    assertEquals(Color.red, pie.getCouleur());
}
    
    @Test
    public void testCollision() {
        Oiseau oiseau1 = new Martin_Pecheur(10, 20, 30, 40);
        Oiseau oiseau2 = new Martin_Pecheur(20, 30, 30, 40);

        boolean collision = oiseau1.collision(oiseau2.getPositionX(), oiseau2.getPositionY(), oiseau2.getLargeur(), oiseau2.getHauteur());

        assertTrue(collision);
    }
    
    /*@Test
    public void testOiseauBlesse() {
        Oiseau oiseau = new Corbeau(0, 0, 50, 50);
        Projectile projectile = new ProjectileCarotte(0, 0, 10, 10);

        assertFalse(oiseau.blesse(projectile)); // L'oiseau ne devrait pas être mort

        oiseau.blesse(projectile);
        assertTrue(oiseau.blesse(projectile)); // L'oiseau devrait être mort
    }*/
}
