package org.bcit.com2522.project;

import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

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

  public void draw(){

    GameManager.getInstance().window.image(
        GameManager.getInstance().window.backgroundMainMenu, 0, 0,
        GameManager.getInstance().window.width,
        GameManager.getInstance().window.height);
//    GameManager.getInstance().window.fill(rgb);
//    GameManager.getInstance().window.rect(0, 0, GameManager.getInstance().window.width, GameManager.getInstance().window.height);
    for (Button b : buttons){
      b.draw();
    }
  }

  public boolean getClickable() {

    return clickable;
  }


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
