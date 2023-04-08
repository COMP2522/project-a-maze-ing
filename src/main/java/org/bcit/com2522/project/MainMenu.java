package org.bcit.com2522.project;

import java.awt.*;

/**
 * Main menu where user controls program.
 */
public class MainMenu extends Menu {

  Button play, loadAll;
  public MainMenu() {
    play = new Button("Play", 100, 100, 200, 100, Color.BLUE, this);
    play.config(() -> GameManager.getInstance().window.initializeObjects());
    buttons.add(play);
    loadAll = new Button("Load All Saved Mazes", 100, 250, 500, 100, Color.BLUE, this);
    buttons.add(loadAll);
    loadAll.config(() -> GameManager.getInstance().setState(GameState.LOAD_ALL));
  }


  // Method to return to the main menu from the saved maze buttons screen
  public void returnToMainMenu() {
    GameManager.getInstance().setState(GameState.MENU);
  }
}
