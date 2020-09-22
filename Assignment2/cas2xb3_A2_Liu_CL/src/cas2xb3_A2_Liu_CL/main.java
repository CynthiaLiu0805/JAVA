package cas2xb3_A2_Liu_CL;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.HashMap;

public class main {

	private static final String String = null;
	// the function that write to a text file
	public static void writeToFile(String s) {
		try {
			FileWriter f = new FileWriter("a2_out.txt", true);
			f.write(s);
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// remove all content in the text file
	public static void clean() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("a2_out.txt");
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		clean();
		Graph gh = new Graph();

		String city1 = "Boston";
		String city2 = "Minneapolis";

		csvData dt = new csvData();

		// create BFS and DFS objects
		DepthFirstSearch dfs = new DepthFirstSearch(gh, city1);
		BreadthFirstSearch bfs = new BreadthFirstSearch(gh, city1);

		// Routine for BFS
		Iterable<String> bfsResult = bfs.pathTo(city2);
		
		long size1 = bfsResult.spliterator().getExactSizeIfKnown();
		writeToFile("BFS:");
		int counter1 = 0;
		// write to file with "," beside the last one
		for (String s : bfsResult) {
			if (counter1 < size1 - 1) {
				writeToFile(s);
				writeToFile(", ");
			} else {
				writeToFile(s);
			}
			counter1++;
		}

		writeToFile("\n");

		// Routine for DFS
		Iterable<String> dfsResult = dfs.pathTo(city2);
		long size2 = dfsResult.spliterator().getExactSizeIfKnown();
		writeToFile("DFS:");
		int counter2 = 0;
		for (String s : dfsResult) {
			if (counter2 < size2 - 1) {
				writeToFile(s);
				writeToFile(", ");
			} else {
				writeToFile(s);
			}
			counter2++;
		}

		writeToFile("\n");
		writeToFile("\n");

		final csvData csvD = new csvData();
		HashMap<String, Integer> cities = csvD.getHashMap();

		// create objects with the starting city
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph();
		DijkstraSP dsp = new DijkstraSP(ewd, cities.get("Boston".toUpperCase()));
		Iterable<DirectedEdge> dspResult = dsp.pathTo(cities.get("Minneapolis".toUpperCase()));
		Formatter formatter = new Formatter();
		writeToFile(String.format("%20s %20s %20s", "City", "Meal Choice", "Cost of Meal"));
		writeToFile("\n");
		for (DirectedEdge s : dspResult) {
			String city = s.to();
			csvD.findMeal(csvD.getAvailableMenu(csvD.getRestaurants("Seattle")), csvD.meals, s.from(), s.to());
			String meal = csvD.getMeal(s.to());
			String cost = String.valueOf(csvD.availableMenu.get(meal));
			if (meal.length() > 20) {
				writeToFile(String.format("%20s %20s %10s", city, meal, cost));
			} else {
				writeToFile(String.format("%20s %20s %16s", city, meal, cost));
			}
			writeToFile("\n");
		}
	}
}
