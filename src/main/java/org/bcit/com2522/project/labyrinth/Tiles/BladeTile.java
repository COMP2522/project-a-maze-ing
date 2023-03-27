package org.bcit.com2522.project.labyrinth.Tiles;

import org.bcit.com2522.project.traps.TrapManager;
import org.bcit.com2522.project.Window;
import org.bcit.com2522.project.traps.Blade;
import processing.core.PVector;

import java.awt.*;
import java.util.Random;

public class BladeTile extends Tile {

    public BladeTile(PVector pos, Window w) {
        super(pos, w);
        Random rand = new Random();
        float x = rand.nextInt(Tile.TILE_SIZE);
        float y = rand.nextInt(Tile.TILE_SIZE);
        TrapManager.getInstance()
            .addTrap(new Blade(pos.copy().add(x, y),
                new PVector(0, 0),
                30f, 0, Color.red, w, 0.05f, 2));
    }

    @Override
    public void draw() {

    }
}
