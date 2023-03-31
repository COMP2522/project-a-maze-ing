package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.enemy.spawners.WraithSpawner;
import processing.core.PVector;

public class WraithTile extends EmptyPath{

  public WraithTile(PVector pos, Window w) {
    super(pos, w);
    EnemyManager.getInstance().addSpawner(new WraithSpawner(pos, w));
  }

}
