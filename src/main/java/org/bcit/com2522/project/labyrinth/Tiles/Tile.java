package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.interfaces.Collidable;
import processing.core.PVector;

public abstract class Tile implements Collidable {

  // The side length of a tile
  public static final int TILE_SIZE = 250; // placeholder value, adjust as needed

  private PVector position;

  public Tile(PVector pos) {
    position = pos;
  }

  @Override
  public boolean collision(Collidable a, Collidable b) {
    return false;
  }



}
