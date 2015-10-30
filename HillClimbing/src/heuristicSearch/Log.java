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
  
  // Create an array to hold the distance covered in each child generation, this
  // will be divided by arrGenerationCount to get an average of each generation.
  int[] arrTotalDistanceInAGeneration = new int[500];
  // Counter that keep track of the number of times a generation has happened, 
  // this is used with arrTotalDistanceInAGeneration to calculate an average
  // distance for each generation in a particular StateGenerator.
  int[] arrGenerationCount = new int[500];
  
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

  void addRoute(int nChildGeneration, Route route) {
    // Add the route to the runRecord
    RunRecord runRecord = log.get(nCurrentRoute);   
    runRecord.addRouteToRecord(route);
    
    // Increment the generation counter
    arrGenerationCount[nChildGeneration]++;
    
    // Increment the total distance covered by this generation
    arrTotalDistanceInAGeneration[nChildGeneration] += route.getRouteLength();
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
    String sFileName = "C:\\Users\\James\\Documents\\Masters\\AI\\Assignment1\\log\\" + this.sAlgorithmName + "_ChildStatesX" + this.nChildStates + ".txt";
    System.out.println("Outputting the results to " + sFileName);
    
    WriteFile writeFile = new WriteFile(sFileName);
    writeFile.WriteToFile("Algorithm: " + this.sAlgorithmName);
    writeFile.WriteToFile("City map: " + this.sCityMap);
    writeFile.WriteToFile("Number of times this algorithm was run: " + this.nRunTimes);
    writeFile.WriteToFile("Number of child states per generation: " + this.nChildStates);
    writeFile.WriteToFile("Shortest route found was " + getShortestRoute().getRouteLength() + " units long.");
    writeFile.WriteToFile("Shortest route took " + getIterationCountOfShortestRoute() + " generations to find");   
    writeFile.WriteToFile("Shortest route:");
    writeFile.WriteToFile(getShortestRoute().printRoute());
    writeFile.WriteToFile("Average distance by generation:");
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
    ArrayList<String> arrAverageRoute = new ArrayList();
    
    for (int i = 0; i < arrGenerationCount.length; i++) {
      if (arrGenerationCount[i] > 0) {
        // At the level we're dealing with, the precision that a float will give
        // us isn't relevant.
        int nAverageDistanceForThisGeneration = (arrTotalDistanceInAGeneration[i] / arrGenerationCount[i]);
        arrAverageRoute.add(i, nAverageDistanceForThisGeneration +  "," + arrGenerationCount[i] );
      }
    }  
    return arrAverageRoute;
  }
}
