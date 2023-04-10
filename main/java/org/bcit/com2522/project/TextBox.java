package org.bcit.com2522.project;

import org.bcit.com2522.project.Database.Database;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class TextBox {
    PApplet parent;
    PVector position;
    int width, height;
    String text = "";

    public TextBox(PApplet parent, PVector position, int width, int height) {
        this.parent = parent;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    // Getter for position
    public PVector getPosition() {
        return position;
    }

    // Setter for position
    public void setPosition(PVector position) {
        this.position = position;
    }

    // Getter for width
    public int getWidth() {
        return width;
    }

    // Setter for width
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter for height
    public int getHeight() {
        return height;
    }

    // Setter for height
    public void setHeight(int height) {
        this.height = height;
    }



    // Setter for text
    public void setText(String text) {
        this.text = text;
    }

    void draw() {
        parent.fill(255);
        parent.rect(position.x, position.y, width, height);
        parent.fill(0);
        parent.text(text, position.x + 5, position.y + height - 5);
    }

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

    public boolean contains(int x, int y) {
        return x >= position.x && x <= position.x + width && y >= position.y && y <= position.y + height;
    }

    public void addChar(char c) {

        text += c;
    }

    public void removeChar() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    public PApplet getParent() {
        return parent;

    }

    public String getText() {

        return text;
    }
}
