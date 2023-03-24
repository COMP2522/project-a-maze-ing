package org.bcit.com2522.project;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PVector;

import java.awt.*;
public class Wraith extends Enemy implements Movable{
  //  private static Ghost ghostInstance = null;

  /* Minim object for playing sound */
  Minim minim;

  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  private boolean soundPlaying = false;
  //
  private Player player; //Reference to the player object
  //SoundFile sporadicSound;

  Wraith(PVector position, PVector direction, float size, float speed, Color color, Window window){
    super(position, direction, size, speed, color, window);
//    minim = new Minim(getWindow());
//    sound = minim.loadFile("sound/wraithSound.mp3");
  }


  @Override
  public void move(Player player) {
    int threshold = 700;
    float tolerance = 5f;
    this.player = player;
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    float distance = direction.mag();
    if (distance < threshold) {
      if (Math.abs(player.getPosition().x -getPosition().x) < tolerance
          || Math.abs(player.getPosition().y - getPosition().y) < tolerance) {
        direction.normalize();
        direction.mult(getSpeed());
        setPosition(PVector.add(getPosition(), direction));
        if(!soundPlaying){
          //sound.play();
          soundPlaying = true;
        }
      } else if (Math.abs(player.getPosition().x -getPosition().x) > tolerance
          || Math.abs(player.getPosition().y - getPosition().y) > tolerance){
        soundPlaying = false;
        //sound.pause();

      }
    } else {

    }
  }



  public void update() {
    if (player != null) {
      move(player);
    }
  }

}
