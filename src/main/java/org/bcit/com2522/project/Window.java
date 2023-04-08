package org.bcit.com2522.project;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.Database.Database;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


/**
 * The window class runs the display that initializes and displays all
 * other classes represented in the front-end of the maze game
 */
public class Window extends PApplet {

  //// Instance var for Textbox class////
  TextBox nameInput;
  SaveButton saveButton;
  boolean isTyping = false;

  PImage backgroundMainMenu;





  public static final int FPS = 144;



  /* AudioPlayer object for sound file */
  private AudioPlayer sound;



  /* Minim object for playing sound */
  private Minim minim;

  /* Length of window in pixels.*/
  public static final int WINDOW_X = 1000;

  /* Height of window in pixels.*/
  public static final int WINDOW_Y = 700;

  /* Captures the current state of the game. */

  String funFact;

  float playerAnimationTime;
  float elpCount = 0;
  int loadingTimer = 0;
//  MainMenu menu = new MainMenu();
//  PauseMenu pauseMenu = new PauseMenu();
//  ArrayList<Menu> menus = new ArrayList<Menu>();

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

    backgroundMainMenu = loadImage("Data/labyrinth.jpeg");

    frameRate(FPS);
    Player.getInstance();
    minim = new Minim(this);

    sound = minim.loadFile("sound/heroSong.mp3");

