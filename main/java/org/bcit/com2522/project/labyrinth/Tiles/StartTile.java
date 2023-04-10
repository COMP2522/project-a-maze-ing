package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.GameManager;
import processing.core.PVector;

public class StartTile extends Tile{


  public StartTile(PVector pos) {
    super(pos);
  }

  @Override
  public void draw() {
    GameManager.getInstance().window.pushStyle();
    GameManager.getInstance().window.fill(51, 153, 255);
    GameManager.getInstance().window.rect(position.x, position.y, TILE_SIZE, TILE_SIZE);
    GameManager.getInstance().window.popStyle();
  }
}
