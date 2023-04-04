package org.bcit.com2522.project;

import com.mongodb.client.FindIterable;
import org.bcit.com2522.project.Database.Database;
import org.bson.Document;

import java.awt.*;
import java.util.ArrayList;

public class MenuManager {

  private static MenuManager instance;

  private MainMenu mainMenu;
  private PauseMenu pauseMenu;
  private ArrayList<Button> savedMazeButtons = new ArrayList<>();
  private ArrayList<Menu> menus;

  private MenuManager(){
    mainMenu = new MainMenu();
    pauseMenu = new PauseMenu();
    menus = new ArrayList<>();

    menus.add(mainMenu);
    menus.add(pauseMenu);
  }

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


  public void loadMainMenu() {
    mainMenu.loadMenu();
    mainMenu.draw();
  }


  public void loadPauseMenu() {
    pauseMenu.loadMenu();
    pauseMenu.draw();
  }

  public void loadSavedMazeButtons() {
    int buttonIndex = 0;
    FindIterable<Document> savedMazes = Database.getInstance().loadAll();
    savedMazeButtons = new ArrayList<>();
    for (Document maze : savedMazes) {
      String mazeName = maze.getString("name");
      // You can customize the button layout (e.g., the position, size, and style) here
      float buttonX = GameManager.getInstance().window.width / 4f;
      float buttonY = GameManager.getInstance().window.height / 4f + buttonIndex * 80;
      Button mazeButton = new Button(mazeName, buttonX, buttonY, 300, 80, Color.BLUE, mainMenu);
      mazeButton.config(() -> {Database.getInstance().loadLabyrinth(mazeName);
        GameManager.getInstance().setState(GameState.LOAD);});
      mazeButton.draw();
      savedMazeButtons.add(mazeButton);
      buttonIndex++;
    }
  }
}
