package org.bcit.com2522.project.enemy;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.Player;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

/**
 * The Wraith class is an enemy type with a specific movement
 * and look. The wraith will rapidly move towards the player
 * and growl when the player is on the same x-axis or y-axis
 * within a specific tolerence thershold of their hitboxes.
 *
 */
public class Wraith extends Enemy {

  /* Speed of the wraith when locked onto the player.*/
  public static final float WRAITH_SPEED = 11.5f;

  /* Colour of the hitbox of the wraith to distinguish during testing.*/
  public static final Color WRAITH_COLOR = new Color(0, 0, 255);

  /*Directory of the wraith image in files. */
  public static final String WRAITH_IMAGE_PATH = "Data/Wraithright.png";

  /* Minim object for playing sound */
  public Minim minim;

  /* AudioPlayer object for sound file */
  public AudioPlayer sound;

  /* Image of the wraith. */
  public PImage wraithImage;

  /* Hitbox size in pixels of wraith enemy type.*/
  public static final int WRAITH_SIZE = 30;

  /* Image dimensions for wraith image. */
  public static final int WRAITH_LENGTH = 50;

  /**
   * Creates a wraith object which loads in the image of the wraith facing
   * right initially and the sound associated with each wraith
   * @param position
   * @param direction
   * @param size
   * @param speed
   * @param color
   * @param imagePath
   */
  public Wraith(PVector position, PVector direction, float size, float speed, Color color, String imagePath){
    super(position, direction, size, speed, color);
    this.wraithImage = GameManager.getInstance().window.loadImage(imagePath);
    minim = new Minim(GameManager.getInstance().window);
    sound = minim.loadFile("sound/wraithSound.mp3");
  }

  /**
   * Sets the image of the wraith
   * @param image
   */
  public void setImage(PImage image){
    this.wraithImage = image;
  }

  /**
   * Moves the wraith when player is within the
   * threshold of wraith's line of sight on x-axis
   * or y-axis. Sound plays and wraith will move towards
   * player if conditions are met that wraith is in a certain
   * radius as well. Points wraith image towards wherever player is
   */
  @Override
  public void move() {

    int threshold = 700;    //distance before wraith moves
    float tolerance = 15f;  //threshold of player and wraith hitboxes on axis to trigger wraith movement

    Player player = Player.getInstance();
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    float distance = direction.mag();

    //Check if player is within distance
    if (distance < threshold) {

      //Check if player is on same x or y-axis
      if (Math.abs(player.getPosition().x -getPosition().x) < tolerance
          || Math.abs(player.getPosition().y - getPosition().y) < tolerance) {

        direction.normalize();
        direction.mult(getSpeed());
        setPosition(PVector.add(getPosition(), direction));

        //Condition to stop sound playing if player is in death range of wraith
        if(distance > player.PLAYER_HEIGHT*2){
          sound.play();
        } else {
          sound.pause();
        }

        //Sets image directions
        if (direction.x < 0){
          this.setImage(GameManager.getInstance().window.loadImage("Data/Wraithleft.png"));
        } else if (direction.x > 0){
          this.setImage(GameManager.getInstance().window.loadImage("Data/Wraithright.png"));
        }

      } else if (Math.abs(player.getPosition().x -getPosition().x) > tolerance
          || Math.abs(player.getPosition().y - getPosition().y) > tolerance
          || distance >= threshold/2){
        sound.pause();
      }
    }
  }

  /**
   * Draws the image of the wraith in the window
   */
  @Override
  public void draw() {
    GameManager.getInstance().window.image(wraithImage, getPosition().x - WRAITH_LENGTH / 2,
          getPosition().y - WRAITH_LENGTH / 2 , WRAITH_LENGTH , WRAITH_LENGTH);
  }


  /**
   * Updates the position of the wraith
   */
  public void update() {
      move();
  }

}
