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
 * other classes represented in the front-end of the maze game.
 * @author Nelson Peterson-Hui
 * @author Matthew Siggs
 * @author Laurie Solkoski
 * @author Alex Sichitiu
 * @version 1.0
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


  /**
   * Handles mouse click events in different game states.
   * Handles mouse click events based on the current game state.
   * Calls the appropriate handler method based on the game state.
   *
   * @param m the MouseEvent containing the details of the mouse click event
   */
  public void mouseClicked(MouseEvent m){
    for (Menu menu : MenuManager.getInstance().getMenus()){
      menu.click(m);
    }

    GameState currentState = GameManager.getInstance().getState();
    if (currentState == GameState.MENU) {
      handleMainMenuClick(m);
    } else if (currentState == GameState.WIN) {
      handleWinStateClick(m);
    } else if (currentState == GameState.LOAD_ALL) {
      handleLoadAllStateClick(m);
    } else {
      isTyping = false;
    }
  }

  /**
   * Handles mouse click events in the main menu state.
   * Trigger actions associated with the main menu.
   * @param m the MouseEvent containing the details of the mouse click event
   */
  private void handleMainMenuClick(MouseEvent m) {
    MenuManager.getInstance().getMainMenu().click(m);
  }

  /**
   * Handles mouse click events in the win state.
   * Trigger actions such as enabling typing for name input, saving the game,
   * or disabling typing based on the clicked UI element.
   * @param m the MouseEvent containing the details of the mouse click event
   */
  private void handleWinStateClick(MouseEvent m) {
    if (nameInput.contains(m.getX(), m.getY())) {
      isTyping = true;
    } else if (saveButton.contains(m.getX(), m.getY())) {
      Database.getInstance().saveCurrent(nameInput.getText());
      isTyping = false;
    } else {
      isTyping = false;
    }
  }

  /**
   * Handles mouse click events in the load all state.
   * Triggers the execution of the clicked saved game button.
   *
   * @param m the MouseEvent containing the details of the mouse click event
   */
  private void handleLoadAllStateClick(MouseEvent m) {
    for (Button button : MenuManager.getInstance().getSavedButtons()) {
      if (button.cursorInside(m.getX(), m.getY())) {
        button.execute();
        break;
      }
    }
  }



  /**
   * Called on every frame to update window objects. Objects
   * are drawn in order of appearance in this method.
   */
  public void draw() {
      GameManager.getInstance().runGame();
  }

  /*
   * Displays the "Load All Saved Mazes" text on the screen.
   */
  public void loadSavedMazes() {
    background(0);
    textSize(30);
    text("Load All Saved Mazes", width / 4, height / 8);
  }


  /*
   * SaveButton class represents a clickable button for saving a maze.
   */
  class SaveButton {
    PApplet parent;
    PVector position;
    int width, height;
    String label;

    /**
     * Constructs a SaveButton with the specified parameters.
     * @param parent The PApplet instance where this button is drawn.
     * @param position The position of the button on the screen.
     * @param width The width of the button.
     * @param height The height of the button.
     * @param label The text displayed on the button.
     */
    SaveButton(PApplet parent, PVector position, int width, int height, String label) {
      this.parent = parent;
      this.position = position;
      this.width = width;
      this.height = height;
      this.label = label;
    }

    /*
     * Draws the button on the screen.
     */
    void draw() {
      parent.fill(0, 255, 0);
      parent.rect(position.x, position.y, width, height);
      parent.fill(255);
      parent.text(label, position.x + 5, position.y + height - 5);
    }


    /**
     * Checks if the given coordinates are within the bounds of the button.
     * @param x The x-coordinate of the point to be checked.
     * @param y The y-coordinate of the point to be checked.
     * @return true if the point is within the button bounds, false otherwise.
     */
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
    drawBackground();
    drawWinMessage();
    drawTimeElapsed();
    drawSaveInstructions();
    drawNameInput();
    drawSaveButton();
  }

  /*
   * Draws the background of the screen in black color.
   */
  private void drawBackground() {
    background(0);
  }

  /*
   * Draws the win message in white color with a font size of 50.
   * The position of the message is set using the width and height variables of the Processing sketch.
   */
  private void drawWinMessage() {
    textSize(50);
    fill(255);
    text("You Won!!!", width / 3, height / 2);
  }

  /*
   * Draws the elapsed time on the screen in white color with a font size of 30.
   * The elapsed time is obtained from the GameManager instance and formatted
   * as a string with one decimal place. The position of the time display is set using
   * the width and height variables of the Processing sketch.
   */
  private void drawTimeElapsed() {
    textSize(30);
    String time = String.format("%.1f", GameManager.getInstance().getTimeElapsed());
    text("Your time was " + time + " seconds!", width / 4, height / 2 + 50);
  }

  /*
   * Draws the save instructions on the screen in white color with a font size of 30.
   * The position of the instructions is set using the width and height variables of the Processing sketch.
   */
  private void drawSaveInstructions() {
    textSize(30);
    text("If you wish to save your maze, name it below. Press M to return to menu", width / 3, height / 2 + 100);
  }

  /*
   * Draws a text box for the user to enter a name to save the maze.
   * The position of the text box is set using the width and height variables of the Processing sketch.
   */
  private void drawNameInput() {
    if (nameInput == null) {
      nameInput = new TextBox(this, new PVector(width / 3, height / 2 + 150), 200, 30);
    }
    nameInput.draw();
  }

  /*
   * Draws a button labeled "Save Maze" that the user can click to save the maze.
   * The position of the button is set using the width and height variables of the Processing sketch.
   */
  private void drawSaveButton() {
    if (saveButton == null) {
      saveButton = new SaveButton(this, new PVector(width / 3 + 210, height / 2 + 150), 150, 50, "Save Maze");
    }
    saveButton.draw();
  }

  /**
   * Returns the AudioPlayer object for the sound file.
   * @return AudioPlayer object for the sound file
   */
  public AudioPlayer getSound() {
    return sound;
  }
}



