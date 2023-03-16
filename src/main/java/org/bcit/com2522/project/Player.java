package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

/**
 * Player. Defines a player character controlled by the user.
 */
public class Player extends Sprite{

  public Player(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
  }

  public boolean collidesWith(Sprite other) {
    return getPosition().dist(other.getPosition()) < (size + other.getSize()) / 2;
  }




}
