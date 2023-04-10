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

    public static final String BLADE_PATH = "Data/blade.png";
    public static final int BLADE_SIZE = 50;
    private float oscillationSpeed;
    private float oscillationAngle;
    private float verticalSpeed;
    private float movementDirection;


    private PVector startPosition;
    private PVector endPosition;

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

