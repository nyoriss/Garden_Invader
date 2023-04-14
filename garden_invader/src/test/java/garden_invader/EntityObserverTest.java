package garden_invader;

import garden_invader.entityStrategy.Rabbit;
import garden_invader.projectileObserver.Projectile;
import garden_invader.projectileObserver.CarrotProjectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class EntityObserverTest {

    Entity observer;
    Projectile projectile;

    @BeforeEach
    void testSetUp() {
        observer = new Entity(new Rabbit(0, 0, 48, 48));
        projectile = new CarrotProjectile((Rabbit) observer.getEntity(), 0, 0);
    }

    @Test
    void testActualiser() {
        assertTrue(observer.actualiser(0, 0, 10, 10));
        assertFalse(observer.actualiser(100, 100, 10, 10));
    }

    @Test
    void testHurt() {
        //L'observer n'est pas mort
        assertFalse(observer.hurt(projectile));
    }

    @Test
    void testHurt2() {
        //L'observer n'est pas mort
        assertFalse(observer.hurt(projectile));
        assertFalse(observer.hurt(projectile));
        //L'observer s'est fait toucher 3 fois et n'a plus de PV
        assertTrue(observer.hurt(projectile));
    }
}