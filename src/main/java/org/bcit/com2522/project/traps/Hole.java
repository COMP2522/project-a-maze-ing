package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;


public class Hole extends Trap implements Drawable {

    public static final int HOLE_SIZE = 70;

    public static final String HOLE_PATH = "Data/hole.png";

    PImage holeImage;

    public Hole(PVector position, PVector direction, float size, float speed, Color color, Window window, String imagePath) {
        super(position, direction, size, speed, color, window);
        this.holeImage = window.loadImage(imagePath);
    }

    public Hole(PVector pos, Window w) {
    }

    @Override
    public void draw() {
//        getWindow().pushStyle();
//        getWindow().fill(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
//        getWindow().ellipse(getPosition().x, getPosition().y, getSize(), getSize());
//        getWindow().popStyle();
        TrapManager.getInstance().getWindow().image(holeImage, getPosition().x - HOLE_SIZE / 2,
            getPosition().y - HOLE_SIZE / 2 , HOLE_SIZE , HOLE_SIZE);
    }



}
