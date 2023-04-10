import org.bcit.com2522.project.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WindowTest {

    private Window window;

    /**
     * Sets up a new instance of window before each test
     */
    @BeforeEach
    public void setUp() {
        window = new Window();
    }




    /**
     * Tests the getters and setter functions
     */

    @Test
    public void testGettersAndSetters() {
        // Test setters and getters for isTyping
        window.setIsTyping(true);
        assertTrue(window.getIsTyping());
        window.setIsTyping(false);
        assertFalse(window.getIsTyping());

        // Test setters and getters for funFact
        String funFact = "The tallest mountain in our solar system is Olympus Mons, located on Mars.";
        window.setFunFact(funFact);
        assertEquals(funFact, window.getFunFact());

        // Test setters and getters for playerAnimationTime
        float playerAnimationTime = 2.5f;
        window.setPlayerAnimationTime(playerAnimationTime);
        assertEquals(playerAnimationTime, window.getPlayerAnimationTime(), 0.001);

        // Test setters and getters for elpCount
        float elpCount = 1.5f;
        window.setElpCount(elpCount);
        assertEquals(elpCount, window.getElpCount(), 0.001);

        // Test setters and getters for loadingTimer
        int loadingTimer = 10;
        window.setLoadingTimer(loadingTimer);
        assertEquals(loadingTimer, window.getLoadingTimer());
    }



}