/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillclimbing;

import java.io.IOException;

/**
 *
 * @author James
 */
public class HillClimbing {

  
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
    // Instantiate a new map and load it with the 312 city map
    Map map = new Map("C:\\Users\\James\\Documents\\Masters\\AI\\Assignment1\\CIT_AI_TS_HillClimbing_JS\\HillClimbing\\src\\hillclimbing\\312_City_Distances.txt");
    // Instantiate a new state generator
    StateGenerator stateGenerator = new StateGenerator(map);
    
    System.out.println(stateGenerator.getState().getLength());
    
  }
}
