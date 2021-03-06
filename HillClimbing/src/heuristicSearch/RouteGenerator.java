/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class charged with generating both an initial state for the algorithm and 
 * child states when given a current state.
 * 
 * @author James
 */
class RouteGenerator {
  
  private int nNumberOfChildStates = 10;
  
  Map map = null;
  
  Random randomGenerator = new Random();
  
  public RouteGenerator(Map map) {
    this.map = map;
  }
  
  public Route getRandomRoute() {
    // Create an array to hold 312 randomised city IDs
    int[] arrRoute = new int[312];
    
    // Populate the array with 312 city IDs
    for (int i = 0; i < arrRoute.length; i++) {
      arrRoute[i] = i;
    }
    
    // Randomize the order of city IDs
    for (int i = 0; i < arrRoute.length; i++) {
      int nRandomPosition = randomGenerator.nextInt(arrRoute.length);
      int temp = arrRoute[i];
      arrRoute[i] = arrRoute[nRandomPosition];
      arrRoute[nRandomPosition] = temp;
    }
   
    return new Route(map, arrRoute);
  }
  
  public Object[] getChildStates(Route parentState) {
    // Instantiate an array of State objects, the length of this is determined
    // by nNumberOfChildStates, a class defined constant.
    Route[] arrChildStates = new Route[nNumberOfChildStates];
    
    // Instantiate requried number of chld states
    for (int i = 0; i < nNumberOfChildStates; i++) {
      arrChildStates[i] = this.getRandomRoute();
    }
    
    return arrChildStates;
  }

  Route generateChildRoute(Route currentBestRoute) {
    // Extract the route from the current best route
    int[] arrCurrentRoute = currentBestRoute.arrRoute;
    
    // Create an arrayList so we can use remove(), add()
    ArrayList<Integer> arrlistParentRoute = new ArrayList<Integer>();
    for(int index = 0; index < arrCurrentRoute.length; index++) {
      arrlistParentRoute.add(arrCurrentRoute[index]);
    }
    
    
    // Generate an empty child route
    int[] arrChildRoute = new int[312];
    
    // Pick a random city ID in the current route
    int nRandomKey  = randomGenerator.nextInt(arrCurrentRoute.length);
    
    // Get the city ID at the random key
    int nCityBeingMovedToFront = arrlistParentRoute.get(nRandomKey);
    
    // Remove the city ID at nRandomKey
    arrlistParentRoute.remove(nRandomKey);
    
    // Add the randomly selected city ID to the front of a new array
    arrChildRoute[0] = nCityBeingMovedToFront;
    
    // Add all the other cityIDs to the new array
    for (int i = 0; i < arrlistParentRoute.size(); i++) {
      arrChildRoute[i + 1] = arrlistParentRoute.get(i);
    }
   
    // Convert the array back into a route object
    return new Route(this.map, arrChildRoute);
  }
}
