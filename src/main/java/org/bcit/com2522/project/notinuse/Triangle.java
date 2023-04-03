package org.bcit.com2522.project.notinuse;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Graphic;

import java.awt.*;

public class Triangle implements Graphic {
  Color color;
  int sideLength;

  public Triangle(Color c, int sl) {
    color = c;
    sideLength = sl;
  }

  @Override
  public void draw(Sprite s, Window w) {
    w.triangle(s.getPosition().x, s.getPosition().y,
        s.getPosition().x + 50, s.getPosition().y + 50,
        s.getPosition().x, s.getPosition().y - 100);
  }
}
