package org.bcit.com2522.project;

import java.util.Random;

/**
 * Generates a random quote when called on.
 */
public class QuoteGenerator {

  /* Quotes that can be returned. */
  private static String[] quotes = {
      "This game was pretty hard to make",
      "Paul is very cool!",
      "Do you even read these?",
      "We've been trying to reach \nyou about your car's extended warranty",
      "I used to be an adventurer \nlike you, until I took CST",
      "\\(o_o)/",
      "We've been trying to reach \nyou about your car's extended warranty",
      "God is dead and we have \nkilled him",
      "The Mitochondria is the \npowerhouse of the cell",
      "Are you finished with \nthose errands?",
      "I think you left your \noven on",
      "never gonna give you up\nnever gonna let you down",
      "Life is short, don't \nwaste it playing this game",
      "(a+b)^2 = a^2 + b^2"
  };

  /* Random number generator. */
  private static Random rng = new Random();

  /**
   * Returns a random quote.
   * @return a random quote String.
   */
  public static String getQuote(){
    return quotes[rng.nextInt(quotes.length)];
  }

}
