import ddf.minim.Minim;
import org.bcit.com2522.project.enemy.Sporadic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SporadicTest {

    /**
     * Tests the move function
     */
    @Test
    public void testMove() {
        // Create a Sporadic enemy
        Sporadic sporadic = new Sporadic(new PVector(0, 0), new PVector(1, 0), 1, 1, Color.BLACK, "");

        // Move the enemy
        sporadic.move();

        // Assert that the enemy's position has changed
        assertNotEquals(new PVector(0, 0), sporadic.getPosition());
    }


    /**
     * Tests the update function
     */
    @Test
    public void testUpdate() {
        // Create a Sporadic enemy
        Sporadic sporadic = new Sporadic(new PVector(0, 0), new PVector(1, 0), 1, 1, Color.BLACK, "");

        // Update the enemy
        sporadic.update();

        // Assert that the enemy's position has changed
        assertNotEquals(new PVector(0, 0), sporadic.getPosition());
    }

    private Sporadic sporadic;


    /**
     * Makes a new sporadic before each of the following tests
     */
    @BeforeEach
    void setUp() {
        PVector position = new PVector(0, 0);
        PVector direction = new PVector(1, 0);
        float size = 1;
        float speed = 1;
        Color color = Color.BLACK;
        String imagePath = "Data/sporadicSleep.png";
        sporadic = new Sporadic(position, direction, size, speed, color, imagePath);
    }

    /**
     * Tests the getter for image
     */
    @Test
    void testGetImage() {
        PImage expectedImage = sporadic.getImage();
        assertEquals(expectedImage, sporadic.getImage());
    }

    /**
     * Tests the setter for image
     */
    @Test
    void testSetImage() {
        PImage newImage = new PImage(10, 10);
        sporadic.setImage(newImage);
        assertEquals(newImage, sporadic.getImage());
    }

    /**
     * Tests the getter for Minim
     */
    @Test
    void testGetMinim() {
        Minim expectedMinim = sporadic.getMinim();
        assertEquals(expectedMinim, sporadic.getMinim());
    }


    /**
     * Tests the getter for sound
     */
    @Test
    void testGetSound() {
        assertNotNull(sporadic.getSound());
    }



}

