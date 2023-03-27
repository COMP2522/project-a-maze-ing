package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Blade extends Trap {

    private float oscillationSpeed;
    private float oscillationAngle;
    private float verticalSpeed;
    private float movementDirection;
    public Blade(PVector position, PVector direction, float size, float speed, Color color, Window window, float oscillationSpeed, float verticalSpeed) {
        super(position, direction, size, speed, color, window);
        this.oscillationSpeed = oscillationSpeed;
        this.oscillationAngle = 0;
        this.verticalSpeed = verticalSpeed;
        this.movementDirection = 1;
    }

    public Blade(PVector pos, Window w) {
        super();
    }


    public void move() {
        getPosition().y += movementDirection * verticalSpeed;

        if (getPosition().y <= 0 || getPosition().y >= getWindow().height - getSize()) {
            movementDirection *= -1;
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

