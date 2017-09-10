package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;



public class Graph {

	private HashMap<Integer, Node> graph = new HashMap<Integer, Node>();
	
	private Map<Integer, Double> benefitFactors = getBenefitFactors();
	
	private Random myRand = new Random();
	
	private Map<Integer, Double> getBenefitFactors() {
		
		Map<Integer, Double> factors = new HashMap<Integer, Double>();
		
		factors.put(0, .75);
		factors.put(1, .75);
		
		return factors;
		
	}
	
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	//@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if(!graph.keySet().contains(num)){
			graph.put(num, new Node());
			graph.get(num).id = num;
		}
	}
	
	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	//@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		Node theNode = graph.get(from);
		theNode.connections.add(to);
				
	}

	
	public void randomlyAssignSelection() {
		Random rand = new Random();
		for(int id: graph.keySet()) {
			Node node = graph.get(id);
			node.selection = rand.nextInt(2);
		}
		
	}
	
	
	public void printState() {
		for(int id: graph.keySet()) {
			Node node = graph.get(id);
			System.out.println("node: " + node.id + " selection: " + node.selection);
		}
	}

	
	public void printSelectionCounts() {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int id: graph.keySet()) {
			Node node = graph.get(id);
			if(map.containsKey(node.selection)) {
				map.put(node.selection, map.get(node.selection) + 1);
			}else {
				map.put(node.selection, 1);
			}
		}
		
		for(int selection: map.keySet()) {
			System.out.println("Selection: " + selection + " Count: " + map.get(selection));
		}	
	}
	
	public void generation() {
		
		// next gen selections
		Map<Integer, Integer> newSelections = new HashMap<Integer, Integer>();
		
		for(int id: graph.keySet()) {
			
			Node n = graph.get(id);
			
			// neighbors selections and counts
			Map<Integer, Integer> neighsChoices = new HashMap<Integer, Integer>();
			
			for(int neigh: n.connections) {
				
				int nSelection = graph.get(neigh).selection;
				
				if(neighsChoices.containsKey(nSelection)) {
					int newCount = neighsChoices.get(nSelection) + 1;
					neighsChoices.put(nSelection, newCount);
				}else {
					neighsChoices.put(nSelection, 1);
				}
			}
			
			// determine next gen choice
			int newPick = -1;
			double newPickBenefit = -1.0;
			
			for(int option: neighsChoices.keySet()) {
				
				int optionCount = neighsChoices.get(option);
				
				//double benefitFactor = .5;//benefitFactors.get(option);
				
				double optionBenefit = (optionCount + 0.0); //* benefitFactor; 
				
				if(optionBenefit > newPickBenefit) {
					
					newPickBenefit = optionBenefit;
					newPick = option;
				}else if(optionBenefit == newPickBenefit) {
					if(myRand.nextBoolean()) {
						newPickBenefit = optionBenefit;
						newPick = option;
					}
				}
			}
			// save next gen choice
			newSelections.put(id, newPick);
			
		}
		
		// update nodes with next gen choice
		for(int id: newSelections.keySet()) {
			graph.get(id).selection = newSelections.get(id);
		}

		
	}
	
	
	

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	//@Override
	public HashMap<Integer, Node> exportGraph() {
		// TODO Auto-generated method stub
		return graph;
	}
	
} 
