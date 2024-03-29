package org.bcit.com2522.project;

import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

/**
 * An interactive menu.
 */
public class Menu {
  ArrayList<Button> buttons = new ArrayList<Button>();
  int rgb = 0;
  Color hover = Color.YELLOW;
  boolean clickable = false;

  public Menu(){
  }

  public void loadMenu(){
    clickable = true;
  }

  public void hideMenu(){
    clickable = false;
  }

  /**
   * Renders the menu and its child Buttons.
   */
  public void draw(){
    GameManager.getInstance().window.image(
        GameManager.getInstance().window.backgroundMainMenu, 0, 0,
        GameManager.getInstance().window.width,
        GameManager.getInstance().window.height);
    for (Button b : buttons){
      b.draw();
    }
  }

  /**
   * Checks whether any button in the menu has been clicked.
   * @param m mouse click event
   */
  public void click(MouseEvent m) {
    if (clickable) {
      for (Button b : buttons) {
        if (b.cursorInside(m.getX(), m.getY())) {
          b.execute();
        }
      }
    }
  }

}
