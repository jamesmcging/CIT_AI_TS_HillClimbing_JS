/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class charged with recording the algorithm results and outputting them to a 
 * file that can be imported into Excel to generate the charts.
 * 
 * @author James
 */
class Log {
  // An internal pointer that serves to keep track of which run of the algorithm
  // we are dealing with
  int nCurrentRoute = 0;
  
  // The name of the algorithm used to generate the log
  public String sAlgorithmName = "";
  
  // The number of times the algorithm was run to generate the log
  int nRunTimes = 0;
  
  // The number of child states generated each time
  int nChildStates = 0;
  
  // The number of cities we are dealign with 
  public String sCityMap = "";
  
  // Keep all other routes in an arrayList
  ArrayList<RunRecord> log = new ArrayList();
  
  public Log(String sAlgorithmName, int nRunTimes, int nChildStates, String sCityMap) {
    this.sAlgorithmName = sAlgorithmName;
    this.nRunTimes = nRunTimes;
    this.nChildStates = nChildStates;
    this.sCityMap = sCityMap;
  }
  
  void newRun(int nRunCount) {
    nCurrentRoute = nRunCount;
    log.add(new RunRecord(nRunCount));
  }

  void addRoute(Route route) {
    RunRecord runRecord = log.get(nCurrentRoute);   
    runRecord.addRouteToRecord(route);
  }
  
  public int getNumberOfRuns() {
    return log.size();
  }
  
  /**
   * Method charged with checking the runRecords looking for the run on which 
   * the shortest route was found.
   * 
   * @return RunRecord
   */
  private RunRecord getBestRun() {
    // Set the bestRun to be the first run
    RunRecord bestRun = log.get(0);
    // Set the shortest route to be the shortest route of the first run
    Route shortestRoute = bestRun.getShortestRun();
    
    for (int i = 0; i < log.size(); i++) {
      // record the shortest run we found
      if (log.get(i).getShortestRun().getRouteLength() < shortestRoute.getRouteLength()) {
        bestRun = log.get(i);
      }
    }
    
    return bestRun;
  }
  
  /**
   * Method charged with finding the runRecord on which the most iterations 
   * took place.
   * 
   * @return int nLargestNumberOfChildStates
   */
  private int getLongestRun() {
    int nLargestNumberOfChildStates = 0;
    
    for (int i = 0; i < log.size(); i++) {
      
      RunRecord currentRunrecord = log.get(i);
      
      // record the most child iterations that we used in a run
      if (currentRunrecord.arrRunRecord.size() > nLargestNumberOfChildStates) {
        nLargestNumberOfChildStates = currentRunrecord.arrRunRecord.size();
      }
    }
    
    return nLargestNumberOfChildStates;
  }
  
  public Route getShortestRoute() {
    RunRecord bestRun = this.getBestRun(); 
    return bestRun.getShortestRun();
  }
  
  private int getIterationCountOfShortestRoute() {
    RunRecord bestRun = this.getBestRun();
    return bestRun.arrRunRecord.size();
  }
  
  void printShortestRouteRecord() {
    System.out.println("Algorithm: " + this.sAlgorithmName);
    System.out.println("City map: " + this.sCityMap);
    System.out.println("Number of iterations this algorithm was run: " + this.nRunTimes);
    System.out.println("Shortest route found was " + getShortestRoute().getRouteLength() + " units long.");    
    System.out.println("Shortest route was: " + getIterationCountOfShortestRoute() + " iterations long");
    getShortestRoute().printRoute();
  }
  
  void printLogToFile() throws IOException {
    String sFileName = "C:\\Users\\James\\Documents\\Masters\\AI\\Assignment1\\log\\" + this.sAlgorithmName + ".txt";
    System.out.println("Outputting the results to " + sFileName);
    
    WriteFile writeFile = new WriteFile(sFileName);
    writeFile.WriteToFile("Algorithm: " + this.sAlgorithmName);
    writeFile.WriteToFile("City map: " + this.sCityMap);
    writeFile.WriteToFile("Number of iterations this algorithm was run: " + this.nRunTimes);
    writeFile.WriteToFile("Shortest route found was " + getShortestRoute().getRouteLength() + " units long.");
    writeFile.WriteToFile("Shortest route was: " + getIterationCountOfShortestRoute() + " iterations long");   
    writeFile.WriteToFile(getShortestRoute().printRoute());
    writeFile.WriteToFile("--------------------------------------------------");
    
    ArrayList<String> arrAverageRunTimes = this.getAverageRunTimes();
    for (int i = 0; i < arrAverageRunTimes.size(); i++) {
      writeFile.WriteToFile(arrAverageRunTimes.get(i));
    }
  }

  /**
   * Method charged with averaging the values of the different iterations for 
   * this series of runs. We end up with a comma separated list looking like 
   * this:
   * 
   * Iteration, Number of times this iteration was found, average distance of this iteration
   * 
   * @return 
   */
  private ArrayList<String> getAverageRunTimes() {
    ArrayList<String> arrAverageRunTimes = new ArrayList();
    // This array list will hold the number of times a particularl iteration occurred
    ArrayList<Integer> arrIterationCount = new ArrayList();
    // This array list will hold the distances reached on this iteration
    ArrayList<Integer> arrDistance = new ArrayList();
    
    // Iterate over all the result sets
    for (int i = 0; i < log.size(); i++) {
      RunRecord runRecord = log.get(i);

      // Iterate over the routes in this result set to get the total distances
      // and number of times reached so that we can work out some averages
      for (int j = 0; j < runRecord.arrRunRecord.size(); j++) {
        // Increment the counter of the number of times this child state has been 
        // reached.
        int nCurrentCount = arrIterationCount.get(j);
        arrIterationCount.add(j, (nCurrentCount + 1));
        
        // Add the distance reached on this route to the total in arrDistance
        int nCurrentDistanceTotal = arrDistance.get(j);
        arrDistance.add(j, (nCurrentDistanceTotal + runRecord.arrRunRecord.get(j).getRouteLength()));
      }
    }
    
    for (int i = 0; i < arrIterationCount.size(); i++) {
      System.out.println("Iteration " + i + " : " + arrIterationCount.get(i));
    }
    
    // Now calculate the averages for each iteration
    for (int i = 0; i < arrIterationCount.size(); i++) {
      String sString = i + " " + (arrIterationCount.get(i) + " " + (arrDistance.get(i) / arrIterationCount.get(i)));
      arrAverageRunTimes.add(i, sString);
    }
    
    return arrAverageRunTimes;
  }
}
