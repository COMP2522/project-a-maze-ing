package org.bcit.com2522.project;

public class Ghost extends Enemy implements Movable{

  private float xPos;
  private float yPos;

  private Player player;
  private float ghostSpeed = 0.3f;

  private static Ghost ghostInstance = null;

  private Ghost(){
// singleton for ghost
  }

  public static Ghost getInstance() {
    if (ghostInstance == null) {
      ghostInstance = new Ghost();
    }
    return ghostInstance;
  }

  /**
   * Update ghost position by checking the position of the player and moving
   * the ghost towards the player.
   */
  public void move() {
    if (xPos <= player.getXpos()) {
      xPos += ghostSpeed;
    } if (yPos <= player.getYpos()) {
      yPos += ghostSpeed;
    } if (xPos > player.getXpos()) {
      xPos -= ghostSpeed;
    } if (yPos > player.getYpos()) {
      yPos -= ghostSpeed;
    }
  }

}
