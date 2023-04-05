package org.bcit.com2522.project.enemy.spawners;

import org.bcit.com2522.project.Window;
import processing.core.PVector;

import java.util.Random;

/**
 * Spawns a variable amount of enemies at a specific tile
 * Note: for code review it may seem a little redundant, but for future implementation of respawning, these
 * will be very useful
 */
public abstract class EnemySpawner {

  protected Window window;

  // the tile that the spawner is associated with.
  protected PVector position;

  // the maximum number of enemies this spawner can spawn.
  protected int maxEnemies;

  // the current number of enemies this spawner has spawned.
  protected int currEnemies;

  // randomizer for spawning in random location in position tile.
  protected static final Random posRand = new Random();

  public EnemySpawner(int max, PVector pos) {
    maxEnemies = max;
    currEnemies = 0;
    position = pos;
  }



  public void removeEnemy() {
    currEnemies--;
  }

  /**
   * spawns an enemy around the position of the spawner.
   */
  public abstract void spawn();


}
