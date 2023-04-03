package org.bcit.com2522.project;

import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class Menu {
  ArrayList<Button> buttons = new ArrayList<Button>();
  Window window;
  int rgb = 0;
  Color hover = Color.YELLOW;
  boolean clickable = false;

  public Menu(Window w){
    window = w;
  }

  public void loadMenu(){
    clickable = true;
  }

  public void hideMenu(){
    clickable = false;
  }

  public void draw(){
    window.fill(rgb);
    window.rect(0, 0, window.width, window.height);
    for (Button b : buttons){
      b.draw();
    }
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
