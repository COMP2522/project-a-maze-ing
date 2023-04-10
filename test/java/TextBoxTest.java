
import org.bcit.com2522.project.TextBox;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class TextBoxTest {
    PApplet parent = new PApplet();
    PVector position = new PVector(0, 0);
    int width = 100;
    int height = 50;

    /**
     * Tests the constructor
     */
    @Test
    void testConstructor() {
        TextBox textBox = new TextBox(parent, position, width, height);
        assertEquals(parent, textBox.getParent());
        assertEquals(position, textBox.getPosition());
        assertEquals(width, textBox.getWidth());
        assertEquals(height, textBox.getHeight());
        assertEquals("", textBox.getText());
    }

    /**
     * Tests initiation and access of position
     */
    @Test
    void testPosition() {
        TextBox textBox = new TextBox(parent, position, width, height);
        PVector newPosition = new PVector(10, 10);
        textBox.setPosition(newPosition);
        assertEquals(newPosition, textBox.getPosition());
    }

    /**
     * Tests mutation, initiation and access of width
     */
    @Test
    void testWidth() {
        TextBox textBox = new TextBox(parent, position, width, height);
        int newWidth = 200;
        textBox.setWidth(newWidth);
        assertEquals(newWidth, textBox.getWidth());
    }

    /**
     * Tests mutation, initiation and access of height
     */
    @Test
    void testHeight() {
        TextBox textBox = new TextBox(parent, position, width, height);
        int newHeight = 75;
        textBox.setHeight(newHeight);
        assertEquals(newHeight, textBox.getHeight());
    }

    /**
     * Tests initiation, mutation and access of text
     */

    @Test
    void testText() {
        TextBox textBox = new TextBox(parent, position, width, height);
        String newText = "new text";
        textBox.setText(newText);
        assertEquals(newText, textBox.getText());
    }


    /**
     * Tests the addCHar method
     */
    @Test
    void testAddChar() {
        TextBox textBox = new TextBox(parent, position, width, height);
        char c = 'a';
        textBox.addChar(c);
        assertEquals("a", textBox.getText());
        textBox.addChar('b');
        assertEquals("ab", textBox.getText());
    }

    /**
     * tests the removeChar method
     */
    @Test
    void testRemoveChar() {
        TextBox textBox = new TextBox(parent, position, width, height);
        textBox.setText("abc");
        textBox.removeChar();
        assertEquals("ab", textBox.getText());
        textBox.removeChar();
        assertEquals("a", textBox.getText());
        textBox.removeChar();
        assertEquals("", textBox.getText());
        textBox.removeChar();
        assertEquals("", textBox.getText());
    }


    @Test
    void testContains() {
        TextBox textBox = new TextBox(parent, position, width, height);
        assertTrue(textBox.contains(0, 0));
        assertTrue(textBox.contains(50, 25));
        assertFalse(textBox.contains(-1, 0));
        assertFalse(textBox.contains(0, -1));
        assertFalse(textBox.contains(width, height));
        assertFalse(textBox.contains(width + 1, height));
        assertFalse(textBox.contains(width, height + 1));
    }

}