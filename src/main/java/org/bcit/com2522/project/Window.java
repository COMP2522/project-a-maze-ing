package org.bcit.com2522.project;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.enemy.Enemy;
import org.bcit.com2522.project.enemy.Ghost;
import org.bcit.com2522.project.enemy.Sporadic;
import org.bcit.com2522.project.enemy.Wraith;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The window class runs the display that initializes and displays all
 * other classes represented in the front-end of the maze game
 */
public class Window extends PApplet {


  private static final int FPS = 144;

  /* Minim object for playing sound */
  Minim minim;

  /* Manager for the labyrinth */
  LabyrinthManager labManager;

  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  /* Timer object that shows how long it takes player to complete the maze.*/
  private Timer timer;

  PImage playerImage;

  /* List of all in-game sprites to be loaded. */
  ArrayList<Sprite> sprites;

  /* List of all in-game enemies to be loaded. */
  ArrayList<Enemy> enemies;

  ArrayList<Wraith> wraiths;

  ArrayList<Sporadic> sporadics;

  /*Single enemy type that follows player around the map. */
  Ghost ghost;

  /*Player that is controlled by user to navigate maze.*/
  Player player;


  private Blade blade1;
  private Blade blade2;
  private Blade blade3;

  private List<Hole> holes;

  PImage playerDown;

  PImage playerLeft;
  PImage playerRight;
  PImage playerUp;

  /* Number of Sporadic enemy types in the maze. */
  int numSporadics = 10;

  boolean gameover;

  PImage backgroundImage; //Background Image for the Window

  /* Number of Wraith enemy types in the maze. */
  int numWraiths = 5;

  /* Hitbox size in pixels of sporadic enemy type.*/
  int playerSize = 10;

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

    minim = new Minim(this);
    sound = minim.loadFile("sound/heroSong.mp3");
    sound.play();

    frameRate(FPS);

    //sets up the background image
    backgroundImage = loadImage("Data/dirt.png");


    playerDown = loadImage("Data/HPfront.png");

    playerLeft = loadImage("Data/HPleft.png");
    playerRight = loadImage("Data/HPright.png");
    playerUp = loadImage("Data/HPup.png");


    System.out.println("Loading image from path: " + sketchPath("Data/HPfront.png"));
    if (playerDown == null) {
      System.out.println("Image is null after loading.");
    } else {
      System.out.println("Image successfully loaded.");
    }


