/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	
	
	private HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
	
	

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub

		if(!graph.keySet().contains(num)){
			graph.put(num, new  HashSet<Integer>());
		}
		
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub

		if(!graph.keySet().contains(from) || !graph.keySet().contains(to)){
			return;
		}
		
		HashSet<Integer> list = graph.get(from);
		list.add(to);
		
		graph.put(from, list);
		
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		
		
		HashSet<Integer> neighs = graph.get(center);
		
		if(neighs == null){
			return new CapGraph();
		}
		
		Graph result = new CapGraph();
		
		neighs.add(center);
		
		for(int n: neighs){
			result.addVertex(n);
		}

		for(int n: neighs){
			HashSet<Integer> connections = graph.get(n);
			
			for( int c: connections){
				if(neighs.contains(c) && n != c ){
					result.addEdge(n, c);
				}
			}

		}		
		
		return result;
	}
	
	
	Set<Integer> visited;
	Stack finished ;
	HashMap<Integer, HashSet<Integer>> graphT; 
	
	
	
	
	public void findTree(Integer r, Graph g){
		
		g.addVertex(r);
		
		
		
		visited.add(r);
		
		for(int n: graphT.get(r)){
			
			if(!visited.contains(n)) findTree(n, g);
		}
		
	}
	
	
	
	public Graph connComponents(Integer r){
		
		
		CapGraph g = new CapGraph();
			
		findTree(r, g);
		
		
		return g;
		
	}
	
	
	public void dfsVisit(Integer v){
		
		visited.add(v);
		
		

		
		for(Integer n: graph.get(v)){
			
			if(!visited.contains(n)){
				
				dfsVisit(n);
			}
					
		}
		
		finished.push(v);
	}
	
	
	private void computeTranspose(){
		graphT = new HashMap<Integer, HashSet<Integer>>();

		for(int i: graph.keySet()){
			
			graphT.put(i, new HashSet<Integer>());
			
		}
		
		for(int curr: graph.keySet()){
			HashSet<Integer> neighs = graph.get(curr);
			
			for(Integer n: neighs){
				HashSet<Integer> neighSet = graphT.get(n);
				neighSet.add(curr);
				graphT.put(n, neighSet);
			}
		}

	}
	

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		
		visited = new HashSet<Integer>();
		finished = new Stack();
		
		
		
		Set<Integer> verts = graph.keySet();
		
		if(verts.size() == 0) return new ArrayList<Graph>();
		
		for(Integer v: verts){
			if(!visited.contains(v)){
				
				dfsVisit(v);
				
			}	
		}
		
		
		computeTranspose();
		
		
		visited = new HashSet<Integer>();
		
		List<Graph> result = new ArrayList<Graph>();
		
		while(!finished.isEmpty()){
			Integer node = (Integer) finished.pop();
			

			if(!visited.contains(node)){
				
				Graph g = connComponents(node);

				Set<Integer> connNodes = g.exportGraph().keySet();
				
				for(Integer x: connNodes){
					for(Integer y: connNodes){
						if(graph.get(x).contains(y)){
							g.addEdge(x, y);
						}
					}
				}
				
				result.add(g);
				


			}
			

		}
		
		
		return result;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return graph;
	}

}
