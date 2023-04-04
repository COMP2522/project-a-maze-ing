package org.bcit.com2522.project;

import java.awt.*;

/**
 * Pause menu that user that displays when the game is in play and paused.
 */
public class PauseMenu extends Menu{

  Button resume, save, exit;
  float buttonWidth;
  float buttonHeight = 100;

  public PauseMenu(Window w) {
    super(w);
    buttonWidth = w.WINDOW_X / 2;
    resume = new Button("Resume", w.width / 4, buttonHeight, buttonWidth, buttonHeight,
        Color.BLUE, w, this);
    resume.config(() -> window.state = GameState.PLAY);
    buttons.add(resume);
    save = new Button("Save", w.width / 4, buttonHeight * 3 , buttonWidth, buttonHeight,
        Color.BLUE, w, this);
    buttons.add(save);
    exit = new Button("Exit", w.width / 4, buttonHeight * 5, buttonWidth, buttonHeight,
        Color.BLUE, w, this);
    buttons.add(exit);
    exit.config(() -> window.state = GameState.MENU);
  }
}
