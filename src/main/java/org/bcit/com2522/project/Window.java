package org.bcit.com2522.project;


import com.mongodb.client.FindIterable;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import org.bcit.com2522.project.Database.Database;
import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import org.bcit.com2522.project.traps.TrapManager;
import org.bson.Document;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;


/**
 * The window class runs the display that initializes and displays all
 * other classes represented in the front-end of the maze game
 */
public class Window extends PApplet {

  //// Instance var for Textbox class////
  TextBox nameInput;
  SaveButton saveButton;
  boolean isTyping = false;

  ArrayList<Button> savedMazeButtons = new ArrayList<Button>();


  private static final int FPS = 144;

  /* Minim object for playing sound */
  Minim minim;


  /* AudioPlayer object for sound file */
  AudioPlayer sound;

  /* Timer object that shows how long it takes player to complete the maze.*/
  private Timer timer;

  private Timer animationTimer;

  /*Player that is controlled by user to navigate maze.*/
  Player player;

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
    WIN,
    PAUSE,
    LOAD_ALL
  }

  String funFact;

  State state;

  float timeElapsed;
  float playerAnimationTime;
  float elpCount = 0;
  int loadingTimer = 0;
  MainMenu menu = new MainMenu(this);
  PauseMenu pauseMenu = new PauseMenu(this);
  ArrayList<Menu> menus = new ArrayList<Menu>();

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

    menus.add(menu);
    menus.add(pauseMenu);

    frameRate(FPS);

    // initializes the objects
    this.initializeObjects();
    state = State.MENU;
    menu.loadMenu();
  }


  /**
   * Initializes all sprites, timers, and walls. Moves some objects to a list to
   * be called and updated in the draw() method
   */
  public void initializeObjects() {
    state = State.LOAD;
    Database.getInstance();

    EnemyManager.getInstance(this);
    TrapManager.getInstance(this);
    player = Player.getInstance(this);


    LabyrinthManager.getInstance(20, 20, this);

  }

  /**
   * Whenever the user presses one of the arrow keys, the player object is moved
   * @param event
   */
  @Override
  public void keyPressed(KeyEvent event) {
    if (state == State.WIN && isTyping) {
      if (event.getKey() == BACKSPACE) {
        nameInput.removeChar();
      } else if (event.getKey() != ENTER && event.getKey() != RETURN) {
        nameInput.addChar(event.getKey());
      }
    }
    int keyCode = event.getKeyCode();
    playerAnimationTime = timer.getTime();
    switch( keyCode ) {
      case LEFT:

        // handle left
        //player.setDirection(new PVector(-1, 0));
        player.getDirection().x = -1;
        player.getDirection().normalize();

        if (Math.round(playerAnimationTime*5) % 2 == 0){
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

        if (Math.round(playerAnimationTime*5) % 2 == 0){
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

        if (Math.round(playerAnimationTime*5) % 2 == 0){

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



        if (Math.round(playerAnimationTime*5) % 2 == 0){
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
        if (state == State.GAMEOVER){
          state = State.PLAY;
          player.setImmunityTimer(1);
          player.setAlive(true);
          player.setPosition(LabyrinthManager.getInstance().getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2));
        }
        break;
      case 'M':
        if (state == State.WIN || state == State.GAMEOVER);
        state = state.MENU;
        menu.loadMenu();
        break;
      case 'P':
        if (state == State.PLAY){
          state = state.PAUSE;
        }
    }
  }

  public void mouseClicked(MouseEvent m){
    for (Menu menu : menus){
      menu.click(m);
    }
    if (state == State.MENU) {
      menu.click(m);
    } else if (state == State.WIN) {
      if (nameInput.contains(m.getX(), m.getY())) {
        isTyping = true;
      } else if (saveButton.contains(m.getX(), m.getY())) {
        Database.getInstance().saveCurrent(nameInput.getText());
        isTyping = false;
      } else {
        isTyping = false;
      }
    } else if (state == State.LOAD_ALL) {
      for (Button button : savedMazeButtons) {
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

    switch (state) {
      case MENU:
        menu.loadMenu();
        menu.draw();
        break;
      case LOAD:
        timer = null;
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
        fill(255);
        text(loading, width / 3, height / 2);
        textSize(30);
        text("Fun fact: " + funFact, width / 4, height / 2 + 50);
        if (!(LabyrinthManager.getInstance().isGenerating()) && (state == State.LOAD)) {
          player.setPosition(LabyrinthManager.getInstance().getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2));
          state = State.PLAY;
          EnemyManager.getInstance().spawnGhost();
        }
        break;

      case PLAY:
        if (timer == null) {
          timer = new Timer(this, new PVector(0, 0));
        }
        background(0);
        //image(backgroundImage, -1000, -1000, width*3, height*3);
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
        LabyrinthManager.getInstance().renderTiles();

        //Updates timer time and position in the window
        timeElapsed = timer.getTime();
        String currTime = String.format("%.1f", timeElapsed);
        text("Time elapsed: " + currTime + " seconds", player.getPosition().x-width/2,player.getPosition().y- width/3);

        sound.play();

        EnemyManager.getInstance().spawn();
        EnemyManager.getInstance().draw();
        TrapManager.getInstance().draw();

        EnemyManager.getInstance().collision(player);
        TrapManager.getInstance().collision(player);

        //Just updates and draws all sprites in the list
        player.update();
        player.draw();


        if (player.getImmunityTimer() > 0) {
          player.setImmunityTimer(player.getImmunityTimer() - ((float) 1 / FPS));
        }

        if (!(player.isAlive())){
          state = State.GAMEOVER;
        }
        if (LabyrinthManager.getInstance().getEnd().collision(player)){
          state = State.WIN;
        }
        break;

      case GAMEOVER:
        background(0);
        fill(255);
        textSize(50);
        text("Game Over!", width / 3, height / 2);
        text("Press R to restart.", width / 3, height / 2 + 50);
        text("OR press M to return to menu", width / 3, height / 2 + 100);
        sound.pause();
        break;

      case WIN:
        sound.pause();
        background(0);
        textSize(50);
        fill(255);
        text("You Won!!!", width / 3, height / 2);
        textSize(30);
        String time = String.format("%.1f", timeElapsed);
        text("Your time was " + time + " seconds!", width / 4, height / 2 + 50);
        text("Press M to return to menu", width / 3, height / 2 + 100);
        if (nameInput == null) {
          nameInput = new TextBox(this, new PVector(width / 3, height / 2 + 150), 200, 30);
        }
        nameInput.draw();

        if (saveButton == null) {
          saveButton = new SaveButton(this, new PVector(width / 3 + 210, height / 2 + 150), 100, 30, "Save Maze");
        }
        saveButton.draw();
        break;

      case PAUSE:
        sound.pause();
        pauseMenu.loadMenu();
        pauseMenu.draw();
        break;



      case LOAD_ALL:
        background(0);
        textSize(30);
        text("Load All Saved Mazes", width / 4, height / 8);
        int buttonIndex = 0;
        FindIterable<Document> savedMazes = Database.getInstance().loadAll();
        savedMazeButtons = new ArrayList<>();
        for (Document maze : savedMazes) {
          String mazeName = maze.getString("name");
          // You can customize the button layout (e.g., the position, size, and style) here
          float buttonX = width / 4;
          float buttonY = height / 4 + buttonIndex * 50;
          Button mazeButton = new Button(mazeName, buttonX, buttonY, 300, 40, Color.BLUE, this, menu);
          mazeButton.config(() -> {Database.getInstance().loadLabyrinth(mazeName);
          this.state = State.LOAD;});
          mazeButton.draw();
          savedMazeButtons.add(mazeButton);
          buttonIndex++;
        }
        break;

    }
  }


  ////////////// Text Box class //////////////
   class TextBox {
    PApplet parent;
    PVector position;
    int width, height;
    String text = "";

    TextBox(PApplet parent, PVector position, int width, int height) {
      this.parent = parent;
      this.position = position;
      this.width = width;
      this.height = height;
    }

    void draw() {
      parent.fill(255);
      parent.rect(position.x, position.y, width, height);
      parent.fill(0);
      parent.text(text, position.x + 5, position.y + height - 5);
    }

    public void mouseClicked(MouseEvent m) {
      if (saveButton.contains(m.getX(), m.getY())) {
        if (!text.trim().isEmpty()) { // This ensures that the text isn't empty or only spaces
          Database.getInstance().saveCurrent(text);
          isTyping = false;
        }
      }
    }

    boolean contains(int x, int y) {
      return x >= position.x && x <= position.x + width && y >= position.y && y <= position.y + height;
    }

    void addChar(char c) {
      text += c;
    }

    void removeChar() {
      if (text.length() > 0) {
        text = text.substring(0, text.length() - 1);
      }
    }

    String getText() {
      return text;
    }
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



