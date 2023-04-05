package org.bcit.com2522.project;

import org.bcit.com2522.project.interfaces.Collidable;
import org.bcit.com2522.project.interfaces.Drawable;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import processing.core.PVector;

import java.awt.*;

public abstract class Sprite implements Drawable, Collidable {
  private PVector position;
  private PVector direction;
  private float size;
  private float speed;
  private Color color;
  //private java.awt.Window window;

  public Sprite() {

  }

  public void setSize(float size) {
    this.size = size;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }


  public Sprite(PVector position, PVector direction, float size, float speed, Color color) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.color = color;
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  public PVector getDirection() {
    return direction;
  }

  public PVector getPosition() {
    return position;
  }

  public float getSpeed() {
    return speed;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

  public float getSize() {
    return size;
  }

  public void update() {
    if (!(LabyrinthManager.getInstance().collision(this))){
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
    }
  }

  public void draw() {
    GameManager.getInstance().window.pushStyle();
    GameManager.getInstance().window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    GameManager.getInstance().window.ellipse(this.position.x, this.position.y, size, size);
    GameManager.getInstance().window.popStyle();
  }

  @Override
  public abstract boolean collision(Sprite s);

}


