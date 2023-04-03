package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.interfaces.Collidable;

import java.util.ArrayList;

public class WallManager implements Collidable {
  private ArrayList<Wall> walls = new ArrayList<Wall>();

  public void add(Wall w){
    walls.add(w);
  }


  @Override
  public boolean collision(Sprite s) {
    for (Wall w : walls){
      if (w.collision(s)){
        return true;
      }
    }
    return false;
  }

  public void clearWalls() {
    walls = new ArrayList<>();
  }

  public void draw(){
    for (Wall w : walls){
      w.draw();
    }
  }
}
