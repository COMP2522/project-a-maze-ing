package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import processing.core.PVector;

public abstract class Tile {

  // The side length of a tile
  public static final int TILE_SIZE = 250; // placeholder value, adjust as needed

  private PVector position;

  private Window window;

  public Tile(PVector pos) {
    position = pos;
  }





}
