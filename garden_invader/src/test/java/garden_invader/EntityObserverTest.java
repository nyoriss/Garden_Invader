package garden_invader;
import garden_invader.entiteStrategy.Rabbit;
import garden_invader.projectileObserver.EntityObserver;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.CarrotProjectile;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntityObserverTest {

    EntityObserver observer;
    Projectile projectile;

    @Before
    public void setUp() {
        observer = (EntityObserver) new Rabbit(0, 0, 0, 0);
        projectile = new CarrotProjectile((Rabbit) observer, 0, 0);
    }

    @Test
    public void testActualiser() {
        assertTrue(observer.actualiser(0, 0, 10, 10));
        assertFalse(observer.actualiser(20, 20, 10, 10));
    }

    @Test
    public void testHurt() {
        assertTrue(observer.hurt(projectile));
    }
}