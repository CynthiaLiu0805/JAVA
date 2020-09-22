package cas2xb3_A2_Liu_CL;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class mainTest {

	@Test
	void bfstest1() {
		String city1 = "Boston";
		String city3 = "St. Louis";
		Graph gh = new Graph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(gh, city1);
		Iterable<String> bfstest1 = bfs.pathTo(city3);
		int counter1=0;
		ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList("Boston", "New York City", "Pittsburgh", "Columbus", "Indianapolis", "St. Louis"));
		for (String s: bfstest1) {
			assertEquals(s,expectedOutput.get(counter1));
			counter1++;
		}
	}
	@Test
    void dfstest1() {
		String city1 = "Boston";
		String city3 = "St. Louis";
		Graph gh = new Graph();
		DepthFirstSearch dfs = new DepthFirstSearch(gh, city1);
		Iterable<String> dfstest1 = dfs.pathTo(city3);
		int counter1=0;
		ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList("Boston", "New York City", "Philadelphia", "Baltimore", "Washington", "Charlotte", "Atlanta", "Nashville", "Columbus", "Indianapolis", "St. Louis"));
		for (String s: dfstest1) {
			assertEquals(s,expectedOutput.get(counter1));
			counter1++;
		}
	}
	
	//the bfs route between two same cities
	@Test
	void bfstestSameCity() {
		String city1 = "Denver";
		String city2 = "Los Angeles";
		Graph gh = new Graph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(gh, city1);
		Iterable<String> bfstest2 = bfs.pathTo(city2);
		int counter1=0;
		ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList("Denver", "Salt Lake City", "Las Vegas", "Los Angeles"));
		for (String s: bfstest2) {
			assertEquals(s,expectedOutput.get(counter1));
			counter1++;
		}
	}
	
	//same city as last one but starting and destination change
	@Test
	void bfstestSameCityReverse() {
		String city1 = "Los Angeles";
		String city2 = "Denver";
		Graph gh = new Graph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(gh, city1);
		Iterable<String> bfstest2 = bfs.pathTo(city2);
		int counter1=0;
		ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList("Los Angeles", "Phoenix", "Albuquerque", "Denver"));
		for (String s: bfstest2) {
			assertEquals(s,expectedOutput.get(counter1));
			counter1++;
		}
	}
	
	
	//the case that there is no route from city1 to city2 for dfs
	@Test
	void dfstestNoRoute() {
		String city1 = "Seattle";
		String city2 = "New York City";
		Graph gh = new Graph();
		DepthFirstSearch dfs = new DepthFirstSearch(gh, city1);
		Iterable<String> dfstest2 = dfs.pathTo(city2);
		Object expectedOutput = null;
		assertEquals(dfstest2,null);
	}
	
	//the case that there is no route from city1 to city2 for bfs
	@Test
	void bfstestNoRoute() {
		String city1 = "Seattle";
		String city2 = "New York City";
		Graph gh = new Graph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(gh, city1);
		Iterable<String> dfstest2 = bfs.pathTo(city2);
		Object expectedOutput = null;
		assertEquals(dfstest2,null);
	}

	//case that two cities directly connected for bfs
	@Test
	void bfstestDirectlyConnected() {
		String city1 = "Houston";
		String city2 = "New Orleans";
		Graph gh = new Graph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(gh, city1);
		Iterable<String> bfstest3 = bfs.pathTo(city2);
		int counter1=0;
		ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList("Houston", "New Orleans"));
		for (String s: bfstest3) {
			assertEquals(s,expectedOutput.get(counter1));
			counter1++;
		}
	}
	
	//case that two cities directly connected for dfs
	@Test
	void dfstestDirectlyConnected() {
		String city1 = "Jacksonville";
		String city2 = "Miami";
		Graph gh = new Graph();
		DepthFirstSearch bfs = new DepthFirstSearch(gh, city1);
		Iterable<String> bfstest3 = bfs.pathTo(city2);
		int counter1=0;
		ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList("Jacksonville", "Miami"));
		for (String s: bfstest3) {
			assertEquals(s,expectedOutput.get(counter1));
			counter1++;
		}
	}
	
}
