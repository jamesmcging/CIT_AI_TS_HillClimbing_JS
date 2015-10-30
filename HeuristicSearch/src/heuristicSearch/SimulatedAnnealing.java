/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The second heuristic search algorithm to implement is the simulated annealing
 * search algorithm. This class represents that algorithm. It can be passed 
 * various parameters than influence how it behaves and outputs its results to a
 * text file.
 * 
 * @author James
 */
public class SimulatedAnnealing extends Algorithm {

  // The name of this algorithm
  static String sAlgorithmName = "Simulated Annealing";

  // The number of times to run the algorithm
  int nRunTimes = 10;
  
  // The state generator to use
  RouteGenerator sRouteGenerator = null;
  
  // The number of child states to generate each generation
  int nChildStates = 312;
  
  // The temperature drop for each generation of children generated
  int nTemperatureDrop = 1;
  
  // A place to dump the results
  Log log = null;
  
  public SimulatedAnnealing(RouteGenerator sRouteGenerator, int nChildStates, String sMapName, int nRunTimes, Log log) throws IOException {
    this.sRouteGenerator = sRouteGenerator;
    this.nChildStates = nChildStates;
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
      Route currentBestRoute = this.sRouteGenerator.getRandomRoute();
      
      // Keep track of the number of child states we pass through
      int nChildGeneration = 0;
      
      // Add the starting route/ state to the log
      log.addRoute(nChildGeneration, currentBestRoute);

      // If we haven't got a shortestRouteFound (first time through) then this
      // becomes shortestRouteFound
      if (shortestRouteFound == null) {
        shortestRouteFound = currentBestRoute;
      }

      // We'll be needing a random umber generator for Simulated Annealing
      Random randomGenerator = new Random();
      
      int nTemperature = 100;
      
      // Run the algorithm until the temperature has dropped to 0.
      while (nTemperature > 0) {        
        // Generate child nodes using the the current best node as a starting 
        // position.
        for (int i = 0; i < nChildStates; i++) {
          arrChildRoutes[i] = this.sRouteGenerator.generateChildRoute(currentBestRoute);
        }
        
        // Set the first of these child routes as the sortest child route
        Route shortestChildRoute = arrChildRoutes[0];

        // Evaluate each child node
        for (int i = 0; i < arrChildRoutes.length; i++) {
          if (arrChildRoutes[i].getRouteLength() < shortestChildRoute.getRouteLength()) {
            // Record the shortest child route found
            shortestChildRoute = arrChildRoutes[i];
          }
        }
        
        // If the best child found is better than the current best route then 
        // the child route becomes the best route found and we start again.
        if (shortestChildRoute.getRouteLength() < currentBestRoute.getRouteLength()) {
          currentBestRoute = shortestChildRoute;
          
        // If there isn't a child route that is shorter then the current best
        // route, we take the shortest child route found and, depending in the 
        // current nTemperature, see if it is worth turning into the 
        // currentBestRoute
        } else {
          // Generate a random number
          double chance = randomGenerator.nextDouble();
          
          // Get the difference between the current route and the best child
          // route we found
          int nDeltaS = currentBestRoute.getRouteLength() - shortestChildRoute.getRouteLength();
          
          // See if the chance is enough to make us use the child route
          if (chance < Math.exp(-(nDeltaS/nTemperature))) {
            currentBestRoute = shortestChildRoute;
          }
        }
        
        // Drop the algorithm temperature by an amount set in the class declaration
        nTemperature -= nTemperatureDrop;
        
        // Save the selected child node to the log
        nChildGeneration++;
        log.addRoute(nChildGeneration, currentBestRoute);
      }      
    }
  }
}
