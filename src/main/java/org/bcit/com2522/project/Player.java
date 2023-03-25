package org.bcit.com2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

//import static processing.awt.ShimAWT.loadImage;

/**
 * Player. Defines a player character controlled by the user.
 */
public class Player extends Sprite {

  public static final int PLAYER_HEIGHT = 44;

  public static final int PLAYER_WIDTH = 27;

  private boolean alive;
  private float immunityTimer;
  private PImage harryPotterImage;

  private PImage playerLeft;
  private float rotationAngle;
  private boolean falling;

  public PImage getHarryPotterImage() {
    return harryPotterImage;
  }

  public float getRotationAngle() {
    return rotationAngle;
  }


  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public boolean isFalling() {
    return falling;
  }

  public void setFalling(boolean falling) {
    this.falling = falling;
  }

  public void moveDown(float speed) {
    getPosition().y += speed;
  }

  public void setHarryPotterImage(PImage harryPotterImage) {
    this.harryPotterImage = harryPotterImage;
  }

  public Player(PVector position, PVector direction, float size, float speed, Color color, Window window, String imagePath) {
    super(position, direction, size, speed, color, window);
    this.harryPotterImage = window.loadImage(imagePath);
    alive = true;
    immunityTimer = 0;
  }




  public float getImmunityTimer() {
    return immunityTimer;
  }

  public void setImmunityTimer(float immunityTimer) {
    this.immunityTimer = immunityTimer;
  }

  @Override
  public boolean collision(Sprite s){
    float dist = PVector.dist(s.getPosition(), getPosition());
    if (dist <= (s.getSize() / 2) + (getSize() / 2)){
      return true;
    }
    return false;
  }


  public void moveForward(float distance) {
    getPosition().add(PVector.mult(getDirection(), distance));
  }

  public void moveBackward(float distance) {
    getPosition().sub(PVector.mult(getDirection(), distance));
  }

  public void moveLeft(float distance) {
    getPosition().x -= distance;
  }

  public void moveRight(float distance) {
    getPosition().x += distance;
  }

  public void rotateLeft(float angle) {
    rotationAngle -= angle;
  }

  public void rotateRight(float angle) {
    rotationAngle += angle;
  }

  @Override
  public void draw() {
    if (harryPotterImage == null) {
      return;
    }

//    float scaleFactor = 0.35f; // Adjust this value to change the image size (e.g., 0.5 for half size, 0.25 for quarter size)
//    getWindow().image(harryPotterImage, getPosition().x, getPosition().y, getSize() * scaleFactor, getSize() * scaleFactor);
  }

}
