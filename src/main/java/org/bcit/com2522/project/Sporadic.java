package org.bcit.com2522.project;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PVector;

import java.awt.*;
public class Sporadic extends Enemy implements Movable{
//  private static Ghost ghostInstance = null;
//

  /* Minim object for playing sound */
  Minim minim;

  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  private boolean soundPlaying = false;

  private Player player; //Reference to the player object
  //SoundFile sporadicSound;

  Sporadic(PVector position, PVector direction, float size, float speed, Color color, Window window){
    super(position, direction, size, speed, color, window);
//    minim = new Minim(getWindow());
//    sound = minim.loadFile("sound/sporadicSound.wav");

  }


  @Override
  public void move(Player player) {
    int threshold = 200;
    this.player = player;
    PVector direction = PVector.sub(player.getPosition(), getPosition());
    float distance = direction.mag();
    if (distance < threshold) {

      PVector randomDirection = PVector.random2D();
      randomDirection.mult(getSpeed());
      setPosition(PVector.add(getPosition(), randomDirection));
      if(!soundPlaying){
        //sound.play();
        soundPlaying = true;
      }
    } else if (distance >= threshold) {
      soundPlaying = false;
      //sound.pause();
    }
//Do nothing
  }

  public void update() {
    if (player != null) {
      move(player);
    }
  }

}

