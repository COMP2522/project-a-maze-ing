package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.GameManager;
import processing.core.PImage;
import processing.core.PVector;

public class EmptyPathTile extends Tile {

  private PImage dirtImage;


  public EmptyPathTile(PVector pos) {
    super(pos);
    dirtImage = new PImage(TILE_SIZE, TILE_SIZE);
    dirtImage = GameManager.getInstance().window.loadImage("Data/dirt2.png");
  }

  @Override
  public void draw() {
    GameManager.getInstance().window.image(dirtImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}

