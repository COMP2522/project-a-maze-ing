package org.bcit.com2522.project;

import com.mongodb.client.FindIterable;
import org.bcit.com2522.project.Database.Database;
import org.bson.Document;
import java.awt.*;
import java.util.ArrayList;

/**
 * The MenuManager class is responsible for managing menus in the game.
 * It is a singleton class that maintains instances of the main menu and pause menu,
 * as well as any other menus added to the game.
 * The MenuManager can load and draw menus and their associated buttons,
 * and return lists of saved maze buttons.
 * @author Matthew Siggs
 * @author Laurie Solkoski
 * @version 1.0
 */
public class MenuManager {

  /*
  The singleton instance of the MenuManager.
   */
  private static MenuManager instance;

  /*
  The main menu for the game.
   */
  private MainMenu mainMenu;

  /*
  The pause menu for the game.
   */
  private PauseMenu pauseMenu;

  /*
  The list of saved maze buttons.
   */
  private ArrayList<Button> savedMazeButtons = new ArrayList<>();

  /*
  The list of menus managed by the MenuManager.
   */
  private ArrayList<Menu> menus;

  /*
  * Private constructor for the MenuManager singleton.
  * Initializes the main menu, pause menu, and menus list.
   */
  private MenuManager(){
    mainMenu = new MainMenu();
    pauseMenu = new PauseMenu();
    menus = new ArrayList<>();
    menus.add(mainMenu);
    menus.add(pauseMenu);
  }

  /**
   * Returns the singleton instance of the MenuManager.
   * @return the singleton instance of the MenuManager
   */
  public static MenuManager getInstance() {
    if (instance == null) {
      instance = new MenuManager();
    }
    return instance;
  }

  /**
   * returns the saved maze buttons.
   * @return the list of saved maze buttons.
   */
  public ArrayList<Button> getSavedButtons() {

    return savedMazeButtons;
  }

  /**
   * Returns the list of menus managed by the MenuManager.
   * @return the list of menus managed by the MenuManager
   */
  public ArrayList<Menu> getMenus() {

    return menus;
  }

  /**
   * Returns the main menu.
   * @return the main menu
   */
  public MainMenu getMainMenu() {

    return mainMenu;
  }

  public void loadMainMenu() {
    mainMenu.loadMenu();
    mainMenu.draw();
  }


  public void loadPauseMenu() {
    pauseMenu.loadMenu();
    pauseMenu.draw();
  }

  /*
   * Common button creation logic has been extracted and placed here.
   */
  private Button createButton(String text, float x, float y, int width, int height, Color color, Menu parentMenu) {
    Button button = new Button(text, x, y, width, height, color, parentMenu);
    button.draw();
    return button;
  }


  /**
   * Loads saved maze buttons and draws them to the screen.
   * The saved maze buttons are loaded from the database using the Database class.
   */
  public void loadSavedMazeButtons() {
    int buttonIndex = 0;
    FindIterable<Document> savedMazes = Database.getInstance().loadAll();
    savedMazeButtons = new ArrayList<>();

    for (Document maze : savedMazes) {
      addMazeButton(maze, buttonIndex);
      buttonIndex++;
    }

    addBackButton();
  }

  /**
   * Adds a button representing a saved maze to the saved maze menu.
   * @param maze        the saved maze document containing maze information
   * @param buttonIndex the index of the button for positioning
   */
  private void addMazeButton(Document maze, int buttonIndex) {
    String mazeName = maze.getString("name");
    float buttonX = GameManager.getInstance().window.width / 4f;
    float buttonY = GameManager.getInstance().window.height / 4f + buttonIndex * 80;
    Button mazeButton = createButton(mazeName, buttonX, buttonY, 300, 80, Color.BLUE, mainMenu);

    mazeButton.config(() -> {
      Database.getInstance().loadLabyrinth(mazeName);
      GameManager.getInstance().setState(GameState.LOAD);
    });

    savedMazeButtons.add(mazeButton);
  }

  /*
   * Adds a back button to return to the main menu from the saved maze menu.
   */
  private void addBackButton() {
    float backButtonX = GameManager.getInstance().window.width / 2f;
    float backButtonY = GameManager.getInstance().window.height * 3f / 4f;
    Button backButton = createButton("Back to Main Menu", backButtonX, backButtonY, 500, 80, Color.BLUE, mainMenu);

    backButton.config(() -> mainMenu.returnToMainMenu());
    savedMazeButtons.add(backButton);
  }

}