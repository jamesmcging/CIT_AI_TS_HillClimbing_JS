/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;


import java.io.BufferedReader;
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
  
  public Map(String sMapName) throws IOException {
    // Object used to read the file with the city distances
    FileReader fileReader = new FileReader(sMapName);
    // Object used to read a line from the file object
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    // Each line in the file is a string. We store it in a list of strings.
    List<String> lines = new ArrayList<String>();
    // Each line is set to null
    String line = null;
    // While the file still has lines to read...
    while ((line = bufferedReader.readLine()) != null) {
      // ...add the current line to the list lines
      lines.add(line);
    }
    // We're now finished with the  line reader
    bufferedReader.close();
    // Convert our list into an array
    String[] arrCityDistances = lines.toArray(new String[lines.size()]);
    
    // For each line in arrCityDistances we...
    for (int i = 0; i < arrCityDistances.length; i++) {
      
      // ... explode the line on space into an array of strings called distances
      String[] arrDistances = arrCityDistances[i].split(" ");
      
      // We then generate the two dimensional array that holds the distances 
      // from each city to the next city
      for (int j = 0; j < arrDistances.length; j++) {
        // We then parse the contents of the array into int
        arrMap[i][j] = Integer.parseInt(arrDistances[j]);
      }
    }
  }
  
  public void printMap() {
    for (int i = 0; i < arrMap.length; i++) {
      for (int j = 0; j < arrMap[i].length; j++) {
        System.out.println(arrMap[i][j]);
      }
    }
  }
}
