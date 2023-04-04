package org.bcit.com2522.project.enemy;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.enemy.spawners.EnemySpawner;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The EnemyManager class manages all the enemy types and their spawners
 * in lists and iterates through them. Ghost spawn methods are unique in this
 * class since the ghost is a singleton.
 */
public class EnemyManager{

  /*List of total enemies.*/
  private ArrayList<Enemy> enemies;

  /* List of enemy spawners.*/
  private ArrayList<EnemySpawner> spawners;

  /* Iterator object to iterate over existing enemies.*/
  private Iterator iterator;



  private Window window;

  private static EnemyManager instance;

  private EnemyManager(Window scene) {
    enemies = new ArrayList<>();
    spawners = new ArrayList<>();
    iterator = enemies.iterator();
    window = scene;

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

  /**
   * adds the given enemy to the manager.
   * @param e the enemy to add.
   */
  public void add(Enemy e){
    enemies.add(e);
  }

  /**
   * adds the given spawner to the manager.
   * @param s the spawner to add
   */
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

  /**
   * Generates a new iterator at start of enemies.
   */
  public void resetIterator(){
    iterator = enemies.iterator();
  }

  /**
   * Draws and updates all enemies.
   */
  public void draw(){
    resetIterator();
    while (iterator.hasNext()){
      Enemy e = (Enemy) iterator.next();
      e.draw();
      e.update();
    }
  }

  /**
   * Iterates over all spawners and calls the spawn function on them.
   */
  public void spawn() {
    iterator = spawners.iterator();
    while (iterator.hasNext()) {
      EnemySpawner s = (EnemySpawner) iterator.next();
      s.spawn();
    }
  }

  /**
   * spawns the ghost
   */
  public void spawnGhost(){
    Player player = Player.getInstance();
    Ghost ghost = Ghost.getInstance(
        new PVector(player.getPosition().x + Ghost.GHOST_START,
            player.getPosition().y + Ghost.GHOST_START),
        new PVector(0,1),
        Ghost.GHOST_SIZE,
        2,
        new Color(255,255,255),
        EnemyManager.getInstance().getWindow(), "Data/ghostRight.png");
    enemies.add(ghost);
  }

  public void makeHyperGhost(Player player){
    Ghost ghost = Ghost.getInstance(new PVector(player.getPosition().x + Ghost.GHOST_START,
            player.getPosition().y + Ghost.GHOST_START),
        new PVector(0,1),
        Ghost.GHOST_SIZE,
        2,
        new Color(255,255,255),
        EnemyManager.getInstance().getWindow(), "Data/ghostRight.png");
      ghost.becomeHyper();
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

  /**
   * Removes all enemies and spawners from the manager.
   */
  public void clearEnemies() {
    enemies = new ArrayList<>();
    spawners = new ArrayList<>();
  }

  public Window getWindow() {
    return window;
  }

}
