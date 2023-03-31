package org.bcit.com2522.project.labyrinth;

import java.util.Arrays;
import java.util.List;

public enum Directions {
  UP,
  RIGHT,
  DOWN,
  LEFT;
  public static final List<Directions> DIR_ORDER = Arrays.asList(new Directions[]{UP, RIGHT, DOWN, LEFT});
}


