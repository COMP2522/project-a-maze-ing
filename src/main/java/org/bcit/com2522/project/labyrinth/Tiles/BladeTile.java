package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.traps.TrapManager;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.traps.Blade;
import processing.core.PVector;

public class BladeTile extends Tile {

    public BladeTile(PVector pos, Window w) {
        super(pos, w);
        TrapManager.getInstance().addTrap(new Blade(pos, w));
    }

    @Override
    public void draw() {

    }
}
