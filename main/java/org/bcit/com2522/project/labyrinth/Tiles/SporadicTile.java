package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.enemy.spawners.SporadicSpawner;
import processing.core.PVector;

public class SporadicTile extends EmptyPathTile{


  public SporadicTile(PVector pos) {
    super(pos);
    EnemyManager.getInstance().addSpawner(new SporadicSpawner(pos));
  }
}
