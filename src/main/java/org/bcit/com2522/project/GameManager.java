package org.bcit.com2522.project;

import org.bcit.com2522.project.Database.Database;
import org.bcit.com2522.project.enemy.EnemyManager;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import org.bcit.com2522.project.labyrinth.Tiles.WallManager;
import org.bcit.com2522.project.traps.TrapManager;
import processing.core.PApplet;
import processing.core.PVector;

public class GameManager {

  private static GameManager instance;
  private GameState state;

  public Window window;



  private Timer timer;




  private float timeElapsed;

  private GameManager() {
    window = new Window();


  }

  private static void initMenus() {
    MenuManager.getInstance();
    EnemyManager.getInstance();
    TrapManager.getInstance();
    LabyrinthManager.getInstance();
    Database.getInstance();


    GameManager.getInstance().setState(GameState.MENU);

  }

  /**
   * gets the gameManager instance.
   * @return the gameManager.
   */
  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
      initMenus();
    }
    return instance;
  }

  /**
   * clears all enemies, tiles, traps and walls from the game.
   */
  public static void clearAll() {
    TrapManager.getInstance().clearTraps();
    EnemyManager.getInstance().clearEnemies();
    LabyrinthManager.getInstance().resetTiles();
    WallManager.getInstance().clearWalls();
  }

  /**
   * checks the game state and runs the code of the current state.
   */
  public void runGame() {
    switch(state) {
      case MENU:
        loadMainMenu();
        break;
      case LOAD:
        loadLoadScreen();
        break;
      case PLAY:
        play();
        break;
      case GAMEOVER:
        loadGameOverMenu();
        break;
      case WIN:
        loadWinMenu();
        break;
      case PAUSE:
        loadPauseMenu();
        break;
      case LOAD_ALL:
        loadSaved();
        break;
    }
  }

  /**
   * gets the current state of the game.
   * @return the current game state.
   */
  public GameState getState() {
    return state;
  }

  /**
   * sets the game state to given state;
   * @param state the new state.
   */
  public void setState(GameState state) {
    this.state = state;
  }

  /**
   * returns the timer.
   * @return the timer.
   */
  public Timer getTimer() {
    return timer;
  }

  public float getTimeElapsed() {
    return timeElapsed;
  }


  /**
   * loads the main menu onto the screen.
   */
  public void loadMainMenu(){
    MenuManager.getInstance().loadMainMenu();
  }

  /**
   * draws the loading screen.
   */
  public void loadLoadScreen() {
    timer = null;
    window.background(0);
    window.textSize(50);
    if (window.funFact == null) {
      window.funFact = QuoteGenerator.getQuote();
    }

    window.load();

    if (!(LabyrinthManager.getInstance().isGenerating()) && (GameManager.getInstance().getState() == GameState.LOAD)) {
        Player.getInstance().setPosition(
          LabyrinthManager.getInstance().
              getStart().getPosition()
              .add(Tile.TILE_HALF_LENGTH, Tile.TILE_HALF_LENGTH)
      );
        GameManager.getInstance().setState(GameState.PLAY);
        EnemyManager.getInstance().spawnGhost();
    }
  }

  public void play() {
    Player player = Player.getInstance();

    if (timer == null) {
      timer = new Timer(window);
    }
    window.background(0);
    PVector cameraPos = new PVector(
        player.getPosition().x - window.width / 2,
        player.getPosition().y - window.height / 2);
    // Translate the drawing surface to the camera position
    window.translate(-cameraPos.x, -cameraPos.y);

    // renders all tiles in labyrinth
    LabyrinthManager.getInstance().renderTiles();


    window.getSound().play();

    EnemyManager.getInstance().spawn();
    EnemyManager.getInstance().draw();
    TrapManager.getInstance().draw();

    //Updates timer time and position in the window
    timeElapsed = timer.getTime();
    String currTime = String.format("%.1f", timeElapsed);
    window.textSize(40);
    window.text("Time elapsed: " + currTime + " seconds",
        player.getPosition().x - window.width/2,player.getPosition().y - window.width/3);
    if (timeElapsed >= 30){
      EnemyManager.getInstance().makeHyperGhost();
    }

    EnemyManager.getInstance().collision(player);
    TrapManager.getInstance().collision(player);

    //Just updates and draws player
    player.update();
    player.draw();


    if (player.getImmunityTimer() > 0) {
      player.setImmunityTimer(player.getImmunityTimer() - ((float) 1 / window.FPS));
    }

    if (!(player.isAlive())){
      GameManager.getInstance().setState(GameState.GAMEOVER);
    }
    if (LabyrinthManager.getInstance().getEnd().collision(player)){
      GameManager.getInstance().setState(GameState.WIN);
    }
  }

  /**
   * Runs game over code.
   */
  public void loadGameOverMenu() {
    window.getSound().pause();
    window.loadGameOver();
  }

  public void loadWinMenu() {
    window.getSound().pause();
    window.loadWin();
  }

  public void loadPauseMenu() {
    window.getSound().pause();
    MenuManager.getInstance().loadPauseMenu();
  }

  public void loadSaved() {
    window.loadSavedMazes();
    MenuManager.getInstance().loadSavedMazeButtons();
  }


  /**
   * Main function to run the entire game.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"find the End"};
    GameManager.getInstance();
    PApplet.runSketch(appletArgs, GameManager.getInstance().window);
  }
}
