package org.bcit.com2522.project;

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
  private static final int PLAYER_SPEED = 5;

  private static Player instance;

  private boolean alive = true;
  private float immunityTimer;
  private PImage harryPotterImage;

  PImage playerDown;

  PImage playerLeft;
  PImage playerRight;
  PImage playerUp;

  private boolean falling;

  public PImage getHarryPotterImage() {
    return harryPotterImage;
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

  private Player(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
    this.harryPotterImage = window.loadImage("Data/HPfront.png");

    this.playerDown = window.loadImage("Data/HPfront.png");
    this.playerLeft = window.loadImage("Data/HPleft.png");
    this.playerRight = window.loadImage("Data/HPright.png");
    this.playerUp = window.loadImage("Data/HPup.png");

    alive = true;
    immunityTimer = 0;
  }

  public static Player getInstance(Window w) {
    if (instance == null) {
      instance = new Player(
          new PVector(0, 0),
          new PVector(0, 0),
          10,
          PLAYER_SPEED,
          new Color(0, 255, 0), w);
    }
    return instance;
  }

  public static Player getInstance() {
    return instance;
  }

  public void setHarryPotterImage(PImage harryPotterImage) {
    this.harryPotterImage = harryPotterImage;
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

  @Override
  public void draw() {
    getWindow().image(getHarryPotterImage(), getPosition().x - PLAYER_WIDTH/2,
        getPosition().y - PLAYER_HEIGHT/2, PLAYER_WIDTH , PLAYER_HEIGHT);

  }

}
