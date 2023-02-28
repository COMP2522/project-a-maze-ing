package org.bcit.com2522.project;

/**
 * Player. Defines a player character controlled by the user.
 */
public class Player extends Sprite{

  private float xpos;
  private float ypos;

  public Player(float xpos, float ypos) {
    this.xpos = xpos;
    this.ypos = ypos;
  }

  public float getXpos() {
    return xpos;
  }

  public float getYpos() {
    return xpos;
  }
}
