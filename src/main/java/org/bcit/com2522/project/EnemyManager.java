package org.bcit.com2522.project;

import org.bcit.com2522.project.enemy.Enemy;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyManager{
  ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  Iterator iterator = enemies.iterator();

  public void add(Enemy e){
    enemies.add(e);
  }

  public void remove(Enemy e){
    enemies.remove(e);
  }

  public Enemy get(){
    return (Enemy) iterator.next();
  }

  public void resetIterator(){
    iterator = enemies.iterator();
  }

  public void draw(){
    while (iterator.hasNext()){
      Enemy e = (Enemy) iterator.next();
      e.draw();
    }
  }

  public void collision(Sprite s){
    while (iterator.hasNext()){
      Enemy e = (Enemy) iterator.next();
      e.collision(s);
    }
  }

}
