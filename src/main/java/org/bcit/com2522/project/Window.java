package org.bcit.com2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {

  private static final int FPS = 144;

  ArrayList<Sprite> sprites;
  ArrayList<Enemy> enemies;
  Ghost ghost;
  Player player;
  //SoundFile sporadicSound;

  //Sporadic sporadic;

  int numSporadics = 10;
  int numWraiths = 5;
  int minSize = 10;
  int maxSize = 20;

  boolean gameover;

  PImage backgroundImage; //Background Image for the Window

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
    //initializes the objects
    this.initializeObjects();
    //sets up the background image
    backgroundImage = loadImage("images/Sleepy.png");
    frameRate(FPS);
    //generates sounds
    //sporadicSound = loadSound("sound/sporadicEX.mp3");
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
    player = new Player(
        new PVector(this.width/2,this.height/2),
        new PVector(0,0),
        minSize + 1,
        2,
        new Color(0,255,0),
        this);

      ghost = new Ghost(
          new PVector(this.width/3,this.height/3),
          new PVector(0,1),
          minSize + 1,
          0.05f,
          new Color(255,255,255),
          this);
//
    for (int i = 0; i < numSporadics; i++) {
      enemies.add(new Sporadic(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1,1)),
          minSize,
          5,
          new Color(255, 0, 0),
          this
      ));
    }
    for (int i = 0; i < numWraiths; i++) {
      enemies.add(new Wraith(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1,1)),
          minSize,
          0.5f,
          new Color(0, 0, 255),
          this
      ));
    }
    sprites.add(ghost);
    sprites.add(player);
    enemies.add(ghost);
    sprites.addAll(enemies);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch( keyCode ) {
      case LEFT:
        // handle left
        player.setDirection(new PVector(-2, 0));
        break;
      case RIGHT:
        // handle right
        player.setDirection(new PVector(2, 0));
        break;
      case UP:
        // handle left
        player.setDirection(new PVector(0, -2));
        break;
      case DOWN:
        // handle right
        player.setDirection(new PVector(0, 2));
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT:
        // stop moving left
        player.setDirection(new PVector(0, 0));
        break;
      case RIGHT:
        // stop moving right
        player.setDirection(new PVector(0, 0));
        break;
      case UP:
        // stop moving up
        player.setDirection(new PVector(0, 0));
        break;
      case DOWN:
        // stop moving down
        player.setDirection(new PVector(0, 0));
        break;
      case 'R':
        if (gameover){
          gameover = false;
          player.setImmunityTimer(1);
          player.setAlive(true);
          PVector newPos = new PVector(player.getPosition().x + 30, player.getPosition().y + 30);
          player.setPosition(newPos);
        }
        break;
    }
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {

    /**
     * This section will Zoom the camera in and follow the player around
     */
    float zoomFactor = 2.0f; // Increase this value to zoom in more
    // Calculate the camera position based on the player's position
    PVector cameraPos = new PVector(
        player.getPosition().x - width / 2,
        player.getPosition().y - height / 2);
    // Translate the drawing surface to the camera position
    translate(-cameraPos.x, -cameraPos.y);
    if (!gameover) {
    /**
     * This section will load the background image
     */
    image(backgroundImage, -1000, -1000, width * 4, height * 4);

    /**
     * Just updates and draws all sprites in the list
     */
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();


//      if (sprite instanceof Player) {
//        Player player = (Player) sprite;
//        PVector direction = player.getDirection();
//        PVector position = player.getPosition();
//        position.add(PVector.mult(direction, player.getSpeed()));
//        player.setPosition(position);
//      }


      ghost.move(player); //This will follow the player everywhere they go
      //Moves multiple enemy sporadic and wraith types
      for (Enemy enemyList : enemies) {
        enemyList.move(player);
      }

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
    if (player.getImmunityTimer() > 0) {
      player.setImmunityTimer(player.getImmunityTimer() - ((float) 1 / FPS));
    }

    for (Enemy e : enemies) {
      if (player.collision(e) && player.getImmunityTimer() <= 0) {
        player.setAlive(false);
      }
    }

    if (!player.isAlive()) {
      gameover = true;
    }
  } else {
    background(0);
    textSize(50);
    text("Game Over!", cameraPos.x + width / 3, cameraPos.y + height / 2);
    text("Press R to restart.", cameraPos.x + width / 3, cameraPos.y + height / 2 + 50);
  }
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
