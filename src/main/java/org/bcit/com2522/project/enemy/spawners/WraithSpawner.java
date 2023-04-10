package org.bcit.com2522.project.enemy.spawners;

import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.enemy.Wraith;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import processing.core.PVector;

public class WraithSpawner extends EnemySpawner {
  private static final int MAX_WRAITH_SPAWNS = 3;

  public WraithSpawner(PVector pos) {
    super(MAX_WRAITH_SPAWNS, pos);
  }

  @Override
  public void spawn() {
    if (currEnemies < maxEnemies) {
      float x = posRand.nextInt(Tile.TILE_SIZE);
      float y = posRand.nextInt(Tile.TILE_SIZE);
      Wraith en = new Wraith(
          position.copy().add(x, y),
          new PVector(0, 0),
          Wraith.WRAITH_SIZE,
          Wraith.WRAITH_SPEED,
          Wraith.WRAITH_COLOR,
          Wraith.WRAITH_IMAGE_PATH);
      EnemyManager.getInstance()
          .add(en);
      currEnemies++;
    }
  }
}
