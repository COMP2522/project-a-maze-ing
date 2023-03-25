package org.bcit.com2522.project.interfaces;

import org.bcit.com2522.project.Sprite;

/**
 * Interface for objects that can collide with other objects that implement Collidable.
 */
public interface Collidable {

  /**
   * Given two Collidable objects, checks if they have collided.
   * @param a first Collidable object a
   * @param b second Collidable object
   * @return true if collision has occurred, false otherwise.
   */
  boolean collision(Sprite s);
}