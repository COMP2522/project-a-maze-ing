package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Drawable;
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
                 float speed, Color color, Window window,
                 float oscillationSpeed, float verticalSpeed,
                 PVector startPosition, PVector endPosition, String imagePath) {
        super(position, direction, size, speed, color, window);
        this.oscillationSpeed = oscillationSpeed;
        this.oscillationAngle = 0;
        this.verticalSpeed = verticalSpeed;
        this.movementDirection = 1;
        this.endPosition = endPosition;
        this.startPosition = startPosition;
        this.bladeImage = window.loadImage(imagePath);
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
//        TrapManager.getInstance().getWindow().pushMatrix();
//        TrapManager.getInstance().getWindow().translate(getPosition().x, getPosition().y);
//        TrapManager.getInstance().getWindow().rotate(oscillationAngle);
//        TrapManager.getInstance().getWindow().imageMode(PConstants.CORNER);
//        TrapManager.getInstance().getWindow().image(bladeImage, -BLADE_SIZE/2, -BLADE_SIZE/2, BLADE_SIZE , BLADE_SIZE);
//        TrapManager.getInstance().getWindow().popMatrix();
//        move();
//
//        oscillationAngle += oscillationSpeed;

    }

}

