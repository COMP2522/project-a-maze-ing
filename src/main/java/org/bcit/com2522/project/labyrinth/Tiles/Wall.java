package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Collidable;
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
    float sX = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).x;
    float sY = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).y;
    if ((sX > position.x && sX < position.x + TILE_SIZE)
      && (sY > position.y && sY < position.y + TILE_SIZE)){
      return true;
    }
    return false;
  }

  public void walkIntoWall(Sprite s) {
    float fX = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).x;
    float fY = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).y;
    float iX = s.getPosition().x;
    float iY = s.getPosition().y;
    float vX = s.getDirection().x;
    float vY = s.getDirection().y;
    float aX, aY;
    if (vX > 0) {
      aX = position.x;
    } else if (vX < 0){
      aX = position.x + TILE_SIZE;
    } else {
      aX = fX;
    }
    if (vY > 0) {
      aY = position.y;
    } else if (vY < 0) {
      aY = position.y + TILE_SIZE;
    } else {
      aY = fY;
    }
    float nX, nY;
    float scale;
    float tX = vX != 0 && s.getSpeed() != 0 ? Math.abs(aX - iX) / vX * s.getSpeed() : Integer.MAX_VALUE;
    float tY = vY != 0 && s.getSpeed() != 0 ? Math.abs(aY - iY) / vY * s.getSpeed() : Integer.MAX_VALUE;
    if (tX < tY){
      nX = aX;
      scale = nX / fX;
      nY = (vY * scale) + iY;
    } else {
      nY = aY;
      scale = nY / fY;
      nX = (vX * scale) + iX;
    }
      s.setPosition(new PVector(nX, nY));
  }

  public void draw(){
    window.image(wallImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}
