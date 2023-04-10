package org.bcit.com2522.project;

import org.bcit.com2522.project.interfaces.Collidable;
import org.bcit.com2522.project.interfaces.Drawable;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import processing.core.PVector;

import java.awt.*;

/**

 An abstract sprite class for game objects such as enemies and player
 that are drawable and can be collided with.
 */
public abstract class Sprite implements Drawable, Collidable{

  // Private instance variables
  private PVector position;
  private PVector direction;
  private float size;
  private float speed;
  private Color color;

  /**
   Default constructor for the Sprite class.
   */
  public Sprite() {
  }

  /**
   Constructor for the Sprite class.
   @param position the position of the object
   @param direction the direction of the object
   @param size the size of the object
   @param speed the speed of the object
   @param color the color of the object
   */
  public Sprite(PVector position, PVector direction, float size, float speed, Color color) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.color = color;
  }

  /**
   Setter method for the size of the Sprite object.
   @param size the size to set for the object
   */
  public void setSize(float size) {
    this.size = size;
  }

  /**
   Setter method for the speed of the Sprite object.
   @param speed the speed to set for the object
   */
  public void setSpeed(float speed) {
    this.speed = speed;
  }
  /**
   Getter method for the color of the Sprite object.
   @return the color of the object
   */
  public Color getColor() {
    return color;
  }

  /**
   Setter method for the color of the Sprite object.
   @param color the color to set for the object
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   Setter method for the direction of the Sprite object.
   @param direction the direction to set for the object
   */
  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  /**
   Getter method for the direction of the Sprite object.
   @return the direction of the object
   */
  public PVector getDirection() {
    return direction;
  }

  /**
   Getter method for the position of the Sprite object.
   @return the position of the object
   */
  public PVector getPosition() {
    return position;
  }

  /**
   Getter method for the speed of the Sprite object.
   @return the speed of the object
   */
  public float getSpeed() {
    return speed;
  }

  /**
   Setter method for the position of the Sprite object.
   @param position the position to set for the object
   */
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   Getter method for the size of the Sprite object.
   @return the size of the object
   */
  public float getSize() {
    return size;
  }

  /**
   Updates the position of the Sprite object based on its direction and speed.
   */
  public void update() {
    if (!(LabyrinthManager.getInstance().collision(this))){
      this.position = this.getPosition().add(this.direction.copy().mult(speed));
    }
  }

  /**
   * Draws hitbox of the Sprite to the window
   */
  public void draw() {
    GameManager.getInstance().window.pushStyle();
    GameManager.getInstance().window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    GameManager.getInstance().window.ellipse(this.position.x, this.position.y, size, size);
    GameManager.getInstance().window.popStyle();
  }

  /**
   * Abstract class to implement in subclasses
   * @param sprite first Collidable Sprite object that collides with another sprite
   * @return
   */
  @Override
  public abstract boolean collision(Sprite sprite);

}


