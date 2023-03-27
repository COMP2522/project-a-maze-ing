package org.bcit.com2522.project.labyrinth;

import org.bcit.com2522.project.Sprite;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.labyrinth.Tiles.*;
import processing.core.PVector;

import java.util.ArrayList;

public class LabyrinthManager {

  private static LabyrinthManager instance;

  private Window window;

  private Labyrinth current;

  private ArrayList<Tile> tiles;

  private StartTile start;

  private EndTile end;

  private WallManager wm = new WallManager();

  private LabyrinthManager(int width, int height, Window w) {
    tiles = new ArrayList<Tile>();
    current = new Labyrinth(width, height);
    window = w;
  }

  /**
   * returns the labyrinth manager if one exists, creates a new one with given size and returns if one doesn't exist.
   * @return the labyrinthManager instance.
   */
  public static LabyrinthManager getInstance(int width, int height, Window w) {
    if(instance == null) {
      instance = new LabyrinthManager(width, height, w);
      instance.newLabyrinth(width, height);
    }
    return instance;
  }

  /**
   * returns the labyrinth manager if one exists, creates a new one with default size and returns if one doesn't exist.
   * @return the labyrinthManager instance.
   */
  public static LabyrinthManager getInstance() {

    return instance;
  }

  /**
   * generates the current labyrinth into the window. starts at PVector (0, 0).
   * should only be called once per labyrinth.
   */
  public void generateTiles() {
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

    location.set(0, (tileList.length) * Tile.TILE_SIZE);

    //bottom row
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
      location.set(Tile.TILE_SIZE, (i + 1) * Tile.TILE_SIZE);
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
        Wall w = new Wall(pos, window);
        tiles.add(w);
        wm.add(w);
        break;
      case PATH:
        tiles.add(new EmptyPath(pos, window));
        break;
      case END:
        end = new EndTile(pos, window);
        tiles.add(end);
        break;
      case START:
        start = new StartTile(pos, window);
        tiles.add(start);
        break;
      default:
        break;
    }
  }

  /**
   * Renders tiles into the window.
   */
  public void renderTiles() {
    //tiles.stream().forEach(); draw each tile. Don't know how to do this yet.
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
    current = new Labyrinth(width, height);
    current.print();
    generateTiles();
  }

  /**
   * loads a existing labyrinth from the database.
   * todo: this is a placeholder, need to figure out how to search for a given labyrinth
   */
  public void loadLabyrinth() {
    //todo
  }

  /**
   * Getter for tile list.
   * @return The Tiles arraylist.
   */
  public ArrayList<Tile> getTiles() {
    return tiles;
  }

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

  public boolean collision(Sprite s){
    return wm.collision(s);
  }

  public void draw(){
    wm.draw();
  }

}
