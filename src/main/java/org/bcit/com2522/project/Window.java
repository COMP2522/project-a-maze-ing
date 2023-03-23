package org.bcit.com2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

/**
 * The window class runs the display that initializes and displays all
 * other classes represented in the front-end of the maze game
 */
public class Window extends PApplet {
  private Timer timer;

  PImage playerImage;
  ArrayList<Sprite> sprites;
  ArrayList<Enemy> enemies;
  Ghost ghost;
  Player player;

  //int num walls = whatever number matt or alex gets for this, need to call it
  //also need to get position of that wall and put in constructor of initialize objects method

  int numSporadics = 10;
  int numWraiths = 5;
  int minSize = 10;
  int maxSize = 20;

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
    backgroundImage = loadImage("images/dirt.png");
    frameRate(144);

    //generates sounds
    //sporadicSound = loadSound("sound/sporadicEX.mp3");

    this.initializeObjects();
  }

  public void initializeObjects() {
    enemies = new ArrayList<Enemy>();
    sprites = new ArrayList<Sprite>();
    //walls = new ArrayList<Wall>();
    timer = new Timer(this, new PVector(0,0));
    player = new Player(
        new PVector(this.width/2,this.height/2),
        new PVector(0,0),
        minSize + 1,
        2,
        new Color(0,255,0),
        this, playerImage);

      ghost = new Ghost(
          new PVector(this.width/3,this.height/3),
          new PVector(0,1),
          minSize + 1,
          0.3f,
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
//    for (int i = 0; i < numWalls; i++) {
//      walls.add(new Wall(
//          new PVector(random(0, this.width), random(0, this.height)),
//          random(minSize, maxSize),
//          this
//      ));
//    }
    sprites.add(ghost);
    sprites.add(player);
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
        player.getPosition().x - width/2,
        player.getPosition().y - height/2);
    // Translate the drawing surface to the camera position
    //timer.setPosition(player.getPosition());
    translate(-cameraPos.x, -cameraPos.y);
    /**
     * This section will load the background image
     */
    image(backgroundImage, -1000, -1000, width*4, height*4);

    //timer.setPosition(player.getPosition());
    float timeElapsed = timer.getTime();
    text("Time elapsed: " + timeElapsed + " seconds", player.getPosition().x-width/2,player.getPosition().y- width/3);

//    float timeElapsed = timer.getTime();
    //text("Time elapsed: " + timeElapsed + " seconds", 10, 10);


    /**
     * Just updates and draws all sprites in the list
     */
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }

    ghost.move(player); //This will follow the player everywhere they go
    //Moves multiple enemy sporadic and wraith types

    for (Enemy enemyList : enemies) {
      enemyList.move(player);
    }

//    for (Wall wall : walls) {
//      wall.draw();
//    }
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"find the End"};
    Window mazeRun = new Window();
    PApplet.runSketch(appletArgs, mazeRun);
  }
}
