package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.interfaces.Collidable;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Random;

public class Wall extends Tile implements Collidable{

  /**
   * the image of this wall.
   */
  PImage wallImage;

  /**
   * Arraylist containing the wall image variants for randomization.
   */
  String[] wallImageVariants = {
      "wall1.png",
      "wall2.png"
  };

  /**
   * Constructor.
   * @param pos
   */
  public Wall(PVector pos) {
    super(pos);
    Random rng = new Random();
    String path = "images/" + wallImageVariants[rng.nextInt(wallImageVariants.length)];
    wallImage = GameManager.getInstance().window.loadImage(path);
  }

  /**
   * Wall collision handling.
   * @param s the sprite colliding with this wall.
   */
  public void walkIntoWall(Sprite s) {
    PVector temp = s.getDirection().copy().normalize();
    float sX = s.getPosition().x;
    float sY = s.getPosition().y;
    while (!(sX + temp.x >= position.x
        && sX + temp.x <= position.x + TILE_SIZE
        && sY + temp.y >= position.y
        && sY + temp.y <= position.y + TILE_SIZE)){
      s.setPosition(s.getPosition().add(temp));
      sX = s.getPosition().x;
      sY = s.getPosition().y;
    }
  }

  /**
   * Renders the wall image on the wall.s
   */
  public void draw(){
    GameManager.getInstance().window.image(wallImage, position.x, position.y, TILE_SIZE, TILE_SIZE);
  }
}
