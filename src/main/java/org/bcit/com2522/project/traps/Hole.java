package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

/**
 * The Hole class represents a trap in the game that is designed to look like a hole in the ground.
 * It extends the Trap class and implements the Drawable interface to allow it to be drawn on the screen.
 * The Hole class includes constant fields for the hole's image path and size.
 * The Hole class uses a PImage to store the image of the hole.
 * @author Nelson Peterson-Hui
 * @author Laurie Solkoski
 * @version 1.0
 */
public class Hole extends Trap implements Drawable {

    /**
     * The size of the hole image.
     */
    public static final int HOLE_SIZE = 70;

    /**
     * The path to the image file for the hole.
     */
    public static final String HOLE_PATH = "Data/hole.png";

    /**
     * The image of the hole.
     */
    PImage holeImage;

    /**
     * Constructs a new Hole with the given position, direction, size, speed, color, and image path.
     * @param position the position of the hole
     * @param direction the direction the hole is facing
     * @param size the size of the hole
     * @param speed the speed of the hole
     * @param color the color of the hole
     * @param imagePath the path to the image file for the hole
     */
    public Hole(PVector position, PVector direction, float size, float speed, Color color, String imagePath) {
        super(position, direction, size, speed, color);
        this.holeImage = GameManager.getInstance().window.loadImage(imagePath);
    }

    /**
     * Draws the hole at its current position, using the PApplet's image() method.
     */
    @Override
    public void draw() {

        GameManager.getInstance().window.image(holeImage, getPosition().x - HOLE_SIZE / 2,
            getPosition().y - HOLE_SIZE / 2 , HOLE_SIZE , HOLE_SIZE);
    }
}
