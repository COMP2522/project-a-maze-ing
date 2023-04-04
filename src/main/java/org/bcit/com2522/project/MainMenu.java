package org.bcit.com2522.project;

import java.awt.*;

/**
 * Main menu where user controls program.
 */
public class MainMenu extends Menu {

  Button play, loadAll;
  public MainMenu() {
    play = new Button("Play", 100, 100, 200, 100, Color.BLUE, this);
    play.config(() -> window.initializeObjects());
    buttons.add(play);
    loadAll = new Button("Load", 100, 250, 200, 100, Color.BLUE, this);
    buttons.add(loadAll);
    loadAll.config(() -> window.state = GameState.LOAD_ALL);
  }
}
