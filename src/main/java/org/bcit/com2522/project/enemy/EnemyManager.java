package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.enemy.spawners.EnemySpawner;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyManager{

  //List of total enemies.
  private ArrayList<Enemy> enemies;
  // List of enemy spawners.
  private ArrayList<EnemySpawner> spawners;
  private Iterator iterator;



  private Window window;

  private static EnemyManager instance;

  private EnemyManager(Window w) {
    enemies = new ArrayList<>();
    spawners = new ArrayList<>();
    iterator = enemies.iterator();
    window = w;
  }

  public static EnemyManager getInstance(Window w) {
    if (instance == null) {
      instance = new EnemyManager(w);
    }
    return instance;
  }


  public static EnemyManager getInstance() {

    return instance;
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
      e.update();
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
    Player player = Player.getInstance();
    resetIterator();

    while (iterator.hasNext()){
      Enemy e = (Enemy) iterator.next();
      if (e.collision(player)) {
        player.setAlive(false);
      }
    }
  }

  public Window getWindow() {
    return window;
  }

}
