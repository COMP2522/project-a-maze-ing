package org.bcit.com2522.project.enemy.spawners;

import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.enemy.Sporadic;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import processing.core.PVector;

public class SporadicSpawner extends EnemySpawner{

  private static final int MAX_SPORADIC_SPAWNS = 2;
  public SporadicSpawner(PVector pos) {
    super(MAX_SPORADIC_SPAWNS, pos);
  }

  @Override
  public void spawn() {
    if (currEnemies < maxEnemies) {
      float x = posRand.nextInt(Tile.TILE_SIZE);
      float y = posRand.nextInt(Tile.TILE_SIZE);
      Sporadic sporadic = new Sporadic(
          position.copy().add(x, y),
          new PVector(0, 0),
          Sporadic.SPORADIC_SIZE,
          Sporadic.SPORADIC_SPEED,
          Sporadic.SPORADIC_COLOR,
          Sporadic.SPORADIC_IMAGE_PATH);
      EnemyManager.getInstance()
          .add(sporadic);
      currEnemies++;
    }
  }
}
