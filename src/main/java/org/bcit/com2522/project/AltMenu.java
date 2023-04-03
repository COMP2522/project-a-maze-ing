package org.bcit.com2522.project;

import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class AltMenu {
  ArrayList<Button> buttons = new ArrayList<Button>();
  Window window;
  Button play;
  int rgb = 0;
  Color hover = Color.YELLOW;

  public AltMenu(Window w){
    window = w;
    play = new Button("Play", 100, 100, 200, 100, Color.BLUE, w, this);
    buttons.add(play);
  }

  public void draw(){
    window.fill(rgb);
    window.rect(0, 0, window.width, window.height);
    for (Button b : buttons){
      b.draw();
    }
  }

  public void click(MouseEvent m){
    for (Button b : buttons){
      if (b.cursorInside(m.getX(), m.getY())){
        b.execute();
      }
    }
  }

}
