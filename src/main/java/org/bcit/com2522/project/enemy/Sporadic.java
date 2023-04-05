package org.bcit.com2522.project.enemy;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.interfaces.Drawable;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
public class Sporadic extends Enemy implements Movable, Drawable {

  /* Minim object for playing sound */
  Minim minim;

  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  /* Image to represent sporadic enemy type. */
  PImage sporadicImage;

  /* Hitbox size of sporadic enemy type. */
  public static final int SPORADIC_SIZE = 35;

  /* Height of sporadic image. */
  public static final int SPORADIC_HEIGHT = 32;

  /* Width of sporadic image. */
  public static final int SPORADIC_WIDTH = 43;

  /* Speed of sporadic when player gets close. */
  public static final int SPORADIC_SPEED = 10;

  /* Colour of hitbox to distinguish from other enemies during testing. */
  public static final Color SPORADIC_COLOR = new Color(255, 0, 0);

  /* Directory for the sporadic image that represents the sporadic. */
  public static final String SPORADIC_IMAGE_PATH = "Data/sporadicSleep.png";

  /**
   * Constructs a sporadic enemy type, sets sounds, and images
   * @param position
   * @param direction
   * @param size
   * @param speed
   * @param color
   * @param imagePath
   */
  public Sporadic(PVector position, PVector direction, float size, float speed, Color color, String imagePath){
    super(position, direction, size, speed, color);
    this.sporadicImage = GameManager.getInstance().window.loadImage(imagePath);
    minim = new Minim(GameManager.getInstance().window);
    sound = minim.loadFile("sound/sporadicSound.mp3");
  }

  /**
   * sets the image of the sporadic enemy type
   * @param image
   */
  public void setImage(PImage image){
    this.sporadicImage = image;
  }

  /**
   * Sets the movement of the sporadic enemy type based on player
   * position. If player gets close, sporadic movement becomes random.
   */
  @Override
  public void move() {
    int threshold = 200;
    Player player = Player.getInstance();
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    float distance = direction.mag();
    if (distance < threshold) {

      PVector randomDirection = PVector.random2D();
      randomDirection.mult(getSpeed());
      setPosition(PVector.add(getPosition(), randomDirection));
      if(distance > player.PLAYER_HEIGHT*3/2){
        sound.play();
        this.setImage(GameManager.getInstance().window.loadImage("Data/sporadicAwake.png"));
      } else {
        sound.pause();
      }
    } else if (distance >= threshold) {
      sound.pause();
    }
  }

  /**
   * Draws the image that represents the sporadic enemy type.
   */
  @Override
  public void draw() {
    GameManager.getInstance().window.image(sporadicImage, getPosition().x - SPORADIC_WIDTH / 2,
        getPosition().y - SPORADIC_HEIGHT / 2 , SPORADIC_WIDTH , SPORADIC_HEIGHT);
  }

  /**
   * Updates enemy sporadic movement.
   */
  public void update() {
    move();
  }

}

