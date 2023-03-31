package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Blade extends Trap {

    private float oscillationSpeed;
    private float oscillationAngle;
    private float verticalSpeed;
    private float movementDirection;

    private PVector startPosition;
    private PVector endPosition;

    public Blade(PVector position, PVector direction, float size,
                 float speed, Color color, Window window,
                 float oscillationSpeed, float verticalSpeed,
                 PVector startPosition, PVector endPosition) {
        super(position, direction, size, speed, color, window);
        this.oscillationSpeed = oscillationSpeed;
        this.oscillationAngle = 0;
        this.verticalSpeed = verticalSpeed;
        this.movementDirection = 1;
        this.endPosition = endPosition;
        this.startPosition = startPosition;
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
        move();

        getWindow().pushMatrix();
        getWindow().pushStyle();

        getWindow().translate(getPosition().x, getPosition().y);
        getWindow().rotate(oscillationAngle);

        getWindow().fill(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
        getWindow().beginShape();

        // Create jagged shape (feel free to modify the points to get the desired shape)
        getWindow().vertex(0, 0);
        getWindow().vertex(getSize() / 2, -getSize() / 3);
        getWindow().vertex(getSize(), 0);
        getWindow().vertex(getSize() / 2, getSize() / 3);
        getWindow().vertex(0, 0);

        getWindow().endShape();

        getWindow().popStyle();
        getWindow().popMatrix();

        oscillationAngle += oscillationSpeed;
    }


}

