package org.bcit.com2522.project;

import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class AltMenu {
  ArrayList<Button> buttons = new ArrayList<Button>();
  Window window;
  Button play;
  Button loadAll;
  int rgb = 0;
  Color hover = Color.YELLOW;

  private Rectangle loadAllButton;

  public AltMenu(){
    play = new Button("Play", 100, 100, 200, 100, Color.BLUE, new Menu());
    loadAll = new Button("Load All", 100, 250, 200, 100, Color.BLUE, new Menu());
    buttons.add(play);
    buttons.add(loadAll);
  }

  public void draw(){
    window.fill(rgb);
    window.rect(0, 0, window.width, window.height);
    for (Button b : buttons){
      b.draw();
    }
  }

  public void click(MouseEvent m) {
    for (Button b : buttons) {
      if (b.cursorInside(m.getX(), m.getY())) {
        if (b == play) {
          GameManager.getInstance().window.initializeObjects();
        } else if (b == loadAll) {
          GameManager.getInstance().setState(GameState.LOAD_ALL);
        }
      }
    }
  }
}