package org.bcit.com2522.project.interfaces;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;

/**
 * Interface for a renderable object.
 */
public interface Graphic {

  /**
   * Draws the given object in the given window.
   */
  void draw(Sprite s, Window w);
}
