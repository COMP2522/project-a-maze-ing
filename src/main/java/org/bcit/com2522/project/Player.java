package org.bcit.com2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Player. Defines a player character controlled by the user.
 */
public class Player extends Sprite{
  private PImage harryPotterImage;
  private float rotationAngle;


  public Player(PVector position, PVector direction, float speed, Window window, PImage harryPotterImage) {
    super(position, direction, Math.max(harryPotterImage.width, harryPotterImage.height), speed, null, window); // Pass null for color
    this.harryPotterImage = harryPotterImage;
  }



  public boolean collidesWith(Sprite other) {
    float minDistance = size / 2 + other.getSize() / 2;
    return getPosition().dist(other.getPosition()) < minDistance;
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

    window.pushMatrix();
    window.translate(getPosition().x, getPosition().y);
    window.rotate(rotationAngle);
    window.image(harryPotterImage, -harryPotterImage.width/2 , -harryPotterImage.height/2, harryPotterImage.width/2, harryPotterImage.height/2 );
    window.popMatrix();
  }

//
//  @Override
//  public void draw() {
//    window.fill(255, 0, 0); // Set a color for the circle (e.g., red)
//    window.ellipse(getPosition().x, getPosition().y, size, size);
//  }


//  @Override
//  public void draw() {
//    window.fill(255, 0, 0);
//    window.ellipse(getPosition().x, getPosition().y, size, size);
//
//    if (harryPotterImage != null) {
//      window.pushMatrix();
//      window.translate(getPosition().x, getPosition().y);
//      window.rotate(rotationAngle);
//      window.image(harryPotterImage, -harryPotterImage.width / 2, -harryPotterImage.height / 2, harryPotterImage.width/2, harryPotterImage.height/2);
//      window.popMatrix();
//    }
  }









