package cas2xb3_A2_Liu_CL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class EdgeWeightedDigraph {

	private static final String NEWLINE = System.getProperty("line.separator");
	private int V; // number of vertices in this digraph
	private int E; // number of edges in this digraph
	public ArrayList<ArrayList<DirectedEdge>> adj; // adj[v] = adjacency list for vertex v
	
	// assign each city a number, boston=0, nyc=1 ...etc
	private final csvData data = new csvData();
	HashMap <String, Integer> cities= data.getHashMap();  //all uppercase
	
	public EdgeWeightedDigraph() {
		this.V = 32;
		this.E = 0;
		adj = new ArrayList<ArrayList<DirectedEdge>>();
		for (int i=0;i<cities.size();i++) {
			adj.add(new ArrayList<DirectedEdge>());
		}
		String S1;
		String S2;

		try {
			Path path = Paths.get("connectedCities.txt");
			long lineCount = Files.lines(path).count();
			
			for (int i = 0; i < lineCount; i++) {
				String string1 = Files.readAllLines(Paths.get("connectedCities.txt")).get(i);
				S1 = string1.split(", ")[0];
				S2 = string1.split(", ")[1];
				int indexOfS1=cities.get(S1.toUpperCase());
				//here we got the city name, find the weight for value city eg.NYC for case boston->NYC
				ArrayList <String> restaurantList = data.getRestaurants(S2);
				HashMap<String,Double> menu = data.getAvailableMenu(restaurantList);
				//call the getWeight function in csvData class
				double weight = data.getWeight(S1,S2);
				
				if (!adj.get(indexOfS1).contains(new DirectedEdge(S1, S2, weight))) {
					adj.get(indexOfS1).add(new DirectedEdge(S1, S2, weight));
					}
				}
			
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(DirectedEdge e) {
		String v = e.from(); // the city from
		String w = e.to();  //the city to

		int indexC = data.cityName.indexOf(v.toUpperCase());
		adj.get(indexC).add(e);
		E++;
	}
	
	 public Iterable<DirectedEdge> edges() {
	        ArrayList<DirectedEdge> list = new ArrayList<DirectedEdge>();
	        for (int v = 0; v < adj.size(); v++) {
	            for (DirectedEdge e : adj.get(v)) {
	                list.add(e);
	            }
	        }
	        return list;
	    } 
}