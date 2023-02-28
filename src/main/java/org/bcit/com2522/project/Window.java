package org.bcit.com2522.project;

import processing.core.PApplet;

public class Window extends PApplet {
  // ArrayList<Sprite> sprites;
  //ArrayList<Sprite> enemies;
  Sprite player;
  int numEnemies = 10;
  int minSize = 10;
  int maxSize = 20;

  private static Window windowInstance;

  private Window() {
    //Empty for singleton
  }

  public static Window getInstance() {
    if (windowInstance == null) {
      windowInstance = new Window();
    }
    return windowInstance;
  }

  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(640, 360);
  }


  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
//  public void setup() {
//    this.init();
//  }
//
//  public void init() {
//    enemies = new ArrayList<Sprite>();
//    sprites = new ArrayList<Sprite>();
//    player = new Sprite(
//        new PVector(this.width/2,this.height/2),
//        new PVector(0,1),
//        minSize + 1,
//        2,
//        new Color(0,255,0),
//        this);
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
//    sprites.addAll(enemies);
//    sprites.add(player);
//  }

//  @Override
//  public void keyPressed(KeyEvent event) {
//    int keyCode = event.getKeyCode();
//    switch( keyCode ) {
//      case LEFT:
//        // handle left
//        player.setDirection(player.getDirection().rotate(-Window.PI / 16));
//        break;
//      case RIGHT:
//        // handle right
//        player.setDirection(player.getDirection().rotate(Window.PI / 16));
//        break;
//    }
//  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
//  public void draw() {
//    background(0);
//    for (Sprite sprite : sprites) {
//      sprite.update();
//      sprite.draw();
//    }
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
//
//  }


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
