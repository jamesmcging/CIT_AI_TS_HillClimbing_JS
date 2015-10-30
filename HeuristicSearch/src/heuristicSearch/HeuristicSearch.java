/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package heuristicSearch;

import java.io.IOException;

/**
 *
 * @author James
 */
public class HeuristicSearch {
  
  // The number of times to run the algorithm
  static int nRunTimes = 1000;
  
  // The algorithm to run
  static String sAlgorithmName = "Hill Climbing"; // "Simulated Annealing";
  
  // The number of child states to generate for each generation
  static int nChildStates = 1000;
  
  static String sMapName = "312_City_Distances.txt";
  
  public static void main(String[] args) throws IOException {  
    // Instantiate a new map and load it with the 312 city map
    Map map = new Map(sMapName);
    
    // Instantiate a route generator (Creates the child routes)
    RouteGenerator routeGenerator = new MoveOneToFront(map);

    // Instantiate a log (keeps track of the results)
    Log log = new Log(sAlgorithmName, nRunTimes, routeGenerator.getName(), nChildStates, map.getName());
    
    // Instantiate a new algorithm
    if (sAlgorithmName.equals("Hill Climbing")) {
      new HillClimbing(routeGenerator, nChildStates, map.getName(), nRunTimes, log);
    } else if (sAlgorithmName.equals("Simulated Annealing")) {
      new SimulatedAnnealing(routeGenerator, nChildStates, map.getName(), nRunTimes, log);
    }
        
    // Print the results of our runs to standard output
    log.printShortestRouteRecord();
    // Write the result to file
    log.printLogToFile();
  }
}
