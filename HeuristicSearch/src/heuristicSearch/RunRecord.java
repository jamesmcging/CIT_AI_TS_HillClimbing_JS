/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package heuristicSearch;

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
  
  public Route getShortestRoute() {
    Route shortestRoute = arrRunRecord.get(0);
    
    for (int i = 0; i < arrRunRecord.size(); i++) {
      if (arrRunRecord.get(i).getRouteLength() < shortestRoute.getRouteLength()) {
        shortestRoute = arrRunRecord.get(i);
      }
    }
    return shortestRoute;
  }
}
