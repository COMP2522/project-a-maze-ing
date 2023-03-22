package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite implements Movable{

  public Enemy(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
  }

//  /**
//   * Draws a circle onscreen at x, y, circle.
//   *
//   * @param x position of centre of circle
//   * @param y position of centre of circle
//   * @param diameter width of circle
//   */
//  public void drawEnemy(float x, float y, float diameter) {
//    window.ellipse(x, y, diameter, diameter);
//  }
//
//  /**
//   * Draws the Enemy.
//   */
//  public void draw() {
//    this.drawEnemy(this.xpos, this.ypos, this.diameter);
//  }
//
//  public float getYpos() {
//    return ypos;
//  }
//
//  public float getXpos() {
//    return xpos;
//  }
//
//  public float getDiameter() {
//    return diameter;
//  }
//
//  public float getVx() {
//    return vx;
//  }
//
//  public float getVy() {
//    return vy;
//  }
//
//  //public float getGhostSpeed(){return ghostSpeed;}
//
//  public void setVy(float vy) {
//    this.vy = vy;
//  }
//
//  public void setVx(float vx) {
//    this.vx = vx;
//  }
//
//
//
//  /**
//   * Calculates collisions between enemy and player.
//   */
//  public void collide() {
//    float dx = player.getXpos() - xpos;
//    float dy = player.getYpos() - ypos;
//    float distance = sqrt(dx * dx + dy * dy);
//    float minDist = player.getDiameter() / 2 + diameter / 2;
//    if (distance < minDist) {
//      //Call the fucking die method lmao
//    }
//  }
//
  @Override
  public void move(Player player) {
    //Sub classes of enemy all move differently
  }
}
