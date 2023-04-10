package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.enemy.spawners.WraithSpawner;
import processing.core.PVector;

public class WraithTile extends EmptyPathTile {

  public WraithTile(PVector pos) {
    super(pos);
    EnemyManager.getInstance().addSpawner(new WraithSpawner(pos));
  }

}
