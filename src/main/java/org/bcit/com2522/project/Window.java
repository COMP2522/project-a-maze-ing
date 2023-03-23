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

  /* Timer object that shows how long it takes player to complete the maze.*/
  private Timer timer;

  PImage playerImage;

  /* List of all in-game sprites to be loaded. */
  ArrayList<Sprite> sprites;

  /* List of all in-game enemies to be loaded. */
  ArrayList<Enemy> enemies;

  /*Single enemy type that follows player around the map. */
  Ghost ghost;

  /*Player that is controlled by user to navigate maze.*/
  Player player;

  //int num walls = whatever number matt or alex gets for this, need to call it
  //also need to get position of that wall and put in constructor of initialize objects method

  /* Number of Sporadic enemy types in the maze. */
  int numSporadics = 10;

  /* Number of Wraith enemy types in the maze. */
  int numWraiths = 5;

  /* Hitbox size in pixels of sporadic enemy type.*/
  int playerSize = 10;

  /* Hitbox size in pixels of sporadic enemy type.*/
  public static final int SPORADIC_SIZE = 20;

  /* Hitbox size in pixels of ghost enemy type.*/
  public static final int GHOST_SIZE = 30;

  /* Hitbox size in pixels of wraith enemy type.*/
  public static final int WRAITH_SIZE = 10;

  /* Background Image displayed for the game. */
  PImage backgroundImage;

  /* Length of window in pixels.*/
  public static final int WINDOW_X = 800;

  /* Height of window in pixels.*/
  public static final int WINDOW_Y = 600;

  /**
   * Provides the size of the window
   */
  public void settings(){
    size(WINDOW_X, WINDOW_Y);
  }

  /**
   * Called to initialize objects once the program starts.
   * Calls initializeObjects() method
   */
  public void setup(){
    backgroundImage = loadImage("images/dirt.png"); //Background image for the window
    frameRate(144);
    this.initializeObjects();
  }

  /**
   * Initializes all sprites, timers, and walls. Moves some objects to a list to
   * be called and updated in the draw() method
   */
  public void initializeObjects() {
    enemies = new ArrayList<Enemy>();  //List of enemies, except ghost

    sprites = new ArrayList<Sprite>();  //List of all sprites

    //walls = new ArrayList<Wall>();  //List of all walls that make up the Labyrinth

    timer = new Timer(this, new PVector(0,0));

    //Initializes player object
    player = new Player(
        new PVector(this.width/2,this.height/2),
        new PVector(0,0),
        playerSize,
        2,
        new Color(0,255,0),
        this, playerImage);

    //Initializes ghost object
    ghost = new Ghost(
        new PVector(this.width/3,this.height/3),
        new PVector(0,1),
        GHOST_SIZE,
        0.3f,
        new Color(255,255,255),
        this);

    //Initializes all sporadic enemies and adds them to enemy array list
    for (int i = 0; i < numSporadics; i++) {
      enemies.add(new Sporadic(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1,1)),
          SPORADIC_SIZE,
          5,
          new Color(255, 0, 0),
          this
      ));
    }

    //Initializes all wraith enemies and adds them to enemy array list
    for (int i = 0; i < numWraiths; i++) {
      enemies.add(new Wraith(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1,1)),
          WRAITH_SIZE,
          0.5f,
          new Color(0, 0, 255),
          this
      ));
    }

    //Initializes all wall objects that make up Labyrinth
//    for (int i = 0; i < numWalls; i++) {
//      walls.add(new Wall(
//          new PVector(random(0, this.width), random(0, this.height)),
//          random(minSize, maxSize),
//          this
//      ));
//    }

    sprites.add(ghost);  //Adds ghost to list of sprites
    sprites.add(player);  //Adds player to list of sprites
    sprites.addAll(enemies);  //Adds remaining enemies to list of sprites
  }

  /**
   * Whenever the user presses one of the arrow keys, the player object is moved
   * @param event
   */
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

  /**
   * Whenever the user stops pressing one of the arrow keys, the player object stops moving
   * @param event
   */
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
   * Called on every frame to update window objects. Objects
   * are drawn in order of appearance in this method.
   */
  public void draw() {

    // Calculate the camera position based on the player's position
    PVector cameraPos = new PVector(
        player.getPosition().x - width/2,
        player.getPosition().y - height/2);
    // Translate the drawing surface to the camera position
    translate(-cameraPos.x, -cameraPos.y);

    image(backgroundImage, -1000, -1000, width*4, height*4); //Loads in background image

    //Updates timer time and position in the window
    float timeElapsed = timer.getTime();
    text("Time elapsed: " + timeElapsed + " seconds", player.getPosition().x-width/2,player.getPosition().y- width/3);

    //Just updates and draws all sprites in the list
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
   * Main function to run the entire game.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"find the End"};
    Window mazeRun = new Window();
    PApplet.runSketch(appletArgs, mazeRun);
  }
}
