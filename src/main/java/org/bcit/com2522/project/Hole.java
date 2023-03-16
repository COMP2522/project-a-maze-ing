package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

public class Hole extends Sprite {
    public Hole(PVector position, float size, Color color, Window window) {
        super(position, new PVector(0, 0), size, 0, color, window);
    }

    @Override
    public void draw() {
        window.fill(color.getRGB());
        window.ellipse(getPosition().x, getPosition().y, size, size);
    }
}

