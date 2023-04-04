package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Drawable;
import processing.core.PVector;

public abstract class Tile implements Drawable {

  // The side length of a tile

  public static final int TILE_SIZE = 250; // placeholder value, adjust as needed

  public static final int TILE_HALF_LENGTH = TILE_SIZE / 2;


  /* The position of the tile's top-left corner. */
  protected PVector position;

  protected Window window;

  public Tile(PVector pos, Window w) {
    position = pos;
    window = w;
  }

  /**
   * Getter for tile position.
   * @return the position of the tile.
   */
  public PVector getPosition() {
    return position.copy();
  }

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