    // initializes the objects
    this.initializeObjects();
  }


  /**
   * Initializes all sprites, timers, and walls. Moves some objects to a list to
   * be called and updated in the draw() method
   */
  public void initializeObjects() {

    labManager = LabyrinthManager.getInstance(20, 20, this);
    System.out.println("lab start " + labManager.getStart().getPosition().x  + " " + labManager.getStart().getPosition().y);

    enemies = new ArrayList<Enemy>();  //List of enemies, except ghost

    sprites = new ArrayList<Sprite>();  //List of all sprites

    wraiths = new ArrayList<Wraith>();  //List of all wraiths

    sporadics = new ArrayList<Sporadic>();  //List of all sporadics

    //walls = new ArrayList<Wall>();  //List of all walls that make up the Labyrinth

    timer = new Timer(this, new PVector(0,0));

    // blades
    blade1 = new Blade(new PVector(100, 100), new PVector(0, 0), 30, 0, Color.RED, this, 0.05f, 2);
    blade2 = new Blade(new PVector(200, 200), new PVector(0, 0), 30, 0, Color.RED, this, 0.05f, 2);
    blade3 = new Blade(new PVector(300, 300), new PVector(0, 0), 30, 0, Color.RED, this, 0.05f, 2);

    // holes
    holes = new ArrayList<>();
    Hole hole1 = new Hole(new PVector(200, 300), new PVector(0, 0), 50, 0, Color.BLACK, this);
    Hole hole2 = new Hole(new PVector(400, 500), new PVector(0, 0), 50, 0, Color.BLACK, this);
    Hole hole3 = new Hole(new PVector(600, 700), new PVector(0, 0), 50, 0, Color.BLACK, this);

    holes.add(hole1);
    holes.add(hole2);
    holes.add(hole3);

    //Initializes player object
    player = new Player(
        //new PVector(this.width/2,this.height/2),
        labManager.getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2),
        new PVector(0,0),
        playerSize,
        2,
        new Color(0,255,0),
        this, "Data/HPfront.png");
    System.out.println("player: " + player.getPosition().x  + " " + player.getPosition().y);

    //Initializes ghost object
    ghost = new Ghost(
        new PVector(this.width/3,this.height/3),
        new PVector(0,1),
        Ghost.GHOST_SIZE,
        0.3f,
        new Color(255,255,255),
        this, "Data/ghostRight.png");

    //Initializes all sporadic enemies and adds them to enemy array list
    for (int i = 0; i < numSporadics; i++) {
      sporadics.add(new Sporadic(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1,1)),
          Sporadic.SPORADIC_SIZE,
          5,
          new Color(255, 0, 0),
          this, "Data/sporadicSleep.png"
      ));
    }

    //Initializes all wraith enemies and adds them to enemy array list
    for (int i = 0; i < numWraiths; i++) {
      wraiths.add(new Wraith(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1,1)),
          Wraith.WRAITH_SIZE,
          2.5f,
          new Color(0, 0, 255),
          this, "Data/Wraithright.png"
      ));
    }
    sprites.add(player);  //Adds player to list of sprites
    enemies.addAll(wraiths);
    enemies.addAll(sporadics);
    sprites.addAll(enemies);  //Adds remaining enemies to list of sprites
    enemies.add(ghost);
    sprites.add(ghost);  //Adds ghost to list of sprites

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
        player.setHarryPotterImage(playerLeft);
        break;
      case RIGHT:
        // handle right
        player.setDirection(new PVector(2, 0));
        player.setHarryPotterImage(playerRight);
        break;
      case UP:
        // handle left
        player.setDirection(new PVector(0, -2));
        player.setHarryPotterImage(playerUp);
        break;
      case DOWN:
        // handle right
        player.setDirection(new PVector(0, 2));
        player.setHarryPotterImage(playerDown);
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
      case 'R':
        if (gameover){
          gameover = false;
          player.setImmunityTimer(1);
          player.setAlive(true);
          PVector newPos = new PVector(player.getPosition().x + 30, player.getPosition().y + 30);
          player.setPosition(newPos);
        }
        break;
      case 'T':
        enemies = new ArrayList<Enemy>();
        wraiths = new ArrayList<Wraith>();
        sporadics = new ArrayList<Sporadic>();
    }
  }


  /**
   * Called on every frame to update window objects. Objects
   * are drawn in order of appearance in this method.
   */
  public void draw() {

    image(backgroundImage, -1000, -1000, width*3, height*3);


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

    labManager.renderTiles();

    //Updates timer time and position in the window
    float timeElapsed = timer.getTime();
    text("Time elapsed: " + timeElapsed + " seconds", player.getPosition().x-width/2,player.getPosition().y- width/3);

    //Just updates and draws all sprites in the list
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }

    //draws the wraith image to every wraith
    for (Wraith wraith : wraiths) {
      image(wraith.getImage(), wraith.getPosition().x - wraith.WRAITH_LENGTH/2,
          wraith.getPosition().y - wraith.WRAITH_LENGTH/2 , wraith.WRAITH_LENGTH , wraith.WRAITH_LENGTH);
    }

    for (Sporadic sporadic : sporadics) {
      image(sporadic.getImage(), sporadic.getPosition().x - sporadic.SPORADIC_WIDTH/2,
          sporadic.getPosition().y - sporadic.SPORADIC_HEIGHT/3 , sporadic.SPORADIC_WIDTH , sporadic.SPORADIC_HEIGHT);
    }

    image(player.getHarryPotterImage(), player.getPosition().x - player.PLAYER_WIDTH/2,
        player.getPosition().y - player.PLAYER_HEIGHT/2, player.PLAYER_WIDTH , player.PLAYER_HEIGHT);


      // draw blades
      blade1.draw();
      blade2.draw();
      blade3.draw();

      // draw holes
      for (Hole hole : holes) {
        hole.draw();
        if (hole.collision(player)) {
          player.setFalling(true);
          break;
        }
      }

//      if (player.isFalling()) {
//        player.moveDown(.5F); // You need to define fallSpeed
//      }

      ghost.move(player); //This will follow the player everywhere they go
      image(ghost.getImage(), ghost.getPosition().x - ghost.GHOST_LENGTH/2,
        ghost.getPosition().y - ghost.GHOST_LENGTH/2 , ghost.GHOST_LENGTH , ghost.GHOST_LENGTH);

      //Moves multiple enemy sporadic and wraith types
      for (Enemy enemyList : enemies) {
        enemyList.move(player);
      }

    if (player.getImmunityTimer() > 0) {
      player.setImmunityTimer(player.getImmunityTimer() - ((float) 1 / FPS));
    }

    for (Enemy e : enemies) {
      if (player.collision(e) && player.getImmunityTimer() <= 0) {
        player.setAlive(false);
      }
    }

    if (player.isAlive()) {
      gameover = false;
    } else {
    background(0);
    textSize(50);
    text("Game Over!", cameraPos.x + width / 3, cameraPos.y + height / 2);
    text("Press R to restart.", cameraPos.x + width / 3, cameraPos.y + height / 2 + 50);
    gameover = true;
  }



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




