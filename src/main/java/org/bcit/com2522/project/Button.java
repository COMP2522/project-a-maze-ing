package org.bcit.com2522.project;

import java.awt.*;

public class Button {
  float x;
  float y;
  float width;
  float height;
  Color bg;
  String label;
  Window window;
  Menu menu;
  private Executable function;

  public Button(String s, float initX, float initY, float w, float h, Color c, Window space, Menu m){
    width = w;
    height = h;
    x = initX;
    y = initY;
    bg = c;
    label = s;
    window = space;
    menu = m;
  }

  public void config(Executable ex) {
    function = ex;
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


  public void execute() {
    function.execute();
    menu.hideMenu();
  }
}

interface Executable{
  void execute();
}
