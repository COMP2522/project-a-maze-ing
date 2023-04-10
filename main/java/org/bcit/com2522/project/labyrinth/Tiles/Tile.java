package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PVector;

public abstract class Tile implements Drawable {

  // The side length of every tile

  public static final int TILE_SIZE = 250;

  // Half of the side length of a tile. Used for calculating the center of a tile.
  public static final int TILE_HALF_LENGTH = TILE_SIZE / 2;


  /* The position of the tile's top-left corner. */
  protected PVector position;


  /**
   * Constructor.
   * @param pos the position to place the tile.
   */
  public Tile(PVector pos) {
    position = pos;
  }

  /**
   * Getter for tile position.
   * @return the position of the tile.
   */
  public PVector getPosition() {
    return position.copy();
  }

  /**
   * Collision logic.
   * @param s the sprite colliding with the tile.
   * @return true if colliding, false otherwise.
   */
  public boolean collision(Sprite s) {
    float sX = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).x;
    float sY = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).y;
    if ((sX >= position.x && sX <= position.x + TILE_SIZE)
        && (sY >= position.y && sY <= position.y + TILE_SIZE)){
      return true;
    }
    return false;
  }






}
