package org.bcit.com2522.project.notinuse;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.interfaces.Graphic;

import java.util.ArrayList;

public class CompoundGraphic implements Graphic{
  ArrayList<Graphic> graphics = new ArrayList<Graphic>();

  /**
   * Add a Graphic to this CompoundGraphic.
   * @param g An object that implements Graphic
   */
  public void add(Graphic g){
    graphics.add(g);
  }

  /**
   * Remove a Graphic from this CompoundGraphic.
   * @param g An object that implements Graphic
   */
  public void remove(Graphic g){
    graphics.remove(g);
  }

  /**
   * Return the list of graphics in this CompoundGraphic.
   * @return the list of Graphic objects
   */
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
