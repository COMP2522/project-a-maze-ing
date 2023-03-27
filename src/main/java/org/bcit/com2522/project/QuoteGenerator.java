package org.bcit.com2522.project;

import java.util.Random;

public class QuoteGenerator {
  private static String[] quotes = {
      "This game was pretty hard to make.",
      "Paul is very cool!",
      "Do you even read these?"
  };

  private static Random rng = new Random();

  public static String getQuote(){
    return quotes[rng.nextInt(quotes.length)];
  }

}
