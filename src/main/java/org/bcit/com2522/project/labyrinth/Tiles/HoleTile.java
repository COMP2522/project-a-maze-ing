package org.bcit.com2522.project.labyrinth.Tiles;


import org.bcit.com2522.project.traps.Hole;
import org.bcit.com2522.project.traps.TrapManager;
import org.bcit.com2522.project.Window;
import processing.core.PVector;

public class HoleTile extends Tile {

    public HoleTile(PVector pos, Window w) {
        super(pos, w);
        TrapManager.getInstance().addTrap(new Hole(pos, w));
    }

    @Override
    public void draw() {

    }
}
