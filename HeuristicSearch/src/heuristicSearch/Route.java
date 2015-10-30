/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package heuristicSearch;

/**
 * Class that represents a route traveled by a salesman through the map. This 
 * was developed for the 312 city problem. If I had enough time to go over this
 * again I would make its size variable and dependent on the size of the map
 * being represented.
 * 
 * @author James
 */
class Route {
  
  int[] arrRoute = new int[312];
  
  Map map = null;
  
  public Route(Map map, int[] arrRoute) {
    this.map = map;
    this.arrRoute = arrRoute;
  }

  public String printRoute() {
    String route = "";
    for (int i = 0; i < this.arrRoute.length; i++) {
      route += this.arrRoute[i] + ", ";
    }
    return route;
  }
  
  /**
   * Method charged with calculating the length of a route.
   * 
   * @return 
   */
  int getRouteLength() {
    int nRouteLength = 0;
    int nFromCity = 0;
    int nToCity = 0;
    
    // Add up the distances between the cities in the array of city IDs
    for (int i = 0; i < (this.arrRoute.length - 1); i++) {
      nFromCity = this.arrRoute[i];
      nToCity = this.arrRoute[i + 1];
      nRouteLength += map.getDistance(nFromCity, nToCity);
    }
    
    // Add the distance from the last city to the first city
    nRouteLength += map.getDistance((arrRoute.length - 1), 0);
    
    return nRouteLength;
  }
}
