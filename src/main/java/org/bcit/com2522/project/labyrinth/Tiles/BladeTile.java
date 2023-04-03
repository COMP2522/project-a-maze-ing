package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.traps.Blade;
import org.bcit.com2522.project.traps.TrapManager;
import processing.core.PVector;

import java.awt.*;

public class BladeTile extends TrapTile {

    public BladeTile(PVector pos, Window w) {
        super(pos, w);
        spawnTraps(TRAP_RANDOMIZER.nextInt(3) + 1, pos);

    }

  @Override
  protected void spawnTraps(int numTraps, PVector pos) {
    float x, y, startX, startY, endX, endY;

    for (int i = 0; i < numTraps; i++) {
      x = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);
      y = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);

      startX = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);
      endX = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);
      startY = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);
      endY = TRAP_RANDOMIZER.nextInt(Tile.TILE_SIZE);

      TrapManager.getInstance()
          .addTrap(new Blade(pos.copy().add(x, y),
              new PVector(0, 0),
              30f, 0, Color.red, this.window, 0.05f,
              2, pos.copy().add(startX, startY),
              pos.copy().add(endX, endY)));

    }
    }
  }
