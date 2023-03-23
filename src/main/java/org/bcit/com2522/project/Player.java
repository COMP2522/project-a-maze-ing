package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

/**
 * Player. Defines a player character controlled by the user.
 */
public class Player extends Sprite{

  private boolean alive;
  private float immunityTimer;

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public Player(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
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


}
