package org.bcit.com2522.project;

import java.awt.*;

/**
 * A clickable button.
 */
public class Button {
  /* x-coordinate of the button's top left corner */
  float x;

  /* y-coordinate of the button's top left corner */
  float y;

  /* button width */
  float width;

  /* button height */
  float height;

  /* button background color */
  Color bg;

  /* text label on the button */
  String label;

  /* menu associated with this button */
  Menu menu;

  /* Lambda function that is called when the button executes */
  private Executable function;

  /**
   * Constructs a Button.
   * @param s text label
   * @param initX x-coor
   * @param initY y-co0r
   * @param w width
   * @param h heigh
   * @param c background color
   * @param m parent menu
   */
  public Button(String s, float initX, float initY, float w, float h, Color c, Menu m){
    width = w;
    height = h;
    x = initX;
    y = initY;
    bg = c;
    label = s;
    menu = m;
  }

  /**
   * Takes a lambda function that will be executed on button click.
   * @param ex lambda function that the button will perform
   */
  public void config(Executable ex) {
    function = ex;
  }

  public Button(String mazeName, float initX, float initY, float w, float h, Color c, Window space) {
  }

  /**
   * Renders the button.
   */
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

  /**
   * Checks if the given mouse event coordinates are inside the button
   * @param mX mouse x-coor
   * @param mY mouse y-coor
   * @return true if mouse event happened in button, false otherwise
   */
  public boolean cursorInside(float mX, float mY){
    return (mX >= x && mX <= x + width && mY >= y && mY <= y + height);
  }

  /**
   * Return the label String of the button.
   * @return label String
   */
  public String getLabel() {
    return label;
  }

  /**
   * The action performed by the button. Set using config().
   */
  public void execute() {
    function.execute();
    menu.hideMenu();
  }
}

/**
 * Implementing classes will be able to accept lambda functions as arguments.
 */
interface Executable{
  void execute();
}
