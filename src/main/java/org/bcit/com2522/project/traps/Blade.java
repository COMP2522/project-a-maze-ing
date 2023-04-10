package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

/**
 * Blade.
 * Represents a spinning blade trap in the game.
 * It extends the Trap class and implements the Drawable interface.
 * @author Laurie Solkoski
 * @author Nelson Peterson-Hui
 * @version 1.0
 *
 */
public class Blade extends Trap implements Drawable {

    /**
     * The path to the image file for the blade.
     */
    public static final String BLADE_PATH = "Data/blade.png";

    /**
     * The size of the blade image.
     */
    public static final int BLADE_SIZE = 50;

    /**
     * The oscillation speed of the blade.
     */
    private float oscillationSpeed;

    /**
     * The oscillation angle of the blade.
     */
    private float oscillationAngle;

    /**
     * The vertical speed of the blade.
     */
    private float verticalSpeed;

    /**
     * The movement direction of the blade.
     */
    private float movementDirection;

    /**
     * The starting position of the blade.
     */
    private PVector startPosition;

    /**
     * The ending position of the blade.
     */
    private PVector endPosition;

    /**
     * The image of the blade.
     */
    PImage bladeImage;

    /**
     * Constructs a Blade object with the given parameters.
     *
     * @param position The starting position of the blade
     * @param direction The direction of the blade's movement
     * @param size The size of the blade
     * @param speed The speed of the blade
     * @param color The color of the blade
     * @param oscillationSpeed The speed of the blade's oscillation
     * @param verticalSpeed The speed of the blade's vertical movement
     * @param startPosition The starting position of the blade's vertical movement
     * @param endPosition The ending position of the blade's vertical movement
     * @param imagePath The path to the image file for the blade
     */
    public Blade(PVector position, PVector direction, float size,
                 float speed, Color color,
                 float oscillationSpeed, float verticalSpeed,
                 PVector startPosition, PVector endPosition, String imagePath) {
        super(position, direction, size, speed, color);
        this.oscillationSpeed = oscillationSpeed;
        this.oscillationAngle = 0;
        this.verticalSpeed = verticalSpeed;
        this.movementDirection = 1;
        this.endPosition = endPosition;
        this.startPosition = startPosition;
        this.bladeImage = GameManager.getInstance().window.loadImage(imagePath);
    }

    /**
     * Moves the blade in a vertical direction.
     */
    public void move() {

        PVector direction = endPosition.copy().sub(startPosition);
        direction.normalize();
        direction.mult(verticalSpeed);
        getPosition().add(direction);


        if (getPosition().dist(startPosition) > endPosition.dist(startPosition)) {
            PVector tempPosition = endPosition;
            endPosition = startPosition;
            startPosition = tempPosition;
        }
    }

    /**
     * Draws the spinning blade at its current position, using the PApplet's image() and translate() methods.
     * The blade's rotation is animated using an oscillation angle and speed.
     * The position of the blade is updated using the move() method.
     */
    @Override
    public void draw() {

        //Blade image
        GameManager.getInstance().window.pushMatrix();
        GameManager.getInstance().window.translate(getPosition().x, getPosition().y);
        GameManager.getInstance().window.rotate(oscillationAngle);
        GameManager.getInstance().window.imageMode(PConstants.CORNER);
        GameManager.getInstance().window.image(bladeImage, -BLADE_SIZE/2, -BLADE_SIZE/2, BLADE_SIZE , BLADE_SIZE);
        GameManager.getInstance().window.popMatrix();
        move();

        oscillationAngle += oscillationSpeed;

    }

}

