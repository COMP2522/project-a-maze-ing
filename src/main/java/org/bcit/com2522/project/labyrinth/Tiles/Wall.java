package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Collidable;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Wall extends Tile implements Collidable{

  PImage wallImage;
  Window window;

  public Wall(PVector pos, Window w) {
    super(pos);
    window = w;
    wallImage = new PImage(TILE_SIZE, TILE_SIZE);
    wallImage = window.loadImage("images/wall.png");
  }

  @Override
  public boolean collision(Sprite s) {
    float sX = s.getPosition().x;
    float sY = s.getPosition().y;
    if ((sX >= position.x && sX <= position.x + TILE_SIZE)
      && (sY >= position.y && sY <= position.y + TILE_SIZE)){
      return true;
    }
    return false;
  }

  public void draw(){
    window.image(wallImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}
