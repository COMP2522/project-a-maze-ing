package org.bcit.com2522.project.labyrinth;

public class MazeGenerationQueue {

  private QueueNode head;

  public int size;

  private QueueNode tail;

  public MazeGenerationQueue() {
    size = 0;
  }

  public void push(QueueNode addition) {
    //System.out.println("\t\tpush called on point: (" + addition.getX() + ", " + addition.getY() + ")");
    if (head == null) {
      head = addition;
      tail = addition;
    } else {
      tail.setNext(addition);
      tail = tail.getNext();
    }
    size++;
  }

  public QueueNode pop() {
    //System.out.println("pop called");
    if (head == null) {
      //System.out.println("head null");
      return null;
    } else {
      QueueNode temp = head;
      head = head.getNext();
      temp.setNext(null);
      size--;
      return temp;
    }


  }



}
