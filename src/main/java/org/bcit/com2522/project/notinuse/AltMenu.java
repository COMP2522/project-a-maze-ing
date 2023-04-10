package org.bcit.com2522.project.notinuse;

import org.bcit.com2522.project.Button;
import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.GameState;
import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class AltMenu {
  ArrayList<org.bcit.com2522.project.Button> buttons = new ArrayList<org.bcit.com2522.project.Button>();
  org.bcit.com2522.project.Window window;
  org.bcit.com2522.project.Button play;
  org.bcit.com2522.project.Button loadAll;
  int rgb = 0;
  Color hover = Color.YELLOW;

  private Rectangle loadAllButton;

  public AltMenu(){
    play = new org.bcit.com2522.project.Button("Play", 100, 100, 200, 100, Color.BLUE, new org.bcit.com2522.project.Menu());
    loadAll = new org.bcit.com2522.project.Button("Load All", 100, 250, 200, 100, Color.BLUE, new org.bcit.com2522.project.Menu());
    buttons.add(play);
    buttons.add(loadAll);
  }

  public void draw(){
    window.fill(rgb);
    window.rect(0, 0, window.width, window.height);
    for (org.bcit.com2522.project.Button b : buttons){
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