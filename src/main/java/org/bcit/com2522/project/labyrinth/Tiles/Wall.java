package org.bcit.com2522.project.labyrinth.Tiles;

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

  public void draw(){
    window.image(wallImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}
