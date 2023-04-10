package org.bcit.com2522.project.labyrinth;

import org.bcit.com2522.project.GameManager;
import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.labyrinth.Tiles.*;
import org.bson.Document;
import processing.core.PVector;

import java.util.ArrayList;

public class LabyrinthManager {

  private static LabyrinthManager instance;

  private Labyrinth current;

  public static final int LAB_WIDTH = 20;
  public static final int LAB_HEIGHT = 20;

  private ArrayList<Tile> tiles;
  private ArrayList<EmptyPathTile> pathTiles;

  /**
   * The StartTile of the current labyrinth.
   */
  private StartTile start;

  /**
   * The end tile of the current labyrinth.
   */
  private EndTile end;

  /**
   * Locking boolean used for thread management.
   */
  private boolean generating = false;

  /**
   * Constructor.
   */
  private LabyrinthManager() {}

  /**
   * Empties the tiles and pathTiles lists.
   */
  public void resetTiles() {
    tiles = new ArrayList<Tile>();
    pathTiles = new ArrayList<EmptyPathTile>();
  }

  /**
   * returns the labyrinth manager if one exists, creates a new one with given size and returns if one doesn't exist.
   * @return the labyrinthManager instance.
   */
  public static LabyrinthManager getInstance() {
    if(instance == null) {
      instance = new LabyrinthManager();
    }
    return instance;
  }

  /**
   * generates the current labyrinth into the window. starts at PVector (0, 0).
   * should only be called once per labyrinth.
   */
  public void generateTiles() {
    resetTiles();
    TileType[][] tileList = current.getTiles();

    PVector location = new PVector(0, 0);

                                                          //render outer wall of labyrinth
    // top row
    for (int j = 0; j < tileList[0].length + 2; j++) {
      addTile(location.copy(), TileType.WALL);
      location.add(Tile.TILE_SIZE, 0);
    }

    // middle rows
    for(int i = 1; i < tileList.length + 1; i++) {
      location.set(0, i * Tile.TILE_SIZE);
      addTile(location.copy(), TileType.WALL);
      location.set((tileList[0].length + 1) * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
      addTile(location.copy(), TileType.WALL);
    }


    //bottom row
    location.set(0, (tileList.length + 1) * Tile.TILE_SIZE);

    for (int j = 0; j < tileList[0].length + 2; j++) {
      addTile(location.copy(), TileType.WALL);
      location.add(Tile.TILE_SIZE, 0);
    }


                                                          //render labyrinth contents
    //start at position (tile_size, tile_size) to account for outer wall
    location.set(Tile.TILE_SIZE, Tile.TILE_SIZE);
    for (int i = 0; i < tileList.length; i++) {
      for (int j = 0; j < tileList[0].length; j++) {
        //render tile
        addTile(location.copy(), tileList[i][j]);

        //move to next place in row
        location.add(Tile.TILE_SIZE, 0);
      }
      //set location to start of next row
      location.set(Tile.TILE_SIZE, (i + 2) * Tile.TILE_SIZE);
    }
    System.out.println("generation done");
  }

  /**
   * renders given tile type at given location.
   * @param pos the position to render the tile
   * @param type the type of tile to render
   */
  private void addTile(PVector pos, TileType type) {
    switch (type) {
      case WALL:
        Wall w = new Wall(pos);
        tiles.add(w);
        WallManager.getInstance().add(w);
        break;
      case PATH:
        EmptyPathTile path = new EmptyPathTile(pos);
        tiles.add(path);
        pathTiles.add(path);
        break;
      case END:
        end = new EndTile(pos);
        tiles.add(end);
        break;
      case START:
        start = new StartTile(pos);
        tiles.add(start);
        break;
      case WRAITH:
        WraithTile wraithPath = new WraithTile(pos);
        tiles.add(wraithPath);
        pathTiles.add(wraithPath);
        break;
      case SPORADIC:
        SporadicTile sporadicPath = new SporadicTile(pos);
        tiles.add(sporadicPath);
        pathTiles.add(sporadicPath);
        break;
      case BLADE_TILE:
        BladeTile bladePath = new BladeTile(pos);
        tiles.add(bladePath);
        pathTiles.add(bladePath);
        break;
      case HOLE_TILE:
        HoleTile holePath = new HoleTile(pos);
        tiles.add(holePath);
        pathTiles.add(holePath);
      default:
        break;
    }
  }

  /**
   * Renders tiles into the window.
   */
  public void renderTiles() {
    for (Tile t : tiles){
      t.draw();
    }
  }

  /**
   * generates a new labyrinth with given width and height, and sets it as current labyrinth.
   * @param width - the width of the new labyrinth
   * @param height - the height of the new labyrinth
   */
  public void newLabyrinth(int width, int height) {
    GameManager.clearAll();

    current = new Labyrinth(width, height);
    current.print();
    generating = true;
    new Thread(() -> {generateTiles(); generating = false;}).start();
  }

  /**
   * loads a existing labyrinth from the database into the manager.
   * @param loadTarget the labyrinth bson object to load.
   */
  public void loadLabyrinth(Document loadTarget) {

    // clear all managers
    GameManager.clearAll();

    ArrayList<ArrayList<String>> t = (ArrayList<ArrayList<String>>) loadTarget.get("tiles");
    TileType[][] layout = new TileType[t.size()][t.get(0).size()];

    for(int i = 0; i < t.size(); i++) {
      for(int j = 0; j < t.get(0).size(); j++) {
        layout[i][j] = TileType.valueOf(t.get(i).get(j));
      }
    }
    current = new Labyrinth(layout);
    generateTiles();
    current.print();
    Player.getInstance().setPosition(LabyrinthManager.getInstance().getStart().getPosition().add(Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2));
  }



  /**
   * Getter for tile list.
   * @return The Tiles arraylist.
   */
  public ArrayList<Tile> getTiles() {
    return tiles;
  }

  /**
   * returns the list of tiles from the current labyrinth.
   * @return the list of tiles
   */
  public TileType[][] getTileList(){return current.getTiles();}

  /**
   * Getter for start tile.
   * @return the start tile
   */
  public StartTile getStart() {
    return start;
  }

  /**
   * getter for end tile.
   * @return the end tile.
   */
  public EndTile getEnd() {
    return end;
  }

  public boolean isGenerating() {
    return generating;
  }

  public boolean collision(Sprite s){
    return WallManager.getInstance().collision(s);
  }


}
