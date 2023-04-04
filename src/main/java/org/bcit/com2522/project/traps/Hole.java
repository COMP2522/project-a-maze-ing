package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;


public class Hole extends Trap implements Drawable {

    public static final int HOLE_SIZE = 70;

    public static final String HOLE_PATH = "Data/hole.png";

    PImage holeImage;

    public Hole(PVector position, PVector direction, float size, float speed, Color color, String imagePath) {
        super(position, direction, size, speed, color);
        this.holeImage = GameManager.getInstance().window.loadImage(imagePath);
    }

    public Hole(PVector pos) {
    }

    @Override
    public void draw() {

        GameManager.getInstance().window.image(holeImage, getPosition().x - HOLE_SIZE / 2,
            getPosition().y - HOLE_SIZE / 2 , HOLE_SIZE , HOLE_SIZE);
    }



}
