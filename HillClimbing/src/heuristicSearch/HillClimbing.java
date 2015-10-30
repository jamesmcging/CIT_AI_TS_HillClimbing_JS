/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillclimbing;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class HillClimbing {

  static int nChildStates = 100;
  static int nRunTimes = 1000;
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
    Log log = new Log("Hill Climbing", nRunTimes, nChildStates, "312 Cities");
    
    // Instantiate a new map and load it with the 312 city map
    Map map = new Map("C:\\Users\\James\\Documents\\Masters\\AI\\Assignment1\\CIT_AI_TS_HillClimbing_JS\\HillClimbing\\src\\hillclimbing\\312_City_Distances.txt");
    
    // Instantiate a new state generator
    RouteGenerator routeGenerator = new RouteGenerator(map);
    
    // Create an array to hold child states, define how many child states we 
    // want in the class static nChildStats.
    Route[] arrChildRoutes = new Route[nChildStates];
    
    // Keep the shortest route found to measure new child routes against
    Route shortestRouteFound = null;
        
    // Run the algorithm a couple of times
    for (int nRunCount = 0; nRunCount < nRunTimes; nRunCount++) {
      
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
    
    // Output the results of our runs
    log.printShortestRouteRecord();
    // Output the result to file
    log.printLogToFile();
  }
}
