/*
 * Cork Institute of Technology
 * Masters in Software Engineering
 * Artificial Intelligence
 * Assignment 1 - Heuristic Search Algorithms
 * Random Restart HillClimbing
 */
package hillclimbing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author James
 */
public class WriteFile {
  
  private String sFilePath;
  
  public WriteFile(String sFilePath) {
    this.sFilePath = sFilePath;
  }
  
  public void WriteToFile(String sTextLine) throws IOException {
    FileWriter objFileWriter = new FileWriter(sFilePath, true);
    PrintWriter objPrintWriter = new PrintWriter(objFileWriter);
    
    objPrintWriter.printf("%s" + "%n", sTextLine);
    objPrintWriter.close();
  }
}
