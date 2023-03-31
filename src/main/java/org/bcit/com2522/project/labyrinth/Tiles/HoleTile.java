package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.traps.Hole;
import org.bcit.com2522.project.traps.TrapManager;
import processing.core.PVector;

import java.awt.*;
import java.util.Random;

public class HoleTile extends EmptyPath {

    public HoleTile(PVector pos, Window w) {
        super(pos, w);
        Random rand = new Random();
        float x = rand.nextInt(Tile.TILE_SIZE);
        float y = rand.nextInt(Tile.TILE_SIZE);
        TrapManager.getInstance().addTrap(
            new Hole(pos.copy().add(x, y),
                new PVector(0, 0), 50, 0, Color.black, w));

      x = rand.nextInt(Tile.TILE_SIZE);
      y = rand.nextInt(Tile.TILE_SIZE);
      TrapManager.getInstance().addTrap(
          new Hole(pos.copy().add(x, y),
              new PVector(0, 0), 50, 0, Color.black, w));
    }

}
