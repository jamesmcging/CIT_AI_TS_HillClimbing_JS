/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicSearch;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The first part of the assignment is to implement the Hill Climbing heuristic 
 * search algorithm. This class represents that algorithm. It can be passed 
 * various parameters than influence how it behaves and outputs its results to a
 * text file.
 * 
 * @author James
 */
abstract public class Algorithm {

  // The name of this algorithm
  static String sAlgorithmName = "Parent Algorithm";

  // The number of times to run the algorithm
  int nRunTimes = 10;
  
  // The state generator to use
  RouteGenerator routeGenerator = null;
}
