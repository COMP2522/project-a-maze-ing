package org.bcit.com2522.project.labyrinth.Tiles;

import processing.core.PVector;

import java.util.Random;

public abstract class TrapTile extends EmptyPathTile {

  protected static final Random TRAP_RANDOMIZER = new Random();
  public TrapTile(PVector pos) {
    super(pos);
  }

  protected abstract void spawnTraps(int numTraps, PVector pos);
}
