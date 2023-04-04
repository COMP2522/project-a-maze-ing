package org.bcit.com2522.project;

import org.bcit.com2522.project.Database.Database;
import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.WallManager;
import org.bcit.com2522.project.traps.TrapManager;

public class GameManager {

  private static GameManager instance;
  private GameState state;

  public Window window;

  private GameManager() {
    window = new Window();
    EnemyManager.getInstance();
    TrapManager.getInstance();
    LabyrinthManager.getInstance();
    Database.getInstance();
    Player.getInstance();
  }

  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  public static void clearAll() {
    TrapManager.getInstance().clearTraps();
    EnemyManager.getInstance().clearEnemies();
    LabyrinthManager.getInstance().resetTiles();
    WallManager.getInstance().clearWalls();
  }

  public GameState getState() {
    return state;
  }

  public void setState(GameState state) {
    this.state = state;
  }

}
