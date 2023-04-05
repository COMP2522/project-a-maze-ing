package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.enemy.spawners.EnemySpawner;
import org.bcit.com2522.project.interfaces.Collidable;
import org.bcit.com2522.project.interfaces.Movable;
import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite implements Movable, Collidable {

  private EnemySpawner spawner; // for future use in case we want to destroy enemies

  public Enemy(PVector position, PVector direction, float size, float speed, Color color) {
    super(position, direction, size, speed, color);

  }

  @Override
  public boolean collision(Sprite sprite) {
    float dist = PVector.dist(sprite.getPosition(), getPosition());
    if (dist <= (sprite.getSize() / 2) + (getSize() / 2)){
      return true;
    }
    return false;
  }

  public EnemySpawner getSpawner() {
    return spawner;
  }

  @Override
  public void move() {
    //Sub classes of enemy all move differently
  }
}
