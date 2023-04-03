package org.bcit.com2522.project;

import processing.core.PVector;

import java.awt.*;

public class Button {
  float x;
  float y;
  float width;
  float height;
  PVector position;
  Color bg;
  String label;
  Window window;
  AltMenu menu;

  public Button(String s, float initX, float initY, float w, float h, Color c, Window space, AltMenu m){
    width = w;
    height = h;
    x = initX;
    y = initY;
    bg = c;
    label = s;
    window = space;
    menu = m;
  }

  public Button(String mazeName, float initX, float initY, float w, float h, Color c, Window space) {
  }

  public void draw(){
    if (cursorInside(window.mouseX, window.mouseY)){
      window.fill(menu.hover.getRGB());
    } else {
      window.fill(bg.getRGB());
    }
    window.rect(x, y, width, height);
    window.fill(0);
    window.textSize(50);
    window.text(label, x, y + 50);
  }

  public boolean cursorInside(float mX, float mY){
    return (mX >= x && mX <= x + width && mY >= y && mY <= y + height);
  }

  public String getLabel() {
    return label;
  }

  public void loadMaze() {
    // Load the maze with the given name
    // Implement the functionality here
  }


  public void execute() {
    window.initializeObjects();
  }
}