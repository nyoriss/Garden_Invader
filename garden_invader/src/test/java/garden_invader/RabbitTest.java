package garden_invader;

import garden_invader.entityStrategy.Rabbit;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RabbitTest {
    @Test
    public void testCollisionWithOverlap() {
        // Créer un objet avec des dimensions qui chevauchent une autre zone
        int posX = 50;
        int posY = 50;
        int width = 100;
        int height = 100;
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui chevauche l'objet
        assertTrue(object.collision(75, 75, 100, 100));
    }

    @Test
    public void testCollisionWithNoOverlap() {
        // Créer un objet avec des dimensions qui ne chevauchent pas une autre zone
        int posX = 50;
        int posY = 50;
        int width = 100;
        int height = 100;
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui ne chevauche pas l'objet
        assertFalse(object.collision(200, 200, 100, 100));
    }

    @Test
    public void testCollisionWithEdgeOverlap() {
        // Créer un objet avec des dimensions qui chevauchent le bord d'une autre zone
        int posX = 50;
        int posY = 50;
        int width = 100;
        int height = 100;
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui chevauche le bord de l'objet
        assertTrue(object.collision(49, 50, 100, 100));
    }

    @Test
    public void testCollisionWithSameCoordinates() {
        // Créer un objet avec des dimensions qui chevauchent exactement une autre zone
        int posX = 50;
        int posY = 50;
        int width = 100;
        int height = 100;
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui chevauche exactement l'objet
        assertTrue(object.collision(50, 50, 100, 100));
    }

}
