/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;

/**
 * Class charged with generating both an initial state for the algorithm and 
 * child states when given a current state.
 * 
 * @author James
 */
class StateGenerator {
  
  private int nNumberOfChildStates = 10;
  
  Map map = null;
  
  public StateGenerator(Map map) {
    this.map = map;
  }
  
  public State getState() {
    int[] arrCityIDs = new int[312];
    State state = new State(arrCityIDs);
    return state;
  }
  
  public Object[] getChildStates(State parentState) {
    // Instantiate an array of State objects, the length of this is determined
    // by nNumberOfChildStates, a class defined constant.
    State[] arrChildStates = new State[nNumberOfChildStates];
    
    // Instantiate requried number of chld states
    for (int i = 0; i < nNumberOfChildStates; i++) {
      arrChildStates[i] = this.getState();
    }
    
    return arrChildStates;
  }
}
