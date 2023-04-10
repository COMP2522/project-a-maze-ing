import org.bcit.com2522.project.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    /**
     * Makes a player before each test
     */
    @BeforeEach
    public void setUp() {
        player = Player.getInstance();
    }

    /**
     * Tests the getInstance method
     */
    @Test
    public void testGetInstance() {
        assertNotNull(player);
        assertEquals(Player.getInstance(), player);
    }

    /**
     * Tests the isAlive method
     */
    @Test
    public void testIsAlive() {
        assertTrue(player.isAlive());
        player.setAlive(false);
        assertFalse(player.isAlive());
    }

    /**
     * Tests the setter and getter of immunityTimer
     */
    @Test
    public void testSetAndGetImmunityTimer() {
        player.setImmunityTimer(5.0f);
        assertEquals(5.0f, player.getImmunityTimer(), 0.0f);
    }


}