package org.bcit.com2522.project;

import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PVector;

import java.awt.*;
public class Ghost extends Enemy implements Movable {

  /* Hitbox size in pixels of ghost enemy type.*/
  public static final int GHOST_SIZE = 30;
  private static Ghost ghostInstance = null;

  private Player player; //Reference to the player object

  Ghost(PVector position, PVector direction, float size, float speed, Color color, Window window){
    super(position, direction, size, speed, color, window);
  }

  public static Ghost getInstance(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    if (ghostInstance == null) {
      ghostInstance = new Ghost(position, direction, size, speed, color, window);
    }
    return ghostInstance;
  }

  /**
   * Update ghost position by checking the position of the player and moving
   * the ghost towards the player.
   */
  @Override
  public void move(Player player) {
    this.player = player;
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    direction.normalize();
    direction.mult(getSpeed());
    setPosition(PVector.add(getPosition(), direction));
  }

  public void update() {
    if (player != null) {
      move(player);
    }
  }

}
