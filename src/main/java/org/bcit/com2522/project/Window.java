package org.bcit.com2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {

  ArrayList<Sprite> sprites;
  ArrayList<Enemy> enemies;
  ArrayList<Blade> blades;
  Ghost ghost;
  Player player;

  int numEnemies = 10;
  int numBlades = 10;
  int minSize = 10;
  int maxSize = 20;

  /**
   * Provides the size of the window
   */
  public void settings(){
    size(800,600);
  }

  /**
   * Called to initialize objects once the program starts.
   * Calls initializeObjects() method
   */
  public void setup(){
    this.initializeObjects();
  }


//  private static Window windowInstance;
//
//  private Window() {
//    //Empty for singleton
//  }
//
//  public static Window getInstance() {
//    if (windowInstance == null) {
//      windowInstance = new Window();
//    }
//    return windowInstance;
//  }
//
  public void initializeObjects() {
    enemies = new ArrayList<Enemy>();
    sprites = new ArrayList<Sprite>();
    blades = new ArrayList<Blade>();
    player = new Player(
        new PVector(this.width/2,this.height/2),
        new PVector(0,1),
        minSize + 1,
        0,
        new Color(0,255,0),
        this);

      ghost = new Ghost(
          new PVector(this.width/3,this.height/3),
          new PVector(0,1),
          minSize + 1,
          5,
          new Color(255,255,255),
          this);

    for (int i = 0; i < numBlades; i++) {
      Blade blade = new Blade(
              new PVector(150 * (i + 1), height / 2),
              new PVector(0, 1),
              40,
              2,
              new Color(255, 0, 0),
              this
      );
      blades.add(blade);
      sprites.add(blade);
    }



//
//    for (int i = 0; i < numEnemies; i++) {
//      enemies.add(new Sprite(
//          new PVector(random(0, this.width), random(0, this.height)),
//          new PVector(random(-1, 1), random(-1,1)),
//          random(minSize, maxSize),
//          random(0,2),
//          new Color(255, 0, 0),
//          this
//      ));
//    }
    sprites.add(ghost);
    sprites.add(player);

  }

  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch( keyCode ) {
      case LEFT:
        // handle left
        player.setDirection(player.getDirection().rotate(-Window.PI / 16));
        break;
      case RIGHT:
        // handle right
        player.setDirection(player.getDirection().rotate(Window.PI / 16));
        break;
    }
  }

  public void handlePlayerDeath() {
    // Stop the game or restart the level
    // For now, just print "Game Over" and stop the sketch
    System.out.println("Game Over");
    noLoop();
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    for (Sprite sprite : sprites) {
      sprite.update();
      if (sprite instanceof Blade && player.collidesWith(sprite)) {
        handlePlayerDeath();
      }
      sprite.draw();
    }
//    ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
//    for (Sprite enemy : enemies) {
//      if (Sprite.collide(player, enemy)) {
//        toRemove.add(enemy);
//      }
//    }
//    for (Sprite enemy : toRemove) {
//      // TODO: implement compareTo and equals to make this work
//      enemies.remove(enemy);
//    }

  }


  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}