    // initializes the objects
    //this.initializeObjects();


  }


  /**
   * Initializes all sprites, timers, and walls. Moves some objects to a list to
   * be called and updated in the draw() method
   */
  public void initializeObjects() {
    GameManager.getInstance().setState(GameState.LOAD);

    LabyrinthManager.getInstance().newLabyrinth(LabyrinthManager.LAB_WIDTH,LabyrinthManager.LAB_HEIGHT);
  }

  /**
   * Whenever the user presses one of the arrow keys, the player object is moved
   * @param event
   */
  @Override
  public void keyPressed(KeyEvent event) {

      Player player = Player.getInstance();
      if (GameManager.getInstance().getState() == GameState.WIN && isTyping) {
        if (event.getKey() == BACKSPACE) {
          nameInput.removeChar();
        } else if (event.getKey() != ENTER && event.getKey() != RETURN) {
          nameInput.addChar(event.getKey());
        }
      }
      int keyCode = event.getKeyCode();
      playerAnimationTime = GameManager.getInstance().getTimer().getTime();

      switch (keyCode) {
        case LEFT:

          // handle left
          //player.setDirection(new PVector(-1, 0));
          player.getDirection().x = -1;
          player.getDirection().normalize();

          if (Math.round(playerAnimationTime * 5) % 2 == 0) {
            player.setHarryPotterImage(player.playerLeftWalk1);
          } else {
            player.setHarryPotterImage(player.playerLeft);
          }
          break;
        case RIGHT:

          // handle right
  //        player.setDirection(new PVector(1, 0));
          player.getDirection().x = 1;
          player.getDirection().normalize();

          if (Math.round(playerAnimationTime * 5) % 2 == 0) {
            player.setHarryPotterImage(player.playerRightWalk1);
          } else {
            player.setHarryPotterImage(player.playerRight);
          }
          break;
        case UP:

          // handle left
  //        player.setDirection(new PVector(0, -1));
          player.getDirection().y = -1;
          player.getDirection().normalize();

          if (Math.round(playerAnimationTime * 5) % 2 == 0) {

            player.setHarryPotterImage(player.playerUp1);
          } else {
            player.setHarryPotterImage(player.playerUp);
          }
          break;
        case DOWN:

          // handle down
  //        player.setDirection(new PVector(0, 1));
          player.getDirection().y = 1;
          player.getDirection().normalize();


          if (Math.round(playerAnimationTime * 5) % 2 == 0) {
            player.setHarryPotterImage(player.playerDown1);
          } else {
            player.setHarryPotterImage(player.playerDown);
          }
          break;
      }

  }

  /**
   * Whenever the user stops pressing one of the arrow keys, the player object stops moving
   * @param event
   */
  @Override
  public void keyReleased(KeyEvent event) {
    Player player = Player.getInstance();
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT:
        // stop moving left
        player.getDirection().x = 0;
        player.getDirection().normalize();

        player.setHarryPotterImage(player.playerLeft);
        break;
      case RIGHT:
        // stop moving right
        player.getDirection().x = 0;
        player.getDirection().normalize();

        player.setHarryPotterImage(player.playerRight);
        break;
      case UP:
        // stop moving up
        player.getDirection().y = 0;
        player.getDirection().normalize();

        player.setHarryPotterImage(player.playerUp);
        break;
      case DOWN:
        // stop moving down
        player.getDirection().y = 0;
        player.getDirection().normalize();

        player.setHarryPotterImage(player.playerDown);
        break;
      case 'R':
        if (GameManager.getInstance().getState() == GameState.GAMEOVER){
          GameManager.getInstance().setState(GameState.PLAY);
          player.setImmunityTimer(1);
          player.setAlive(true);
          player.setPosition(LabyrinthManager.getInstance().getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2));
          GameManager.getInstance().getTimer().resetTime();
        }
        break;
      case 'M':
        if (GameManager.getInstance().getState() == GameState.WIN || GameManager.getInstance().getState() == GameState.GAMEOVER);
        GameManager.getInstance().setState(GameState.MENU);
        MenuManager.getInstance().loadMainMenu();
        break;
      case 'P':
        if (GameManager.getInstance().getState() == GameState.PLAY){
          GameManager.getInstance().setState(GameState.PAUSE);
        }
    }
  }

  public void mouseClicked(MouseEvent m){
    for (Menu menu : MenuManager.getInstance().getMenus()){
      menu.click(m);
    }
    if (GameManager.getInstance().getState() == GameState.MENU) {
      MenuManager.getInstance().getMainMenu().click(m);
    } else if (GameManager.getInstance().getState() == GameState.WIN) {
      if (nameInput.contains(m.getX(), m.getY())) {
        isTyping = true;
      } else if (saveButton.contains(m.getX(), m.getY())) {
        Database.getInstance().saveCurrent(nameInput.getText());
        isTyping = false;
      } else {
        isTyping = false;
      }
    } else if (GameManager.getInstance().getState() == GameState.LOAD_ALL) {
      for (Button button : MenuManager.getInstance().getSavedButtons()) {
        if (button.cursorInside(m.getX(), m.getY())) {
          button.execute();
          break;
        }
      }
    } else {
      isTyping = false;
    }
  }


  /**
   * Called on every frame to update window objects. Objects
   * are drawn in order of appearance in this method.
   */
  public void draw() {
      GameManager.getInstance().runGame();
  }

  public void loadSavedMazes() {
    background(0);
    textSize(30);
    text("Load All Saved Mazes", width / 4, height / 8);
  }


  class SaveButton {
    PApplet parent;
    PVector position;
    int width, height;
    String label;

    SaveButton(PApplet parent, PVector position, int width, int height, String label) {
      this.parent = parent;
      this.position = position;
      this.width = width;
      this.height = height;
      this.label = label;
    }

    void draw() {
      parent.fill(0, 255, 0);
      parent.rect(position.x, position.y, width, height);
      parent.fill(255);
      parent.text(label, position.x + 5, position.y + height - 5);
    }

    boolean contains(int x, int y) {
      return x >= position.x && x <= position.x + width && y >= position.y && y <= position.y + height;
    }
  }

  /**
   * Updates the loading screen during loading.
   */
  public void load() {
    String loading = "Loading";
    loadingTimer++;
    if (loadingTimer % FPS == 0){
      elpCount++;
    }
    for (int i = 0; i < elpCount; i++){
      loading += ".";
    }
    fill(255);
    text(loading, width / 3, height / 2);
    textSize(30);
    text("Fun fact: " + funFact, width / 4, height / 2 + 50);
  }

  /**
   * Loads the game over screen.
   */
  public void loadGameOver() {
    background(0);
    fill(255);
    textSize(50);
    text("Game Over!", width / 3, height / 2);
    text("Press R to restart.", width / 3, height / 2 + 50);
    text("OR press M to return to menu", width / 3, height / 2 + 100);
  }

  /**
   * Loads win screen.
   */
  public void loadWin() {
    background(0);
    textSize(50);
    fill(255);
    text("You Won!!!", width / 3, height / 2);
    textSize(30);
    String time = String.format("%.1f", GameManager.getInstance().getTimeElapsed());
    text("Your time was " + time + " seconds!", width / 4, height / 2 + 50);
    text("If you wish to save your maze, name it below. Press M to return to menu", width / 3, height / 2 + 100);
    if (nameInput == null) {
      nameInput = new TextBox(this, new PVector(width / 3, height / 2 + 150), 200, 30);
    }
    nameInput.draw();

    if (saveButton == null) {
      saveButton = new SaveButton(this, new PVector(width / 3 + 210, height / 2 + 150), 150, 50, "Save Maze");
    }
    saveButton.draw();
  }


  public AudioPlayer getSound() {
    return sound;
  }
}



