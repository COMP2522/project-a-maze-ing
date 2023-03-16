package org.bcit.com2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {
  PImage harryPotterImage;

  ArrayList<Sprite> sprites;
  ArrayList<Enemy> enemies;
  ArrayList<Blade> blades;
  Ghost ghost;
  Player player;

  int numEnemies = 10;
  int numBlades = 2;
  int numHoles = 4;
  int minSize = 10;
  int maxSize = 20;

  /**
   * Provides the size of the window
   */
  public void settings(){
    size(1000,600);
  }

  /**
   * Called to initialize objects once the program starts.
   * Calls initializeObjects() method
   */
  public void setup(){
    harryPotterImage = loadImage("/Users/laurieannesolkoski/IdeaProjects/project-a-maze-ing/Data/harry_potter.png");
    System.out.println("Loading image from path: " + sketchPath("Data/harry_potter.png"));
    if (harryPotterImage == null) {
      System.out.println("Image is null after loading.");
    } else {
      System.out.println("Image successfully loaded.");
    }
    this.initializeObjects();
  }


  public void initializeObjects() {
    enemies = new ArrayList<Enemy>();
    sprites = new ArrayList<Sprite>();
    blades = new ArrayList<Blade>();

    player = new Player(
            new PVector(this.width / 2, this.height / 2),
            new PVector(0, 1),
            0,
            this,
            harryPotterImage
    );




      ghost = new Ghost(
          new PVector(this.width/3,this.height/3),
          new PVector(0,1),
          minSize + 1,
          5,
          new Color(255,255,255),
          this);

// Initialize holes
    for (int i = 0; i < numHoles; i++) {
      PVector holePosition = new PVector(random(0, width), random(0, height));
      float holeSize = random(60, 60);
        Hole hole = new Hole(holePosition, holeSize, new Color(139, 69, 19), this);
        sprites.add(hole);


    }


    // Initialize blades
    for (int i = 0; i < numBlades; i++) {
      Blade blade = new Blade(
              new PVector(150 * (i + 1), height / 2),
              new PVector(0, 1),
              40,
              2,
              new Color(255, 0, 0),
              this
      );
      blades.add(blade);
      sprites.add(blade);
    }


    sprites.add(ghost);

  }



  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case 'A':
        // Rotate left
        player.rotateLeft(0.1f); // Adjust this value to control the rotation speed
        break;
      case 'D':
        // Rotate right
        player.rotateRight(0.1f); // Adjust this value to control the rotation speed
        break;
      case LEFT:
        // Move left
        player.moveLeft(5); // Adjust this value to control the speed of the player
        break;
      case RIGHT:
        // Move right
        player.moveRight(5); // Adjust this value to control the speed of the player
        break;
      case DOWN:
        // Move forward
        player.moveForward(5); // Adjust this value to control the speed of the player
        break;
      case UP:
        // Move backward
        player.moveBackward(5); // Adjust this value to control the speed of the player
        break;
    }
  }





  public void handlePlayerDeath() {
    // Stop the game or restart the level
    // For now, just print "Game Over" and stop the sketch
    System.out.println("Game Over");
    noLoop();
  }

  public void handlePlayerFallingThroughHole(PVector holePosition, float holeSize) {
    // Set the player's position directly below the hole's position
    player.setPosition(new PVector(holePosition.x, holePosition.y + holeSize / 2 + player.getSize() / 2));
  }







  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(200, 200, 200);

    player.update();
    player.draw();

    for (Sprite sprite : sprites) {
      sprite.update();

      if (sprite instanceof Blade && player.collidesWith(sprite)) {
        handlePlayerDeath();
      }
      if (sprite instanceof Hole && player.collidesWith(sprite)) {
        handlePlayerFallingThroughHole(sprite.getPosition(), sprite.getSize());
      }
      if (sprite instanceof Ghost && player.collidesWith(sprite)) {
        handlePlayerDeath();
      }

      sprite.draw();
    }
  }






  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}
