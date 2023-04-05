package org.bcit.com2522.project.traps;

import org.bcit.com2522.project.Player;
import org.bcit.com2522.project.Sprite;

import java.util.ArrayList;
import java.util.Iterator;

public class TrapManager {
    private static TrapManager instance;
    private ArrayList<Trap> traps;
    private Iterator<Trap> iterator;

    private TrapManager() {
        traps = new ArrayList<>();
    }

    public static TrapManager getInstance() {
        if (instance == null) {
            instance = new TrapManager();
        }
        return instance;
    }


    public void addTrap(Trap t) {
        traps.add(t);
    }

    public void removeTrap(Trap t) {
        traps.remove(t);
    }

    public void resetIterator() {
        iterator = traps.iterator();
    }

    public void draw() {
        resetIterator();
        while (iterator.hasNext()) {
            Trap t = iterator.next();
            t.draw();
        }
    }


    public void collision(Sprite s){
        Player player = Player.getInstance();
        resetIterator();

        while (iterator.hasNext()) {
            Trap trap = iterator.next();
            if (trap.collision(player)) {
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
