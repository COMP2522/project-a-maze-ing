package org.bcit.com2522.project;

import java.awt.*;

public class Button {
  float x;
  float y;
  float width;
  float height;
  Color bg;
  String label;
  Menu menu;
  private Executable function;

  public Button(String s, float initX, float initY, float w, float h, Color c, Menu m){
    width = w;
    height = h;
    x = initX;
    y = initY;
    bg = c;
    label = s;
    menu = m;
  }

  public void config(Executable ex) {
    function = ex;
  }
  public Button(String mazeName, float initX, float initY, float w, float h, Color c, Window space) {
  }

  public void draw(){
    if (cursorInside(GameManager.getInstance().window.mouseX, GameManager.getInstance().window.mouseY)){
      GameManager.getInstance().window.fill(menu.hover.getRGB());
    } else {
      GameManager.getInstance().window.fill(bg.getRGB());
    }
    GameManager.getInstance().window.rect(x, y, width, height);
    GameManager.getInstance().window.fill(0);
    GameManager.getInstance().window.textSize(50);
    GameManager.getInstance().window.text(label, x, y + 50);
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
