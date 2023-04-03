package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Collidable;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Random;

public class Wall extends Tile implements Collidable{

  PImage wallImage;
  String[] wallImageVariants = {
      "wall1.png",
      "wall2.png"
  };

  public Wall(PVector pos, Window w) {
    super(pos, w);
    Random rng = new Random();
    String path = "images/" + wallImageVariants[rng.nextInt(wallImageVariants.length)];
    wallImage = window.loadImage(path);
  }

  public void walkIntoWall(Sprite s) {
//    float fX = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).x;// final/projected x position of s
//    float fY = s.getPosition().copy().add(s.getDirection().copy().mult(s.getSpeed())).y;//final/projected y position of s
//    float iX = s.getPosition().x; //initial x position of s
//    float iY = s.getPosition().y; // initial y position of s
//    float vX = s.getDirection().x;//x component of the direction of s
//    float vY = s.getDirection().y;//y component of the direction of s
//    float aX, aY;
//    if (vX > 0) {
//      aX = position.x - 1;
//    } else if (vX < 0){
//      aX = position.x + TILE_SIZE + 1;
//    } else {
//      aX = iX + (iX == position.x ? -1 : 1);
//    }
//    if (vY > 0) {
//      aY = position.y - 1;
//    } else if (vY < 0) {
//      aY = position.y + TILE_SIZE + 1;
//    } else {
//      aY = iY + (iY == position.y ? -1 : 1);
//    }
//    float nX, nY;
//    float scale;
//    float tX = vX != 0 && s.getSpeed() != 0 ? Math.abs(aX - iX) / (vX * s.getSpeed()) : Integer.MAX_VALUE;
//    float tY = vY != 0 && s.getSpeed() != 0 ? Math.abs(aY - iY) / (vY * s.getSpeed()) : Integer.MAX_VALUE;
//    if (Float.compare(tX, tY) < 0){
//      nX = aX;
//      scale = nX / fX;
//      nY = (vY * s.getSpeed() * scale) + iY;
//    } else {
//      nY = aY;
//      scale = nY / fY;
//      nX = (vX * s.getSpeed() * scale) + iX;
//    }
    PVector top = position.copy().add(TILE_SIZE / 2, 0);
    PVector left = position.copy().add(0, TILE_SIZE / 2);
    PVector right = position.copy().add(TILE_SIZE, TILE_SIZE / 2);
    PVector Bottom = position.copy().add(TILE_SIZE / 2, TILE_SIZE);



      //s.setPosition(new PVector(nX, nY));
  }

  public void draw(){
    window.image(wallImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}
