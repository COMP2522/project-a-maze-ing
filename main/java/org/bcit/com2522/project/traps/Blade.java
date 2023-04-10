package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

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

