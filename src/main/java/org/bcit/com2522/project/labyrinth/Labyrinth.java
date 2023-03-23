package org.bcit.com2522.project.labyrinth;

import java.util.Random;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class Labyrinth {

  /**
   * Array of every tile within the labyrinth - false represents a wall, true represents a corridor.
   */
  private boolean[][] tiles;

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

  Random randomizer;

  public Labyrinth(int width, int height) {
    tiles = new boolean[height][width];
    randomizer = new Random();

    init();
  }

  public void init() {
    sX = randomizer.nextInt(tiles[0].length);
    sY = randomizer.nextInt(tiles.length);
    int repeats = -1;                      // debugging variable, remove later

    do {
      eX = randomizer.nextInt(tiles[0].length);
      eY = randomizer.nextInt(tiles.length);
      repeats++;                        // debugging variable, remove later
    } while ( max(tiles[0].length / 2, tiles.length / 2) > sqrt((abs(pow((eX - sX), 2) - pow((eY - sY), 2)))));
    tiles[sY][sX] = true;
    tiles [eY][eX] = true;
    System.out.println("repeats: " + repeats);// debugging variable, remove later

    int[][] empty = {};

    generateCorrectPath();


  }

  private void generateCorrectPath() {
    MazeGenerationQueue queue = new MazeGenerationQueue();
    int[][] startPath = {};
    boolean[][] seenTiles = new boolean[tiles.length][tiles[0].length];
    queue.push(new QueueNode(sX, sY, startPath));

    boolean endFound = false;

    QueueNode curr;

    while (!endFound) {
      curr = queue.pop();
      System.out.println("queue size: " + queue.size);
      System.out.println("\tcoordinate: (" + curr.getX() + ", " + curr.getY() + ")");


      if(curr.getX() == eX && curr.getY() == eY) {
        endFound = true;
        drawPath(curr.getPathSF());
      } else {


        int[][] newPath = copyPath(curr.getPathSF());
        newPath[curr.getPathSF().length][0] = curr.getX();
        newPath[curr.getPathSF().length][1] = curr.getY();

        if (!outOfBounds(curr.getX() + 1, curr.getY()) && !seenTiles[curr.getY()][curr.getX() + 1]) {
          queue.push(new QueueNode(curr.getX() + 1, curr.getY(), newPath));
          seenTiles[curr.getY()][curr.getX() + 1] = true;
        }

        if (!outOfBounds(curr.getX() , curr.getY() - 1) && !seenTiles[curr.getY() - 1][curr.getX()]) {
          queue.push(new QueueNode(curr.getX(), curr.getY() - 1, newPath));
          seenTiles[curr.getY() - 1][curr.getX()] = true;
        }

        if (!outOfBounds(curr.getX() - 1, curr.getY()) && !seenTiles[curr.getY()][curr.getX() - 1]) {
          queue.push(new QueueNode(curr.getX() - 1, curr.getY(), newPath));
          seenTiles[curr.getY()][curr.getX() - 1] = true;
        }

        if (!outOfBounds(curr.getX() , curr.getY() + 1) && !seenTiles[curr.getY() + 1][curr.getX()]) {
          queue.push(new QueueNode(curr.getX(), curr.getY() + 1, newPath));
          seenTiles[curr.getY() + 1][curr.getX()] = true;
        }



      }

    }

  }

  private void drawPath(int[][] path) {
    for (int i = 1; i < path.length; ++i) {
      tiles[path[i][1]][path[i][0]] = true;
    }
  }

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
        if ((j == sX) && (i == sY)) {
          System.out.print("SSS\t");
        } else if ((j == eX) && (i == eY)) {
          System.out.print("EEE\t");
        } else if (tiles[i][j]) {
          System.out.print("   \t");
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
}
