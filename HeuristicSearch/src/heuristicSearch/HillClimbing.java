/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicSearch;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The first part of the assignment is to implement the Hill Climbing heuristic 
 * search algorithm. This class represents that algorithm. It can be passed 
 * various parameters than influence how it behaves and outputs its results to a
 * text file.
 * 
 * @author James
 */
public class HillClimbing extends Algorithm {

  // The name of this algorithm
  static String sAlgorithmName = "Hill Climbing";

  // The number of times to run the algorithm
  int nRunTimes = 10;
  
  // The state generator to use
  RouteGenerator routeGenerator = null;
  
  // The number of child states to generate each generation
  int nChildStates = 312;
  
  // The map to use
  String sMapName = "312 Cities";
  
  // Somewhere to dump the results of our algorithm runs
  Log log = null;
  
  public HillClimbing(RouteGenerator routeGenerator, int nChildStates, String sMapName, int nRunTimes, Log log) throws IOException {
    this.routeGenerator = routeGenerator;
    this.nChildStates = nChildStates;
    this.sMapName = sMapName;
    this.nRunTimes = nRunTimes;
    this.log = log;
    
    // Create an array to hold child states, define how many child states we 
    // want in the class static nChildStats.
    Route[] arrChildRoutes = new Route[nChildStates];
    
    // Keep the shortest route found to measure new child routes against
    Route shortestRouteFound = null;
        
    // Run the algorithm a couple of times
    for (int nRunCount = 0; nRunCount < this.nRunTimes; nRunCount++) {
      
      // Create a new record for this run
      log.newRun(nRunCount);
      
      // Generate a starting position
      Route currentBestRoute = routeGenerator.getRandomRoute();
      
      // Keep track of the number of child states we pass through
      int nChildGeneration = 0;
      
      // Add the starting route/ state to the log
      log.addRoute(nChildGeneration, currentBestRoute);

      // If we haven't got a shortestRouteFound (first time through) then this
      // becomes shortestRouteFound
      if (shortestRouteFound == null) {
        shortestRouteFound = currentBestRoute;
      }

      // Run the algorithm until we can't find a better child node.
      boolean bFoundShorterChildNode = true;
      while (bFoundShorterChildNode) {
        bFoundShorterChildNode = false;
        nChildGeneration++;

        // Generate child nodes using the the current best node as a starting 
        // position.
        for (int i = 0; i < nChildStates; i++) {
          arrChildRoutes[i] = routeGenerator.generateChildRoute(currentBestRoute);
        }

        // Evaluate each child node
        for (int i = 0; i < arrChildRoutes.length; i++) {
          if (arrChildRoutes[i].getRouteLength() < currentBestRoute.getRouteLength()) {
            // Record the shortest route found so far
            currentBestRoute = arrChildRoutes[i];
            // Tell the algorithm to try again
            bFoundShorterChildNode = true;
          }
        }
        
        // Save the shortest child route found to the log
        if (bFoundShorterChildNode) {
          log.addRoute(nChildGeneration, currentBestRoute);
        }
      }      
    }
  }
}
