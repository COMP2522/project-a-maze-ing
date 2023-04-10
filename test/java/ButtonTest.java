import org.bcit.com2522.project.Button;
import org.bcit.com2522.project.Menu;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class ButtonTest {
    /**
     * Test case for the constructor
     */
    @Test
    void testConstructor() {
        Menu menu = new Menu();
        Button button = new Button("Click me", 10, 20, 50, 30, Color.RED, menu);
        assertEquals("Click me", button.getLabel());
        assertEquals(10, button.getX());
        assertEquals(20, button.getY());
        assertEquals(50, button.getWidth());
        assertEquals(30, button.getHeight());
        assertEquals(Color.RED, button.getBg());

    }

    /**
     * Test case for the getters
     */
    @Test
    void testGetters() {
        Menu menu = new Menu();
        Button button = new Button("Click me", 10, 20, 50, 30, Color.RED, menu);

        assertEquals(10, button.getX());
        assertEquals(20, button.getY());
        assertEquals(50, button.getWidth());
        assertEquals(30, button.getHeight());
        assertEquals(Color.RED, button.getBg());

    }


    /**
     * Tests the cursorInside method
     */
    @Test
    void testCursorInside() {
        Menu menu = new Menu();
        Button button = new Button("Click me", 10, 20, 50, 30, Color.RED, menu);
        assertTrue(button.cursorInside(20, 25));
        assertFalse(button.cursorInside(5, 15));
    }



}