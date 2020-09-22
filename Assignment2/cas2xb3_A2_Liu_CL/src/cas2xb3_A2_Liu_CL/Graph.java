package cas2xb3_A2_Liu_CL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graph {
	static int V;
	static int E;
    HashMap <String, ArrayList<String>> adj;

	
	public Graph() {
        adj = new HashMap<String, ArrayList<String>>();
        String S1;
        String S2;
		try { 
			Path path = Paths.get("connectedCities.txt");
			long lineCount = Files.lines(path).count();
			for (int i =0;i<lineCount;i++) {
	     	String string1 = Files.readAllLines(Paths.get("connectedCities.txt")).get(i);
	     	S1=string1.split(", ")[0];
	     	S2=string1.split(", ")[1];
//	     	System.out.println(string1);
	     	if (!adj.containsKey(S1)) {
	     		adj.put(S1, new ArrayList<String>());
	     	}
	     	if (!adj.containsKey(S2)) {
	     		adj.put(S2, new ArrayList<String>());
	     	}
	     	addEdge(S1,S2);
     	}
		}catch (IOException e) {
            e.printStackTrace();
		}
    }
 
	public void addEdge(String city1, String city2) {		
		if (!adj.get(city1).contains(city2)) {		
			adj.get(city1).add(city2);
		}
		E++;
	}
	public int V() {
        return V;
    }
	public int E() {
        return E;
    }
	

	    
}