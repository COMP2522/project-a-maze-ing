package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.enemy.Enemy;
import org.bcit.com2522.project.enemy.spawners.EnemySpawner;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyManager{

  //List of total enemies.
  private ArrayList<Enemy> enemies;
  // List of enemy spawners.
  private ArrayList<EnemySpawner> spawners;
  private Iterator iterator;

  private static EnemyManager instance;

  private EnemyManager() {
    enemies = new ArrayList<>();
    spawners = new ArrayList<>();
    iterator = enemies.iterator();
  }

  public static EnemyManager getInstance() {
    if (instance == null) {
      instance = new EnemyManager();
    }
    return instance;
  }

  /**
   * Constructor to initialize iterator.
   */
  public EnemyManager() {
    iterator = enemies.iterator();
  }

  public void add(Enemy e){
    enemies.add(e);
  }
  public void addSpawner(EnemySpawner s){
    spawners.add(s);
  }

  public void remove(Enemy e){
        e.getSpawner().removeEnemy();
        enemies.remove(e);
  }

  public Enemy get(){
    return (Enemy) iterator.next();
  }

  public void resetIterator(){
    iterator = enemies.iterator();
  }

  public void draw(){
    resetIterator();
    while (iterator.hasNext()){
      Enemy e = (Enemy) iterator.next();
      e.draw();
    }
  }

  public void spawn() {
    iterator = spawners.iterator();
    while (iterator.hasNext()) {
      EnemySpawner s = (EnemySpawner) iterator.next();
      s.spawn();
    }
  }

  public void collision(Sprite s){
    while (iterator.hasNext()){
      Enemy e = (Enemy) iterator.next();
      e.collision(s);
    }
  }

}
