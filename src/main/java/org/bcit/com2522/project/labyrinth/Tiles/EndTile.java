package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import processing.core.PVector;

public class EndTile extends Tile {

  public EndTile(PVector pos, Window w) {
    super(pos, w);

  }

  @Override
  public void draw() {
    window.pushStyle();
    window.fill(0, 153, 76);
    window.rect(position.x, position.y, TILE_SIZE, TILE_SIZE);
    window.popStyle();
  }
}
