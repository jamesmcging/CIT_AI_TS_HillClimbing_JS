/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package heuristicSearch;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class charged with loading a map from a text file and stuffing it into a 
 * two dimensional array keyed on city ID.
 * 
 * @author James
 */
public class Map {
  
  public int[][] arrMap = new int[312][312];
  
  public String sMapName = "";
  
  public Map(String sMapName) throws IOException {
    this.sMapName = sMapName;
    
    // Get the current location of the maps - talk about convoluted!
    String sFilePath = new File("").getAbsolutePath()+ "\\src\\maps\\";
    // Object used to read the file with the city distances
    FileReader fileReader = new FileReader(sFilePath + sMapName);
    // Object used to read a line from the file object
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    // Each line in the file is a string. We store it in a list of strings.
    List<String> distancesFromACity = new ArrayList<String>();
    // Each line is set to null
    String line = null;
    // While the file still has lines to read...
    while ((line = bufferedReader.readLine()) != null) {
      // ...add the current line to the list lines
      distancesFromACity.add(line);
    }
    // We're now finished with the  line reader
    bufferedReader.close();
    
    // Convert our list into an array
    String[] arrCityDistances = distancesFromACity.toArray(new String[distancesFromACity.size()]);
    
    // For each line in arrCityDistances we...
    for (int i = 0; i < distancesFromACity.size(); i++) {
      
      // ... explode the line on space into an array of strings called distances
      String[] arrDistances = distancesFromACity.get(i).split(" ");
      
      // We then generate the two dimensional array that holds the distances 
      // from each city to the next city
      for (int j = 0; j < arrDistances.length; j++) {
        // We then parse the contents of the array into int
        arrMap[i][j] = Integer.parseInt(arrDistances[j]);
      }
    }
  }
  
  public int getDistance(int nFromCityID, int nToCityID) {
    return arrMap[nFromCityID][nToCityID];
  }

  String getName() {
    return this.sMapName;
  }
}