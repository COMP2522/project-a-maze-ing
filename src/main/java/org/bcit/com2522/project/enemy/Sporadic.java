package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.enemy.Enemy;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PVector;

import java.awt.*;
public class Sporadic extends Enemy implements Movable {
//  private static Ghost ghostInstance = null;
//
  private Player player; //Reference to the player object
  //SoundFile sporadicSound;

  Sporadic(PVector position, PVector direction, float size, float speed, Color color, Window window){
    super(position, direction, size, speed, color, window);
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
      //sporadicSound.play();
    } else {
//      direction.normalize();
//      direction.mult(getSpeed());
//      setPosition(PVector.add(getPosition(), direction));
    }



//    this.player = player;
//    PVector direction = PVector.sub(player.getPosition(), getPosition());
//    direction.normalize();
//    direction.mult(getSpeed());
//    setPosition(PVector.add(getPosition(), direction));


//    The following method helps you make a friend instead
//    int threshold = 200;
//    this.player = player;
//    PVector direction = PVector.sub(player.getPosition(), getPosition());
//    float distance = direction.mag();
//    if (distance < threshold) {
//      PVector randomDirection = PVector.random2D();
//      randomDirection.mult(getSpeed());
//      setPosition(PVector.add(getPosition(), randomDirection));
//    } else {
//      direction.normalize();
//      direction.mult(getSpeed());
//      setPosition(PVector.add(getPosition(), direction));
//    }
  }

  public void update() {
    if (player != null) {
      move(player);
    }
  }

}

