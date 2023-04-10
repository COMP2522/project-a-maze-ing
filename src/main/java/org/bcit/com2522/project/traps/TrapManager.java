package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Sprite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The TrapManager class manages the collection of Traps in the game.
 * It is a singleton class and contains methods for adding, removing,
 * and drawing Traps, as well as checking for collisions.
 * The TrapManager uses an ArrayList to store Traps and an Iterator to iterate over them.
 * @author Laurie Solkoksi
 * @author Mattew Siggs
 * @author Nelson Peterson-Hui
 * @version 1.0
 */
public class TrapManager {

    /*
    The singleton instance of the TrapManager.
    */
    private static TrapManager instance;

    /*
    The collection of Traps managed by the TrapManager.
     */
    private ArrayList<Trap> traps;

    /*
    The iterator used to iterate over the Traps in the collection.
     */
    private Iterator<Trap> iterator;

    /*
    Constructs a new TrapManager with an empty collection of Traps.
     */
    private TrapManager() {
        traps = new ArrayList<>();
    }

    /**
     *Returns the singleton instance of the TrapManager.
     * @return the singleton instance of the TrapManager.
     */
    public static TrapManager getInstance() {
        if (instance == null) {
            instance = new TrapManager();
        }
        return instance;
    }

    /**
     * Adds the given Trap to the collection of Traps managed by the TrapManager.
     * @param t the Trap to add to the collection
     */
    public void addTrap(Trap t) {

        traps.add(t);
    }

    /*
    Resets the iterator used to iterate over the Traps in the collection.
     */
    public void resetIterator() {

        iterator = traps.iterator();
    }

    /*
    Draws all Traps in the collection using the draw() method of each Trap.
     */
    public void draw() {
        for (Trap t : traps) {
            t.draw();
        }
    }

    /**
     * Checks if the given player collides with the specified trap.
     * @param trap   the trap to check for collision
     * @param player the player to check for collision
     * @return true if the player collides with the trap, false otherwise
     */
    private boolean collision(Trap trap, Player player) {
        return trap.collision(player);
    }

    /**
     * Checks for collisions between the given Sprite and all Traps in the collection.
     * If a collision is detected, the Player's alive state is set to false.
     * @param s the Sprite to check for collisions with
     */
    public void collision(Sprite s) {
        Player player = Player.getInstance();
        for (Trap trap : traps) {
            if (collision(trap, player)) {
                player.setAlive(false);
            }
        }
    }

  /**
   * Removes all traps from the manager.
   */
  public void clearTraps() {
    traps = new ArrayList<>();

  }

}
