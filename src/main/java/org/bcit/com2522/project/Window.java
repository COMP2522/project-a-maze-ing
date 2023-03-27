package org.bcit.com2522.project;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.enemy.Enemy;
import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import org.bcit.com2522.project.traps.TrapManager;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;


/**
 * The window class runs the display that initializes and displays all
 * other classes represented in the front-end of the maze game
 */
public class Window extends PApplet {


  private static final int FPS = 60;

  /* Minim object for playing sound */
  Minim minim;

  /* Manager for the labyrinth */
  private LabyrinthManager labManager;

  private EnemyManager enemyManager;
  private TrapManager trapManager;


  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  /* Timer object that shows how long it takes player to complete the maze.*/
  private Timer timer;


  /* List of all in-game sprites to be loaded. */
  ArrayList<Sprite> sprites;

  /* List of all in-game enemies to be loaded. */
  ArrayList<Enemy> enemies;

  /*Player that is controlled by user to navigate maze.*/
  Player player;

  PImage playerDown;

  PImage playerLeft;
  PImage playerRight;
  PImage playerUp;

  PImage backgroundImage; //Background Image for the Window


  /* Length of window in pixels.*/
  public static final int WINDOW_X = 800;

  /* Height of window in pixels.*/
  public static final int WINDOW_Y = 600;

  /* Captures the current state of the game. */
  enum State{
    MENU,
    LOAD,
    GAMEOVER,
    PLAY,
    WIN
  }

  String funFact;

  State state;

  float timeElapsed;
  float elpCount = 0;
  int loadingTimer = 0;

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
    state = State.LOAD;

    enemyManager = EnemyManager.getInstance(this);
    trapManager = TrapManager.getInstance();

    labManager = LabyrinthManager.getInstance(20, 20, this);

    trapManager = TrapManager.getInstance();


    sprites = new ArrayList<Sprite>();  //List of all sprites

    //Initializes player object
    player = Player.getInstance(this);



//    //Initializes ghost object
//    ghost = new Ghost(
//        new PVector(this.width/3,this.height/3),
//        new PVector(0,1),
//        Ghost.GHOST_SIZE,
//        0.3f,
//        new Color(255,255,255),
//        this, "Data/ghostRight.png");

//    //Initializes all sporadic enemies and adds them to enemy array list
//    for (int i = 0; i < numSporadics; i++) {
//      sporadics.add(new Sporadic(
//          new PVector(random(0, this.width), random(0, this.height)),
//          new PVector(random(-1, 1), random(-1,1)),
//          Sporadic.SPORADIC_SIZE,
//          5,
//          new Color(255, 0, 0),
//          this, "Data/sporadicSleep.png"
//      ));
//    }

    sprites.add(player);  //Adds player to list of sprites

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
        if (state == State.GAMEOVER){
          state = State.PLAY;
          player.setImmunityTimer(1);
          player.setAlive(true);
          player.setPosition(labManager.getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2));
        }
        break;
      case 'T':
        enemies = new ArrayList<Enemy>();
    }
  }


  /**
   * Called on every frame to update window objects. Objects
   * are drawn in order of appearance in this method.
   */
  public void draw() {

    if (!(labManager.isGenerating()) && (state == State.LOAD)) {
      player.setPosition(labManager.getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2));
      state = State.PLAY;
    }

    switch (state) {
      case LOAD:
        background(0);
        textSize(50);
        if (funFact == null) {
          funFact = QuoteGenerator.getQuote();
        }
        String loading = "Loading";
        loadingTimer++;
        if (loadingTimer % FPS == 0){
          elpCount++;
        }
        for (int i = 0; i < elpCount; i++){
          loading += ".";
        }
        text(loading, width / 3, height / 2);
        textSize(30);
        text("Fun fact: " + funFact, width / 4, height / 2 + 50);
        break;

      case PLAY:

        if (timer == null){
          timer = new Timer(this, new PVector(0, 0));
        }
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

        // renders all tiles in labyrinth
        labManager.renderTiles();

        //Updates timer time and position in the window
        timeElapsed = timer.getTime();
        String currTime = String.format("%.1f", timeElapsed);
        text("Time elapsed: " + currTime + " seconds", player.getPosition().x-width/2,player.getPosition().y- width/3);

    enemyManager.spawn();
    enemyManager.draw();
    trapManager.draw();

    enemyManager.collision(player);
    trapManager.collision(player);

        //Just updates and draws all sprites in the list
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }

//    for (Sporadic sporadic : sporadics) {
//      image(sporadic.getImage(), sporadic.getPosition().x - sporadic.SPORADIC_WIDTH/2,
//          sporadic.getPosition().y - sporadic.SPORADIC_HEIGHT/3 , sporadic.SPORADIC_WIDTH , sporadic.SPORADIC_HEIGHT);
//    }

        //Moves multiple enemy sporadic and wraith types
//        for (Enemy enemyList : enemies) {
//          enemyList.move(player);
//        }

        if (player.getImmunityTimer() > 0) {
          player.setImmunityTimer(player.getImmunityTimer() - ((float) 1 / FPS));
        }

        if (!(player.isAlive())){
          state = State.GAMEOVER;
        }
        if (labManager.getEnd().collision(player)){
          state = State.WIN;
        }
        break;

      case GAMEOVER:
        // Calculate the camera position based on the player's position
        cameraPos = new PVector(
            player.getPosition().x - width / 2,
            player.getPosition().y - height / 2);
        // Translate the drawing surface to the camera position
        translate(-cameraPos.x, -cameraPos.y);
        background(0);
        textSize(50);
        text("Game Over!", cameraPos.x + width / 3, cameraPos.y + height / 2);
        text("Press R to restart.", cameraPos.x + width / 3, cameraPos.y + height / 2 + 50);
        break;

      case WIN:
        background(0);
        textSize(50);
        text("You Won!!!", width / 3, height / 2);
        textSize(30);
        String time = String.format("%.1f", timeElapsed);
        text("Your time was " + time + " seconds!", width / 4, height / 2 + 70);
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




