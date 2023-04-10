package org.bcit.com2522.project;

import processing.core.PApplet;

/**
 * The Timer class provides a simple way to keep track of elapsed time in seconds.
 */
public class Timer {

  /*The start time of the timer in milliseconds.*/
  private long startTime;

  /* A reference to the PApplet object to get the current time. */
  private PApplet sketch;

  /**
   * Constructs a Timer object with the current time as the start time.
   * @param sketch The PApplet object to get the current time.
   */
  public Timer(PApplet sketch) {
    this.sketch = sketch;
    this.startTime = sketch.millis();
  }

  /**
   * Returns the elapsed time in seconds since the Timer was constructed or reset.
   * @return The elapsed time in seconds.
   */
  public float getTime() {
    return (sketch.millis() - startTime) / 1000.0f;
  }

  /**
   * Resets the start time of the Timer to the current time.
   */
  public void resetTime(){
    startTime = sketch.millis();
  }

}


