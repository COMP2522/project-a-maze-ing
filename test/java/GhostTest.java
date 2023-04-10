import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.enemy.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;



public class GhostTest {

    private Ghost ghost;

    /**
     * Constructs a new ghost before each test
     */
    @BeforeEach
    public void setUp() {
        PVector position = new PVector(0, 0);
        PVector direction = new PVector(0, 0);
        float size = 1;
        float speed = 2;
        Color color = Color.WHITE;
        String imagePath = "Data/ghost.png";
        ghost = Ghost.getInstance(position, direction, size, speed, color, imagePath);
    }

    /**
     *Tests the getInstance method
     */
    @Test
    public void testGetInstance() {
        Ghost instance1 = Ghost.getInstance(null, null, 0, 0, null, null);
        Ghost instance2 = Ghost.getInstance(null, null, 0, 0, null, null);
        assertSame(instance1, instance2);
    }

    /**
     * Tests the move method
     */
    @Test
    public void testMove() {
        // Set up player
        PVector playerPosition = new PVector(50, 50);
        Player.getInstance().setPosition(playerPosition);

        // Set up ghost
        PVector ghostPosition = new PVector(0, 0);
        ghost.setPosition(ghostPosition);
        ghost.move();

        // Check if ghost moved towards player
        PVector expectedPosition = new PVector(ghost.getGhostSpeed(), ghost.getGhostSpeed());
        PVector actualPosition = PVector.sub(ghost.getPosition(), playerPosition);
        assertTrue(actualPosition.x <= expectedPosition.x);
        assertTrue(actualPosition.y <= expectedPosition.y);
    }

    /**
     * Tests the becomeHyper method
     */
    @Test
    public void testBecomeHyper() {
        int originalSpeed = ghost.getGhostSpeed();
        Ghost.becomeHyper();
        assertTrue(Ghost.getIsHyper());
        assertEquals(8, Ghost.getGhostSpeed());
        Ghost.setGhostSpeed(originalSpeed);
        Ghost.setIsHyper(false);
    }


    /**
     * Tests the image setter
     */
    @Test
    public void testGetImage() {
        PImage expectedImage = GameManager.getInstance().window.loadImage("Data/ghost.png");
        PImage actualImage = ghost.getImage();
        assertEquals(expectedImage.width, actualImage.width);
        assertEquals(expectedImage.height, actualImage.height);
    }

    /**
     * Tests the image getter
     */
    @Test
    public void testSetImage() {
        PImage expectedImage = GameManager.getInstance().window.loadImage("Data/redghost.png");
        ghost.setImage(expectedImage);
        PImage actualImage = ghost.getImage();
        assertEquals(expectedImage, actualImage);
    }

}