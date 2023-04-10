package org.bcit.com2522.project.labyrinth;

/**
 * Queue datastructure used during labyrinth generation. Stores the head of the queue, it's size, and the tail.
 */
public class MazeGenerationQueue {

  /**
   * The head of the queue.
   */
  private QueueNode head;

  /**
   * the current size of the queue.
   */
  public int size;

  /**
   * The tail of the queue.
   */
  private QueueNode tail;

  /**
   * Constructor.
   */
  public MazeGenerationQueue() {
    size = 0;
  }

  /**
   * Add's given QueueNode to the end of the queue.
   * @param addition the node to add.
   */
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

  /**
   * Returns and removes the Queunode at the top of the queue.
   * @return
   */
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
