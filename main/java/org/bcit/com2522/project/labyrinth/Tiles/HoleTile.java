package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.traps.Hole;
import org.bcit.com2522.project.traps.TrapManager;
import processing.core.PVector;

import java.awt.*;

public class HoleTile extends TrapTile {

    public HoleTile(PVector pos) {
        super(pos);
        spawnTraps(TRAP_RANDOMIZER.nextInt(3) + 1, pos);
    }

  @Override
  protected void spawnTraps(int numTraps, PVector pos) {
    float x, y;
    for (int i = 0; i < numTraps; i++) {
      x = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);
      y = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);
      TrapManager.getInstance().addTrap(
          new Hole(pos.copy().add(x, y),
              new PVector(0, 0), 50, 0, Color.black, Hole.HOLE_PATH));
    }

  }

}
