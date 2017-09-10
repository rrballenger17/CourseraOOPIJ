package graph;

import java.util.Random;

import util.GraphLoader;


public class Tests {
	
	
	
	public static void main(String[] args) {
		      // Prints "Hello, World" in the terminal window.
		      System.out.println("Hello, World");
		      
		      /*
		      Random r = new Random(5);
		      	
		      int count = 0;
		      while(count++ < 1000) {
		    	  
		    	  	System.out.println(r.nextInt(2));
		    	  
		      }*/
		      
		      Graph g = new Graph();
		      
	          GraphLoader.loadGraph(g, "data/facebook_ucsd.txt");

	          
	          g.randomlyAssignSelection();
	          
	          g.printSelectionCounts();
	          
	          int count = 0;
	          
	          while(count++ < 10) {
	        	  	g.generation();
	        	  	System.out.print(".");
	          }
	          System.out.println("");
	          g.printSelectionCounts();
	          
		      
		      
	}
	
	

}
