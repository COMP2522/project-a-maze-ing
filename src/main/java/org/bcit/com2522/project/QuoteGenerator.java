package org.bcit.com2522.project;

import java.util.Random;

public class QuoteGenerator {
  private static String[] quotes = {
      "This game was pretty hard to make.",
      "Paul is very cool!",
      "Do you even read these?",
      "We've been trying to reach \nyou about your car's extended warranty.",
      "I used to be an adventurer \nlike you, until I took CST.",
      "(o_o)/",
      "We've been trying to reach \nyou about your car's extended warranty",
      "God is dead and we have killed",
      "The Mitochondria is the \npowerhouse of the cell",
      "Are you finished with \nthose errands?"
  };

  private static Random rng = new Random();

  public static String getQuote(){
    return quotes[rng.nextInt(quotes.length)];
  }

}
