package org.bcit.com2522.project;

import org.bcit.com2522.project.interfaces.Graphic;

import java.util.ArrayList;

public class CompoundGraphic implements Graphic{
  ArrayList<Graphic> graphics = new ArrayList<Graphic>();

  public void add(Graphic g){
    graphics.add(g);
  }

  public void remove(Graphic g){
    graphics.remove(g);
  }

  public ArrayList<Graphic> get(){
    return graphics;
  }

  @Override
  public void draw(Sprite s, Window w) {
    for (Graphic gra : graphics){
      gra.draw(s, w);
    }
  }
}
