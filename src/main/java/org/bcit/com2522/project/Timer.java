package org.bcit.com2522.project;

import processing.core.PApplet;
import processing.core.PVector;

public class Timer {
  private long startTime;
  private PApplet sketch;

  public Timer(PApplet sketch, PVector position) {
    this.sketch = sketch;
    this.startTime = sketch.millis();
  }

  public float getTime() {
    return (sketch.millis() - startTime) / 1000.0f;
  }

  public void setPosition(PVector position) {
  }
}
