package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Drawable;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
public class Ghost extends Enemy implements Movable, Drawable {

  /* Hitbox size of ghost enemy type.*/
  public static final int GHOST_SIZE = 30;

  /* Sets the speed of the ghost. */
  public static int ghostSpeed = 2;


  public static final int GHOST_LENGTH = 35;

  public static final int GHOST_START = 300;

  private static Ghost ghostInstance = null;

  PImage ghostImage;

  private static boolean isHyper = false;


  private Ghost(PVector position, PVector direction, float size, float speed, Color color, Window window, String imagePath){
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

  public static void becomeHyper(){
    ghostSpeed = 8;
    isHyper = true;
  }

  /**
   * Update ghost position by checking the position of the player and moving
   * the ghost towards the player. Updates hyper mode of ghost too
   */
  @Override
  public void move() {
    Player player = Player.getInstance();
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    direction.normalize();
    direction.mult(ghostSpeed);
    setPosition(PVector.add(getPosition(), direction));
    if (direction.x < 0){
      if (isHyper){
        this.setImage(player.getWindow().loadImage("Data/redghostleft.png"));
      } else {
        this.setImage(player.getWindow().loadImage("Data/ghostLeft.png"));
      }
    } else if (direction.x > 0){
      if (isHyper){
        this.setImage(player.getWindow().loadImage("Data/redghostright.png"));
      } else {
        this.setImage(player.getWindow().loadImage("Data/ghostRight.png"));
      }
    }
  }

  /**
   * Updates the position of the ghost
   */
  public void update() {
      move();
  }

  /**
   * Draws the ghost image and sets the ghost size (not the hitbox)
   */
  @Override
  public void draw() {
    EnemyManager.getInstance().getWindow().image(getImage(), getPosition().x - GHOST_LENGTH/2,
        getPosition().y - GHOST_LENGTH/2 , GHOST_LENGTH , GHOST_LENGTH);
    }



}
