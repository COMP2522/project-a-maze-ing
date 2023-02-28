package org.bcit.com2522.project;

import static processing.core.PApplet.sqrt;

public class Enemy extends Sprite implements Hostile, Collidable, Movable{
  private float xpos;
  private float ypos;
  private final float diameter;
  private float vx = 0;
  private float vy = 0;
  private final int id;
  private Enemy[] enemies;
  private Window window;
  private Player player;


  Enemy(float x, float y, float hitbox, int id, Enemy[] enemies, Window scene) {
    this.xpos = x;
    this.ypos = y;
    this.diameter = hitbox;
    this.id = id;
    this.enemies = enemies;
    this.window = scene;
  }

  /**
   * Draws a circle onscreen at x, y, circle.
   *
   * @param x position of centre of circle
   * @param y position of centre of circle
   * @param diameter width of circle
   */
  public void drawEnemy(float x, float y, float diameter) {
    window.ellipse(x, y, diameter, diameter);
  }

  /**
   * Draws the Enemy.
   */
  public void draw() {
    this.drawEnemy(this.xpos, this.ypos, this.diameter);
  }

  public float getYpos() {
    return ypos;
  }

  public float getXpos() {
    return xpos;
  }

  public float getDiameter() {
    return diameter;
  }

  public float getVx() {
    return vx;
  }

  public float getVy() {
    return vy;
  }

  //public float getGhostSpeed(){return ghostSpeed;}

  public void setVy(float vy) {
    this.vy = vy;
  }

  public void setVx(float vx) {
    this.vx = vx;
  }



  /**
   * Calculates collisions between enemy and player.
   */
  public void collide() {
    float dx = player.getXpos() - xpos;
    float dy = player.getYpos() - ypos;
    float distance = sqrt(dx * dx + dy * dy);
    float minDist = player.getDiameter() / 2 + diameter / 2;
    if (distance < minDist) {
      //Call the fucking die method lmao
    }
  }

  @Override
  public void move() {
    //Sub classes of enemy all move differently
  }
}
