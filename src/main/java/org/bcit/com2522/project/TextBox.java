package org.bcit.com2522.project;

import org.bcit.com2522.project.Database.Database;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

/**
 * This class represents a text box in the user interface.
 * Allows users to enter text and save a labyrinth with a given name.
 * @author Laurie Solkoski
 * @version 1.0
 */
public class TextBox {

    /*
    The PApplet instance associated with the TextBox.
     */
    PApplet parent;

    /*
    The position of the top-left corner of the TextBox.
     */
    PVector position;

    /*
    Width and height of text box.
     */
    int width, height;

    /*
    The text currently contained within the TextBox.
     */
    String text = "";

    /**
     * Creates a new TextBox with the specified position, width, and height.
     * @param parent the parent PApplet
     * @param position the position of the TextBox as a PVector
     * @param width the width of the TextBox
     * @param height the height of the TextBox
     */
    TextBox(PApplet parent, PVector position, int width, int height) {
        this.parent = parent;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    /*
    Draws the TextBox on the screen.
     */
    void draw() {
        parent.fill(255);
        parent.rect(position.x, position.y, width, height);
        parent.fill(0);
        parent.text(text, position.x + 5, position.y + height - 5);
    }

    /**
     * Handles a mouse click event on the TextBox.
     * If the saveButton is clicked and the text in the TextBox is not empty,
     * saves the current labyrinth with the given name and closes the TextBox.
     * @param m the MouseEvent to handle
     */
    public void mouseClicked(MouseEvent m) {
        if (parent instanceof Window) {
            Window win = (Window) parent;
            if (win.saveButton.contains(m.getX(), m.getY())) {
                if (!text.trim().isEmpty()) {
                    Database.getInstance().saveCurrent(text);
                    win.isTyping = false;
                }
            }
        }
    }

    /**
     * Checks if the specified coordinates are within the bounds of the TextBox.
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return true if the coordinates are within the bounds of the TextBox, false otherwise
     */
    boolean contains(int x, int y) {
        return x >= position.x && x <= position.x + width && y >= position.y && y <= position.y + height;
    }

    /**
     * Adds the specified character to the text in the TextBox.
     * @param c the character to add
     */
    void addChar(char c) {
        text += c;
    }

    /*
    Removes the last character from the text in the TextBox.
     */
    void removeChar() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    /**
     * Gets the text currently in the TextBox.
     * @return the text in the TextBox
     */
    String getText() {
        return text;
    }
}
