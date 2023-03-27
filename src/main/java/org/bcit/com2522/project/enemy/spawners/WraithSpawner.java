package org.bcit.com2522.project.enemy.spawners;

import org.bcit.com2522.project.EnemyManager;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.enemy.Wraith;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import processing.core.PVector;

public class WraithSpawner extends EnemySpawner {
  private static final int MAX_WRAITH_SPAWNS = 3;

  public WraithSpawner(PVector pos, Window w) {
    super(MAX_WRAITH_SPAWNS, pos, w);
  }

  @Override
  public void spawn() {
    if (currEnemies < maxEnemies) {
      float x = posRand.nextInt(Tile.TILE_SIZE) + (Tile.TILE_SIZE / 2);
      float y = posRand.nextInt(Tile.TILE_SIZE) + (Tile.TILE_SIZE / 2);
      Wraith en = new Wraith(
          position.copy().add(x, y),
          new PVector(0, 0),
          Wraith.WRAITH_SIZE,
          Wraith.WRAITH_SPEED,
          Wraith.WRAITH_COLOR,
          window,
          Wraith.WRAITH_IMAGE_PATH);
      EnemyManager.getInstance()
          .add(en);
      currEnemies++;
      System.out.println("Spawn called at tile" + position.x + ", " + position.y);
      System.out.println("enemy spawned : " + en.getPosition().x + ", " + en.getPosition().y);

    }
  }
}
