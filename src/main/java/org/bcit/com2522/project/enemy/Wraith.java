package org.bcit.com2522.project.enemy;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Drawable;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

public class Wraith extends Enemy implements Movable, Drawable {
  public static final float WRAITH_SPEED = 11.5f;

  public static final Color WRAITH_COLOR = new Color(0, 0, 255);

  public static final String WRAITH_IMAGE_PATH = "Data/Wraithright.png";
  //  private static Ghost ghostInstance = null;

  /* Minim object for playing sound */
  Minim minim;

  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  private boolean soundPlaying = false;
  PImage wraithImage;
  //SoundFile sporadicSound;

  /* Hitbox size in pixels of wraith enemy type.*/
  public static final int WRAITH_SIZE = 30;

  public static final int WRAITH_LENGTH = 50;


  public Wraith(PVector position, PVector direction, float size, float speed, Color color, Window window, String imagePath){
    super(position, direction, size, speed, color, window);
    this.wraithImage = window.loadImage(imagePath);
    minim = new Minim(getWindow());
    sound = minim.loadFile("sound/wraithSound.mp3");
  }

  public PImage getImage(){
    return wraithImage;
  }

  public void setImage(PImage image){
    this.wraithImage = image;
  }


  @Override
  public void move() {
    int threshold = 700;
    float tolerance = 15f;
    Player player = Player.getInstance();
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    float distance = direction.mag();
    if (distance < threshold) {
      if (Math.abs(player.getPosition().x -getPosition().x) < tolerance
          || Math.abs(player.getPosition().y - getPosition().y) < tolerance) {
        direction.normalize();
        direction.mult(getSpeed());
        setPosition(PVector.add(getPosition(), direction));
        if(distance > player.PLAYER_HEIGHT){
          sound.play();
          soundPlaying = true;
        } else {
          soundPlaying = false;
          sound.pause();
        }
        if (direction.x < 0){
          this.setImage(player.getWindow().loadImage("Data/Wraithleft.png"));
        } else if (direction.x > 0){
          this.setImage(player.getWindow().loadImage("Data/Wraithright.png"));
        }
      } else if (Math.abs(player.getPosition().x -getPosition().x) > tolerance
          || Math.abs(player.getPosition().y - getPosition().y) > tolerance
      || distance >= threshold/2){
        soundPlaying = false;
        sound.pause();
      }
    }
  }

  @Override
  public void draw() {
    EnemyManager.getInstance().getWindow().image(wraithImage, getPosition().x - WRAITH_LENGTH / 2,
          getPosition().y - WRAITH_LENGTH / 2 , WRAITH_LENGTH , WRAITH_LENGTH);
  }



  public void update() {
      move();
  }

}
