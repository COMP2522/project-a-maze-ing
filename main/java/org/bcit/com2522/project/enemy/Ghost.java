package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.Player;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

/**
 * The ghost class will load as a single instance in the window.
 * The ghost follows the player around and emulates similar
 * properties to other enemy types.
 */
public class Ghost extends Enemy{

  /* Hitbox size of ghost enemy type.*/
  public static final int GHOST_SIZE = 30;

  /* Sets the speed of the ghost. */
  public static int ghostSpeed = 2;

  /* Sets the dimensions of the ghost image (is a square).*/
  public static final int GHOST_LENGTH = 35;

  /* Sets the starting distance of the ghost away from the player. */
  public static final int GHOST_START = 300;

  /* Single instance of the ghost. */
  private static Ghost ghostInstance = null;

  /*Image to represent ghost. */
  private PImage ghostImage;

  /*Condition to check if ghost is in hyper mode. */
  private static boolean isHyper = false;

  /**
   * Singleton ghost constructor that haunts the player and follows the player around
   * @param position
   * @param direction
   * @param size
   * @param speed
   * @param color
   * @param imagePath
   */
  private Ghost(PVector position, PVector direction, float size, float speed, Color color, String imagePath){
    super(position, direction, size, speed, color);
    this.ghostImage = GameManager.getInstance().window.loadImage(imagePath);
  }

  /**
   * Checks to see if there is a single instance of ghost. If no instance,
   * a new one is made.
   * @param position
   * @param direction
   * @param size
   * @param speed
   * @param color
   * @param imagePath
   * @return ghostInstance
   */
  public static Ghost getInstance(PVector position, PVector direction, float size, float speed, Color color, String imagePath) {
    if (ghostInstance == null) {
      ghostInstance = new Ghost(position, direction, size, speed, color, imagePath);
    }
    return ghostInstance;
  }

  /**
   * Gets the image that represents the ghost
   * @return
   */
  public PImage getImage(){
    return ghostImage;
  }

  /**
   * Sets the image to represent the ghost
   * @param image
   */
  public void setImage(PImage image){
    this.ghostImage = image;
  }

  /**
   * Puts the ghost in hyper mode and increases speed
   */
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

    //Moves ghost towards player, changes images and sets checks for hypermode
    if (direction.x < 0){
      if (isHyper){
        this.setImage(GameManager.getInstance().window.loadImage("Data/redghostleft.png"));
      } else {
        this.setImage(GameManager.getInstance().window.loadImage("Data/ghostLeft.png"));
      }
    } else if (direction.x > 0){
      if (isHyper){
        this.setImage(GameManager.getInstance().window.loadImage("Data/redghostright.png"));
      } else {
        this.setImage(GameManager.getInstance().window.loadImage("Data/ghostRight.png"));
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
  GameManager.getInstance().window.image(getImage(), getPosition().x - GHOST_LENGTH/2,
        getPosition().y - GHOST_LENGTH/2 , GHOST_LENGTH , GHOST_LENGTH);
    }

  /**
   * Setter for ghostSpeed
   * @param ghostSpeed
   */
  public static void setGhostSpeed(int ghostSpeed) {
    Ghost.ghostSpeed = ghostSpeed;
  }

  /**
   * Setter for isHyper
   * @param isHyper
   */
  public static void setIsHyper(boolean isHyper) {
    Ghost.isHyper = isHyper;
  }

  /**
   * Getter for ghostSpeed
   * @return ghostSpeed
   */
  public static int getGhostSpeed() {
    return ghostSpeed;
  }

  /**
   * Getter for isHyper
   * @return isHyper
   */
  public static boolean getIsHyper() {
    return isHyper;
  }


}
