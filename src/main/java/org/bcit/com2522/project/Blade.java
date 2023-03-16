package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

public class Blade extends Enemy {
    private float oscillationAngle = 0;

    public Blade(PVector position, PVector direction, float size, float speed, Color color, Window window) {
        super(position, direction, size, speed, color, window);
    }

    @Override
    public void update() {
        oscillationAngle += 0.05;
        getPosition().y += Math.sin(oscillationAngle) * getSpeed();
    }


    @Override
    public void draw() {
        window.pushMatrix();
        window.translate(getPosition().x, getPosition().y);

        // Rotate the blade to give it a slashing motion
        window.rotate(oscillationAngle / 2);

        window.fill(color.getRGB());

        // Create a custom blade shape
        window.beginShape();
        window.vertex(-size / 4, -size / 4);
        window.vertex(size / 4, -size / 4);
        window.vertex(size / 2, 0);
        window.vertex(size / 4, size / 4);
        window.vertex(-size / 4, size / 4);
        window.vertex(-size / 2, 0);
        window.endShape(2);

        window.popMatrix();
    }

}
