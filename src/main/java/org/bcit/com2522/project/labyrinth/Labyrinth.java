package org.bcit.com2522.project.labyrinth;

import org.bcit.com2522.project.labyrinth.Tiles.TileType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class Labyrinth {

  /**
   * Array of every tile within the labyrinth - false represents a wall, true represents a corridor.
   * set up such that tiles[a][b] where a is the y coordinate and b is the x coordinate.
   */
  private TileType[][] tiles;

  /**
   * start x coordinate for the player.
   */
  private int sX;

  /**
   * start y coordinate for the player.
   */
  private int sY;

  /**
   * X coordinate for the labyrinth exit
   */
  private int eX;

  /**
   * Y coordinate for the labyrinth exit
   */
  private int eY;


  private Random randomizer;

  /**
   * Constructor
   * @param width width of the new maze
   * @param height height of the new maze
   */
  public Labyrinth(int width, int height) {
    tiles = new TileType[height][width];

    for(int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        tiles[i][j] = TileType.WALL;
      }
    }

    randomizer = new Random();

    init();
  }

  /**
   * Initialization function for creating new labyrinth. To ensure maze is not too small, if the start and end of the
   * maze are less than half the length of the longest dimension of the maze away from each other, it resets the end
   * of the maze.
   * (i.e. if the maze is 50 x 20, if the start and end are less than 25 tiles away from each other
   * (50 / 2 = 25), generate a new end)
   */
  public void init() {
    sX = randomizer.nextInt(tiles[0].length);
    sY = randomizer.nextInt(tiles.length);
    int repeats = -1;                      // debugging variable, remove later

    do {
      eX = randomizer.nextInt(tiles[0].length);
      eY = randomizer.nextInt(tiles.length);
      repeats++;                        // debugging variable, remove later
    } while ( max(tiles[0].length / 2, tiles.length / 2) > sqrt((abs(pow((eX - sX), 2) - pow((eY - sY), 2)))));
    tiles[sY][sX] = TileType.START;
    tiles[eY][eX] = TileType.END;
    //System.out.println("repeats: " + repeats);// debugging variable, remove later


    generateCorrectPath();


  }

  /**
   * Algorithm to generate the shortest path between the start and end of the labyrinth.
   * Note: currently only generates L shaped paths for quick testing purposes. Need to add randomizer later
   */
  private void generateCorrectPath() {
    MazeGenerationQueue queue = new MazeGenerationQueue();
    int[][] startPath = {};
    boolean[][] seenTiles = new boolean[tiles.length][tiles[0].length];
    queue.push(new QueueNode(sX, sY, startPath));

    boolean endFound = false;

    QueueNode curr;

    Directions[] dirVals = {Directions.DOWN, Directions.UP, Directions.LEFT, Directions.RIGHT};
    List<Directions> order = Arrays.asList(dirVals);

    while (!endFound) {
      curr = queue.pop();
      //System.out.println("queue size: " + queue.size);
      //System.out.println("\tcoordinate: (" + curr.getX() + ", " + curr.getY() + ")");


      if(curr.getX() == eX && curr.getY() == eY) {
        endFound = true;
        drawPath(curr.getPathSF());
      } else {


        int[][] newPath = copyPath(curr.getPathSF());
        newPath[curr.getPathSF().length][0] = curr.getX();
        newPath[curr.getPathSF().length][1] = curr.getY();

        Collections.shuffle(order);

        for (Directions i: order) {
          switch (i) {
            case UP:
              checkTile(curr.getX(), curr.getY() - 1, queue, seenTiles, newPath);
              break;
            case RIGHT:
              checkTile(curr.getX() + 1, curr.getY(), queue, seenTiles, newPath);
              break;
            case DOWN:
              checkTile(curr.getX(), curr.getY() + 1, queue, seenTiles, newPath);
              break;
            case LEFT:
              checkTile(curr.getX() - 1, curr.getY(), queue, seenTiles, newPath);
              break;
          }

        }

      }

    }

  }

  /**
   * given a set of coordinates, checks if it has been seen so far in generation
   * and if it is out of bounds. If both are false, adds a new node representing this coordinate
   * in the provided queue.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param q the queue to add to
   * @param seenTiles Boolean array of tiles seen so far, true if a tile seen
   * @param newPath the path the give to the new node
   */
  private void checkTile(int x, int y, MazeGenerationQueue q, boolean[][] seenTiles, int[][] newPath) {
    if (!outOfBounds(x , y) && !seenTiles[y][x]) {
      q.push(new QueueNode(x, y, newPath));
      seenTiles[y][x] = true;
    }
  }

  /**
   * given an array of coordinates, go through the array and set all tiles at the contained coordinates to a path.
   * @param path
   */
  private void drawPath(int[][] path) {
    for (int i = 1; i < path.length; ++i) {
      tiles[path[i][1]][path[i][0]] = randPathTile();
    }
  }

  /**
   * Generates a random type of path tile.
   * @return a random path tile
   */
  private TileType randPathTile() {
    int pick = randomizer.nextInt(100);
    if (pick <= 10) {
      return TileType.WRAITH;
    } else if (pick <= 25) {
      return TileType.BLADE_TILE;
    } else if (pick <= 35) {
      return TileType.HOLE_TILE;
    } else {
      return TileType.PATH;
    }
  }

  /**
   * Given a set of coordinates, check if it is out of the bounds of the labyrinth.
   * @param xCoord the x coordinate to check
   * @param yCoord the y coordinate to check
   * @return true if the given coordinates are out of the bounds of the labyrinth
   */
  private boolean outOfBounds(int xCoord, int yCoord) {
    return yCoord >= tiles.length || yCoord < 0 || xCoord >= tiles[0].length || xCoord < 0;
  }

  /** Copies given path into new path*/
  private int[][] copyPath(int[][] path) {
    int[][] newPath = new int[path.length + 1][2];
    for (int i = 0; i < path.length; ++i) {
      newPath[i][0] = path[i][0];
      newPath[i][1] = path[i][1];
    }
    return newPath;
  }

  /**
   * print method for debugging maze generation, remove later.
   */
  public void print() {
    System.out.print("\t");

    for (int i = 0; i < tiles[0].length; i++) {
      System.out.print(i + "\t");
    }
    System.out.print("\n\n\t");

    for (int i = 0; i < tiles[0].length; i++) {
      System.out.print("===\t");
    }

    for (int i = 0; i < tiles.length; i++) {
      System.out.print("\n" + i + "\t");

      for (int j = 0; j < tiles[0].length; j++) {
        if (tiles[i][j] == TileType.START) {
          System.out.print("SSS\t");
        } else if (tiles[i][j] == TileType.END) {
          System.out.print("EEE\t");
        } else if (tiles[i][j] == TileType.PATH) {
          System.out.print("   \t");
        } else if (tiles[i][j] == TileType.WRAITH) {
          System.out.print(" W \t");
        } else if (tiles[i][j] == TileType.BLADE_TILE) {
          System.out.print(" B \t");
        } else if (tiles[i][j] == TileType.HOLE_TILE) {
          System.out.print(" H \t");
        } else {
          System.out.print("[-]\t");
        }
      }
    }
    System.out.print("\n\t");
    for (int i = 0; i < tiles[0].length; i++) {
      System.out.print("===\t");
    }

    System.out.println("\nStart: X: " + sX + " Y: " + sY);
    System.out.println("End: X: " + eX + " Y: " + eY);
  }

  /**
   * getter method for tiles.
   * @return the tile array of the labyrinth.
   */
  public TileType[][] getTiles() {
    return tiles;
  }

  /**
   * sets the tile at given coordinates to provided value.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param value the new value to set.
   */
  public void setTile(int x, int y, TileType value) {
    this.tiles[y][x] = value;
  }
}
