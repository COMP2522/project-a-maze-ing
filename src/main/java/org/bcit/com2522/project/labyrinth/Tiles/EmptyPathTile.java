package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import processing.core.PImage;
import processing.core.PVector;

public class EmptyPathTile extends Tile {

  private PImage dirtImage;

  public EmptyPath(PVector pos, Window w) {
  public EmptyPathTile(PVector pos, Window w) {
    super(pos, w);
    dirtImage = new PImage(TILE_SIZE, TILE_SIZE);
    dirtImage = window.loadImage("images/dort.png");
  }

  @Override
  public void draw() {
    window.image(dirtImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}
