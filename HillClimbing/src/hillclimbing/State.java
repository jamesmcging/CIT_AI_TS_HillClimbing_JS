/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;

/**
 * Class that represents a route traveled by a salesman through the map
 * @author James
 */
class State {
  
  int[] arrCityIDs = new int[312];
  
  public State(int[] arrCityIDs) {
    
  }

  /**
   * Method charged with calculating the length of a route.
   * 
   * @return 
   */
  int getLength() {
    return 42;
  }
}
