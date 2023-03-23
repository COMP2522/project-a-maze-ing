package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.interfaces.Collidable;
import processing.core.PVector;

public class Wall extends Tile {

  public Wall(PVector pos) {
    super(pos);
  }

  @Override
  public boolean collision(Collidable a, Collidable b) {
    return false;
  }
}
