package org.bcit.com2522.project;

import java.awt.*;

/**
 * The MainMenu class represents the main menu of the game.
 * It extends the Menu class and includes buttons for playing the game and loading all saved mazes.
 * The MainMenu class uses Button objects to create and manage its buttons.
 * @author Laurie Solkoski
 * @author Alex Sichitiu
 * @author Matthew Siggs
 * @version 1.0
 */
public class MainMenu extends Menu {

  /* Main menu Buttons */
  Button play, loadAll;

  /**
   * Constructs a MainMenu.
   */
  public MainMenu() {
    play = new Button("Play", 100, 100, 200, 100, Color.BLUE, this);
    play.config(() -> GameManager.getInstance().window.initializeObjects());
    buttons.add(play);
    loadAll = new Button("Load All Saved Mazes", 100, 250, 500, 100, Color.BLUE, this);
    buttons.add(loadAll);
    loadAll.config(() -> GameManager.getInstance().setState(GameState.LOAD_ALL));
  }


  /**
   *   Return to the main menu from the saved maze buttons screen
   */
  public void returnToMainMenu() {
    GameManager.getInstance().setState(GameState.MENU);
  }
}
