package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.enemy.spawners.EnemySpawner;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PVector;

import java.awt.*;

/**
 * The enemy class instantiates an enemy that is able
 * to collide with other sprites and has a get method for
 * enemy spawners. It extends Sprite and implements
 * the Collidable interface.
 */
abstract public class Enemy extends Sprite implements Movable {

  /* EnemySpawner variable for future use in case we want to destroy enemies. */
  private EnemySpawner spawner;

  /**
   * A constructor to create an enemy type that uses the parameters from Sprite
   * @param position
   * @param direction
   * @param size
   * @param speed
   * @param color
   */
  public Enemy(PVector position, PVector direction, float size, float speed, Color color) {
    super(position, direction, size, speed, color);
  }

  /**
   * gets the distance between an enemy and any other sprite and determines
   * whether the two have collided
   * @param sprite
   * @return boolean
   */
  @Override
  public boolean collision(Sprite sprite) {
    float dist = PVector.dist(sprite.getPosition(), getPosition());
    if (dist <= (sprite.getSize() / 2) + (getSize() / 2)){
      return true;
    }
    return false;
  }

  /**
   * gets an EnemySpawner object and is used in the EnemyManager
   * class to remove a spawner if needed.
   * @return spawner
   */
  public EnemySpawner getSpawner() {
    return spawner;
  }

}
