package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.interfaces.Collidable;

import java.util.ArrayList;

/**
 * Handles logic for all walls.
 */
public class WallManager implements Collidable {
  /**
   * List of all walls in the game.
   */
  private ArrayList<Wall> walls = new ArrayList<Wall>();

  /**
   * Singleton Instance.
   */
  private static WallManager instance;

  /**
   * Constructor.
   */
  private  WallManager() {}

  /**
   * Gets the singleton instance.
   * @return the instance of the wall manager.
   */
  public static WallManager getInstance() {
    if (instance == null) {
      instance = new WallManager();
    }
    return instance;
  }

  /**
   * Adds a wall to the game.
   * @param w the wall to add.
   */
  public void add(Wall w){
    walls.add(w);
  }


  /**
   * Handles collision with all walls.
   * @param s  Sprite object to test collision against.
   * @return
   */
  @Override
  public boolean collision(Sprite s) {
    for (Wall w : walls){
      if (w.collision(s)){
        w.walkIntoWall(s);
        return true;
      }
    }
    return false;
  }

  /**
   * Removes all walls from the game.
   */
  public void clearWalls() {
    walls = new ArrayList<>();
  }

  /**
   * Render all walls.
   */
  public void draw(){
    for (Wall w : walls){
      w.draw();
    }
  }
}
