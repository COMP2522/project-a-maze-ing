package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Sprite;
import processing.core.PVector;

import java.awt.*;

/**
 * The Trap class represents a basic trap in the game.
 * It extends the Sprite class and provides basic collision detection.
 * The Trap class does not include any unique behavior or visuals.
 * @author Laurie Solkoski
 * @author Nelson Peterson-Hui
 * @version 1.0
 *
 */
public class Trap extends Sprite {
    public Trap(PVector position, PVector direction, float size, float speed, Color color) {
        super(position, direction, size, speed, color);
    }

    /**
     * Determines whether this Trap collides with the given Sprite by calculating the distance between their positions.
     * @param sprite the Sprite to check for collision with
     * @return true if the Trap collides with the given Sprite, false otherwise
     */
    @Override
    public boolean collision(Sprite sprite) {
        float dist = PVector.dist(sprite.getPosition(), getPosition());
        return dist <= (sprite.getSize() / 2) + (getSize() / 2);
    }
}
