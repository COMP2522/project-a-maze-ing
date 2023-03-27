package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
public class Ghost extends Enemy implements Movable {

  /* Hitbox size in pixels of ghost enemy type.*/
  public static final int GHOST_SIZE = 30;

  public static final int GHOST_LENGTH = 35;

  private static Ghost ghostInstance = null;

  PImage ghostImage;

  public Ghost(PVector position, PVector direction, float size, float speed, Color color, Window window, String imagePath){
    super(position, direction, size, speed, color, window);
    this.ghostImage = window.loadImage(imagePath);
  }

  public static Ghost getInstance(PVector position, PVector direction, float size, float speed, Color color, Window window, String imagePath) {
    if (ghostInstance == null) {
      ghostInstance = new Ghost(position, direction, size, speed, color, window, imagePath);
    }
    return ghostInstance;
  }

  public PImage getImage(){
    return ghostImage;
  }

  public void setImage(PImage image){
    this.ghostImage = image;
  }

  /**
   * Update ghost position by checking the position of the player and moving
   * the ghost towards the player.
   */
  @Override
  public void move() {
    Player player = Player.getInstance();
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    direction.normalize();
    direction.mult(getSpeed());
    setPosition(PVector.add(getPosition(), direction));
    if (direction.x < 0){
      this.setImage(player.getWindow().loadImage("Data/ghostLeft.png"));
    } else if (direction.x > 0){
      this.setImage(player.getWindow().loadImage("Data/ghostRight.png"));
    }
  }

  public void update() {
      move();

  }

}
