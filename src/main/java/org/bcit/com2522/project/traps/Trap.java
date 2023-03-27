package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Trap extends Sprite {
    public Trap(PVector position, PVector direction, float size, float speed, Color color, Window window) {
        super(position, direction, size, speed, color, window);
    }

    @Override
    public boolean collision(Sprite s) {
        return false;
    }
}
