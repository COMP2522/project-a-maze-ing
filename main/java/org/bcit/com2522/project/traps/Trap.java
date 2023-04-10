package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Sprite;
import processing.core.PVector;

import java.awt.*;

public class Trap extends Sprite {
    public Trap(PVector position, PVector direction, float size, float speed, Color color) {
        super(position, direction, size, speed, color);
    }

    public Trap() {
        super();
    }

    @Override
    public boolean collision(Sprite sprite) {
        float dist = PVector.dist(sprite.getPosition(), getPosition());
        return dist <= (sprite.getSize() / 2) + (getSize() / 2);
    }
}
