package org.bcit.com2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

/**
 * Player. Defines a player character controlled by the user.
 */
public class Player extends Sprite {

  private PImage playerImage;

  public Player(PVector position, PVector direction, float size, float speed, Color color, Window window, PImage playerImage) {
    super(position, direction, size, speed, color, window);
    this.playerImage = playerImage;
  }

//  @Override
//  public void draw() {
//    if (playerImage == null) {
//      return;
//    }
//
//    window.pushMatrix();
//    window.translate(getPosition().x, getPosition().y);
//    window.image(playerImage, playerImage.width / 2, -playerImage.height / 2, playerImage.width / 2, playerImage.height / 2);
//    window.popMatrix();
//
//  }
}
