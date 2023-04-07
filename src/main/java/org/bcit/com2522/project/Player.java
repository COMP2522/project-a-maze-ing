package org.bcit.com2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;


/**
 * Defines a player character controlled by the user
 * and animates player movement with various images.
 * Can collide with other sprites and is implemented
 * as a singleton.
 */
public class Player extends Sprite {

  /* Constants for player size, speed and dimensions.*/
  public static final int PLAYER_HEIGHT = 44;
  public static final int PLAYER_WIDTH = 27;
  public static final int PLAYER_SPEED = 10;
  private static final int PLAYER_SIZE = 10;

  // Singleton instance of Player
  private static Player instance;

  /* Player state variables. */
  private boolean alive = true;
  private float immunityTimer;

  /* Player images for different directions and walking animations. */
  private PImage harryPotterImage;
  public PImage playerDown;
  public PImage playerLeft;
  public PImage playerRight;
  public PImage playerUp;
  public PImage playerLeftWalk1;
  public PImage playerRightWalk1;
  public PImage playerUp1;
  public PImage playerDown1;

  /**
   * Constructs a new Player object with the given position, direction, size, speed and color.
   *
   * @param position  the position of the player
   * @param direction the direction of the player
   * @param size      the size of the player
   * @param speed     the speed of the player
   * @param color     the color of the player
   */
  private Player(PVector position, PVector direction, float size, float speed, Color color) {
    super(position, direction, size, speed, color);

    // Load player images
    this.harryPotterImage = GameManager.getInstance().window.loadImage("Data/HPfront.png");
    this.playerDown = GameManager.getInstance().window.loadImage("Data/HPfront.png");
    this.playerLeft = GameManager.getInstance().window.loadImage("Data/HPleft.png");
    this.playerRight = GameManager.getInstance().window.loadImage("Data/HPright.png");
    this.playerUp = GameManager.getInstance().window.loadImage("Data/HPup.png");
    this.playerLeftWalk1 = GameManager.getInstance().window.loadImage("Data/HPleft1.png");
    this.playerRightWalk1 = GameManager.getInstance().window.loadImage("Data/Hpright1.png");
    this.playerDown1 = GameManager.getInstance().window.loadImage("Data/HPfront1.png");
    this.playerUp1 = GameManager.getInstance().window.loadImage("Data/HPup1.png");

    alive = true;
    immunityTimer = 0;
  }

  /**
   * Returns the singleton instance of Player.
   *
   * @return the singleton instance of Player
   */
  public static Player getInstance() {
    if (instance == null) {
      instance = new Player(new PVector(0, 0), new PVector(0, 0), PLAYER_SIZE, PLAYER_SPEED, new Color(0, 255, 0));
    }
    return instance;
  }

  /**
   * Returns the current Harry Potter image for the Player.
   *
   * @return the current Harry Potter image
   */
  public PImage getHarryPotterImage() {
    return harryPotterImage;
  }

  /**
   * Sets the image of the player
   * @param harryPotterImage
   */
  public void setHarryPotterImage(PImage harryPotterImage) {
    this.harryPotterImage = harryPotterImage;
  }

  /**
   * returns the state of player being alive or dead
   * @return alive
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * sets player to alive or dead
   * @param alive
   */
  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  /**
   * gets the amount of time player is immune for
   * @return
   */
  public float getImmunityTimer() {
    return immunityTimer;
  }

  /**
   * Sets the amount of time player is immune for
   * @param immunityTimer
   */
  public void setImmunityTimer(float immunityTimer) {
    this.immunityTimer = immunityTimer;
  }

  /**
   * manages the collision between player and other sprites
   * @param sprite first Collidable Sprite object that collides with another sprite
   * @return
   */
  @Override
  public boolean collision(Sprite sprite){
    float dist = PVector.dist(sprite.getPosition(), getPosition());
    if (dist <= (sprite.getSize() / 2) + (getSize() / 2)){
      return true;
    }
    return false;
  }

  /**
   * Draws the harry potter sprite image to the window
   */
  @Override
  public void draw() {
    GameManager.getInstance().window.image(getHarryPotterImage(), getPosition().x - PLAYER_WIDTH/2,
        getPosition().y - PLAYER_HEIGHT/2, PLAYER_WIDTH , PLAYER_HEIGHT);

  }

}
