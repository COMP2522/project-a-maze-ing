package org.bcit.com2522.project.labyrinth;

import org.bcit.com2522.project.Window;

public class LabyrinthManager {

  private static LabyrinthManager instance;

  private Window window;

  private Labyrinth current;
  private LabyrinthManager() {}

  /**
   * returns the labyrinth manager if one exists, creates a new one and returns if one doesn't exist.
   * @return
   */
  public static LabyrinthManager getInstance() {
    if(instance == null) {
      instance = new LabyrinthManager();
    }
    return instance;
  }

  /**
   * Renders the current labyrinth into the window.
   */
  public void render() {

  }

  /**
   * generates a new labyrinth with given width and height, and sets it as current labyrinth.
   * @param width - the width of the new labyrinth
   * @param height - the height of the new labyrinth
   */
  public void newLabyrinth(int width, int height) {
    current = new Labyrinth(width, height);
  }

  /**
   * loads a existing labyrinth from the database.
   * todo: this is a placeholder, need to figure out how to search for a given labyrinth
   */
  public void loadLabyrinth() {
    //todo
  }


}
