package org.bcit.com2522.project;

import java.awt.*;

/**
 * Pause menu that user that displays when the game is in play and paused.
 */
public class PauseMenu extends Menu{

  Button resume, save, exit;
  float buttonWidth;
  float buttonHeight = 100;

  public PauseMenu() {
    buttonWidth = GameManager.getInstance().window.WINDOW_X / 2;
    resume = new Button("Resume", GameManager.getInstance().window.width / 4, buttonHeight, buttonWidth, buttonHeight,
        Color.BLUE, this);
    resume.config(() -> GameManager.getInstance().setState(GameState.PLAY));
    buttons.add(resume);
    save = new Button("Save", GameManager.getInstance().window.width / 4, buttonHeight * 3 , buttonWidth, buttonHeight,
        Color.BLUE, this);
    buttons.add(save);
    exit = new Button("Exit", GameManager.getInstance().window.width / 4, buttonHeight * 5, buttonWidth, buttonHeight,
        Color.BLUE, this);
    buttons.add(exit);
    exit.config(() -> GameManager.getInstance().setState(GameState.MENU));
  }
}
