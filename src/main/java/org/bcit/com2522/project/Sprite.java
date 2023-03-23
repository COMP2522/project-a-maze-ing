package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

public class Sprite {
  private PVector position;
  private PVector direction;
  private float size;
  private float speed;
  private Color color;
  //private java.awt.Window window;
  private Window window;
  public CompoundGraphic graphic;


  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
    this.graphic = new CompoundGraphic();
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
  //  public void bounce() {
//    if (this.position.x <= 0 ||
//        this.position.x >= window.width ||
//        this.position.y <= 0 ||
//        this.position.y >= window.height) {
//      this.direction.rotate(window.HALF_PI);
//    }
//  }

//  public PVector getDirection() {
//    return direction.copy();
//  }
//
//  public PVector getPosition() {
//    return position.copy();
//  }
//
  public void update() {
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  public void draw() {
    graphic.draw(this, window);
  }
//
//  public float getSize() {
//    return size;
//  }
//  @Override
//  public boolean collided(Collidable b) {
//
//    try {
//      Sprite other = (Sprite) b;
//      float distance = PVector.dist(this.getPosition(), other.getPosition());
//      if (distance <= (this.getSize() + other.getSize())) {
//        return true;
//      }
//      return false;
//    } catch (ClassCastException e) {
//      throw new ClassCastException("this don't work");
//    }
//
//  }

//
//  public void setDirection(PVector direction) {
//    this.direction = direction;
//  }
//
//  @Override
//  public int compareTo(Object o) {
//    if (o == null) {
//      throw new NullPointerException("Exception thrown: object is null");
//    }
//
//    try {
//      Sprite other = (Sprite) o;
//
//      if (other.equals(this)) {
//        return 0;
//      } else if (other.getSize() > this.size) {
//        return -1;
//      } else {
//        return 1;
//      }
//
//    } catch (ClassCastException e) {
//      throw new ClassCastException("Invalid type");
//    }
//  }
//
//  @Override
//  public boolean equals(Object o){
//    try {
//      Sprite other = (Sprite) o;
//      return (this.getSize() == other.getSize());
//    } catch (ClassCastException e) {
//      throw new ClassCastException("Invalid type");
//    }
//  }
}
