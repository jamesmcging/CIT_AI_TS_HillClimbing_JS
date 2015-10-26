/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;

import java.util.ArrayList;
 
/**
 *
 * @author James
 */
class RunRecord {
  int nRunCount = 0;
  ArrayList<Route> arrRunRecord = new ArrayList<Route>();
  
  public RunRecord(int nRunCount) {
    this.nRunCount = nRunCount;
  }
  
  public void addRouteToRecord(Route newRoute) {
    arrRunRecord.add(newRoute);
  }
  
  public Route getShortestRun() {
    // By definition, the shortest run in a record is the last run
    return arrRunRecord.get(arrRunRecord.size() - 1);
  }
}
