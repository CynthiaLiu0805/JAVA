package cas2xb3_A2_Liu_CL;
import java.util.*;
import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirstSearch {
	HashMap<String, Boolean> marked;
	HashMap<String, String> edgeTo;
	HashMap<String, Integer> distTo;
	String s;
	int count=0;

	//initialize the bfs
	public BreadthFirstSearch(Graph G, String s) {
		marked = new HashMap<String, Boolean>();
		edgeTo = new HashMap<String, String>();
		this.s = s;
		for (String w : G.adj.keySet()) {
			marked.put(w, false);
			edgeTo.put(w, null);
		}		
		bfs(G, s);		
	}

	//use queue to 
	private void bfs(Graph G, String s) {
		count++;
		Queue<String> q = new LinkedList<String>();
        marked.replace(s,true);
        q.add(s);

		int count=0;
		while (count<G.adj.size()) {
			String c = q.peek();

			q.remove();
			for (String w : G.adj.get(c)) {
				if (!marked.get(w)) {
					edgeTo.replace(w, c);
					marked.replace(w, true);
					q.add(w);
				}
			count++;
			}
		}
	}

	public boolean hasPathTo(String v) {
		return marked.get(v);
	}

	public Iterable<String> pathTo(String v) {
		if (!hasPathTo(v)) {
			return null;
		}
		List<String> path = new ArrayList<>();
		int counter = 0;
		while (counter < edgeTo.size()) {
			if (edgeTo.get(v) == null)
				break;
			path.add(v);
			v = edgeTo.get(v);
			counter++;
		}
		path.add(s);
		Collections.reverse(path);
		return path;
    }
}
