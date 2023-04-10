package org.bcit.com2522.project.labyrinth;

/**
 * Node for the Maze Generation Queue. Represents a tile within the labyrinth and the path traversed to reach it.
 * Stores the x, y coordinates for the tile, and an array containing the coordinates
 * of each tile visited before reaching the current tile.
 */
public class QueueNode {

  /**
   * X coordinate of this tile
   */
  private int xCoord;

  /**
   * Y coordinate of this tile.
   */
  private int yCoord;

  /**
   * Array of coordinates of previously visited tiles.
   * Format: 1st dimension (pathSF.length) is the number of tiles previously visited, or the path length.
   *        2nd dimension is always of length 2:
   *            pathSF[i][0] = the x coordinate of tile i
   *            pathSF[i][1] = the y coordinate of tile i
   */
  private int[][] pathSF;

  /**
   * the next node in the queue.
   */
  private QueueNode next;

  /**
   * Constructor.
   * @param x the X coord of the current tile.
   * @param y the Y coord of the current tile.
   * @param path the path used to reach the current tile.
   */
  public QueueNode(int x, int y, int[][] path) {
    this.xCoord = x;
    this.yCoord = y;
    this.pathSF = path;
  }

  /**
   * Set the next queue node.
   * @param next the next node.
   */
  public void setNext(QueueNode next) {
    this.next = next;
  }

  /**
   * returns the X coordinate of the current tile.
   * @return the X coordinate of the current tile.
   */
  public int getX() {
    return xCoord;
  }

  /**
   * returns the Y coordinate of the current tile.
   * @return the Y coordinate of the current tile.
   */
  public int getY() {
    return yCoord;
  }

  /**
   * returns the next Node.
   * @return next.
   */
  public QueueNode getNext() {
    return next;
  }

  /**
   * returns the list of previously visited tiles.
   * @return the list of previously visited tiles.
   */
  public int[][] getPathSF() {
    return pathSF;
  }

}
