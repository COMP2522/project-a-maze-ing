package org.bcit.com2522.project.labyrinth;

public class QueueNode {

  private int xCoord;

  private int yCoord;

  private int[][] pathSF;

  private QueueNode next;

  public QueueNode(int x, int y, int[][] path) {
    this.xCoord = x;
    this.yCoord = y;
    this.pathSF = path;
  }

  public void setNext(QueueNode next) {
    this.next = next;
  }

  public int getX() {
    return xCoord;
  }

  public int getY() {
    return yCoord;
  }

  public QueueNode getNext() {
    return next;
  }

  public int[][] getPathSF() {
    return pathSF;
  }

}
