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
//  private static Ghost ghostInstance = null;
//

  /* Minim object for playing sound */
  Minim minim;

  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  private boolean soundPlaying = false;

  PImage sporadicImage;

  private Player player; //Reference to the player object
  //SoundFile sporadicSound;

  /* Hitbox size in pixels of sporadic enemy type.*/
  public static final int SPORADIC_SIZE = 35;

  public static final int SPORADIC_HEIGHT = 32;

  public static final int SPORADIC_WIDTH = 43;

  public static final int SPORADIC_SPEED = 10;

  public static final Color SPORADIC_COLOR = new Color(255, 0, 0);

  public static final String SPORADIC_IMAGE_PATH = "Data/sporadicSleep.png";

  public Sporadic(PVector position, PVector direction, float size, float speed, Color color, String imagePath){
    super(position, direction, size, speed, color);
    this.sporadicImage = GameManager.getInstance().window.loadImage(imagePath);
    minim = new Minim(getWindow());
    sound = minim.loadFile("sound/sporadicSound.mp3");
  }

  public PImage getImage(){
    return sporadicImage;
  }

  public void setImage(PImage image){
    this.sporadicImage = image;
  }


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
        soundPlaying = true;
        this.setImage(GameManager.getInstance().window.loadImage("Data/sporadicAwake.png"));
      } else {
        soundPlaying = false;
        sound.pause();
      }
    } else if (distance >= threshold) {
      soundPlaying = false;
      sound.pause();
    }
  }

  @Override
  public void draw() {
    GameManager.getInstance().window.image(sporadicImage, getPosition().x - SPORADIC_WIDTH / 2,
        getPosition().y - SPORADIC_HEIGHT / 2 , SPORADIC_WIDTH , SPORADIC_HEIGHT);
  }

  public void update() {
    move();
  }

}

