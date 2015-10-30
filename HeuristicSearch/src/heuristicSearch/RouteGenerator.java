/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package heuristicSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class charged with generating both an initial state for the algorithm and 
 * child states when given a current state.
 * 
 * @author James
 */
abstract class RouteGenerator {
  
  private String sGeneratorName = "Parent Route Generator";
  
  Map map = null;
  
  Random randomGenerator = new Random();
  
  public String getName() {
    return sGeneratorName;
  }
  
  abstract public Route getRandomRoute();

  abstract Route generateChildRoute(Route currentBestRoute);
}
