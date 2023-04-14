package garden_invader;

import garden_invader.entityStrategy.Rabbit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RabbitTest {

    int posX;
    int posY;
    int width;
    int height;

    @BeforeEach
    void testSetUp() {
        posX = 50;
        posY = 50;
        width = 100;
        height = 100;
    }


    @Test
    void testCollisionWithOverlap() {
        // Créer un objet avec des dimensions qui chevauchent une autre zone
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui chevauche l'objet
        assertTrue(object.collision(75, 75, 100, 100));
    }

    @Test
    void testCollisionWithNoOverlap() {
        // Créer un objet avec des dimensions qui ne chevauchent pas une autre zone
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui ne chevauche pas l'objet
        assertFalse(object.collision(200, 200, 100, 100));
    }

    @Test
    void testCollisionWithEdgeOverlap() {
        // Créer un objet avec des dimensions qui chevauchent le bord d'une autre zone
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui chevauche le bord de l'objet
        assertTrue(object.collision(49, 50, 100, 100));
    }

    @Test
    void testCollisionWithSameCoordinates() {
        // Créer un objet avec des dimensions qui chevauchent exactement une autre zone
        Rabbit object = new Rabbit(posX, posY, width, height);

        // Tester la collision avec une zone qui chevauche exactement l'objet
        assertTrue(object.collision(50, 50, 100, 100));
    }

}
