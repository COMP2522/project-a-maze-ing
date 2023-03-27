package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import processing.core.PVector;

public class StartTile extends Tile{


  public StartTile(PVector pos, Window w) {
    super(pos, w);
  }

  @Override
  public void draw() {
    window.pushStyle();
    window.fill(51, 153, 255);
    window.rect(position.x, position.y, TILE_SIZE, TILE_SIZE);
    window.popStyle();
  }
}
