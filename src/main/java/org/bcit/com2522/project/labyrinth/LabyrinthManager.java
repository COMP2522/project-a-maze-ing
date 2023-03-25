package org.bcit.com2522.project.labyrinth;

import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.labyrinth.Tiles.Tile;
import org.bcit.com2522.project.labyrinth.Tiles.TileType;
import org.bcit.com2522.project.labyrinth.Tiles.Wall;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;

public class LabyrinthManager {

  private static LabyrinthManager instance;

  private Window window;

  private Labyrinth current;

  private ArrayList<Tile> tiles;

  private LabyrinthManager(int width, int height) {
    tiles = new ArrayList<Tile>();
    current = new Labyrinth(width, height);
  }

  /**
   * returns the labyrinth manager if one exists, creates a new one with given size and returns if one doesn't exist.
   * @return the labyrinthManager instance.
   */
  public static LabyrinthManager getInstance(int width, int height) {
    if(instance == null) {
      instance = new LabyrinthManager(width, height);
    }
    return instance;
  }

  /**
   * returns the labyrinth manager if one exists, creates a new one with default size and returns if one doesn't exist.
   * @return the labyrinthManager instance.
   */
  public static LabyrinthManager getInstance() {
    if(instance == null) {
      instance = new LabyrinthManager(20, 20);  //arbitrary default value of 20x20 if no instance exists.
    }
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
      addTile(location, TileType.WALL);
      location.add(Tile.TILE_SIZE, 0);
    }

    // middle rows
    for(int i = 1; i < tileList.length + 1; i++) {
      location.set(0, i * Tile.TILE_SIZE);
      addTile(location, TileType.WALL);
      location.set((tileList[0].length + 2) * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
      addTile(location, TileType.WALL);
    }

    location.set(0, (tileList.length + 2) * Tile.TILE_SIZE);

    //bottom row
    for (int j = 0; j < tileList[0].length + 2; j++) {
      addTile(location, TileType.WALL);
      location.add(Tile.TILE_SIZE, 0);
    }


                                                          //render labyrinth contents
    //start at position (tile_size, tile_size) to account for outer wall
    location.set(Tile.TILE_SIZE, Tile.TILE_SIZE);
    for (int i = 0; i < tileList.length; i++) {
      for (int j = 0; j < tileList[0].length; j++) {
        //render tile
        addTile(location, tileList[i][j]);

        //move to next place in row
        location.add(Tile.TILE_SIZE, 0);
      }
      //set location to start of next row
      location.set(Tile.TILE_SIZE, (i + 1) * Tile.TILE_SIZE);
    }
  }

  /**
   * renders given tile type at given location.
   * @param pos the position to render the tile
   * @param type the type of tile to render
   */
  private void addTile(PVector pos, TileType type) {
    switch (type) {
      case WALL:
        tiles.add(new Wall(pos));
        break;
      case PATH:
        //todo: add path tile at location
        break;
      case END:
        //todo: add end tile at <location>
        break;
      case START:
        //todo: add start tile at <location>
    }
  }

  public void renderTiles() {
    //tiles.stream().forEach(); draw each tile. Don't know how to do this yet.
  }

  /**
   * generates a new labyrinth with given width and height, and sets it as current labyrinth.
   * @param width - the width of the new labyrinth
   * @param height - the height of the new labyrinth
   */
  public void newLabyrinth(int width, int height) {
    current = new Labyrinth(width, height);
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


}
