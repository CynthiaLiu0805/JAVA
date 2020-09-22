package cas2xb3_lab10;

public class Graph {
	
	
	private final int V; // number of vertices
	private int E; // number of edges
	private Bag<Integer>[] adj; // adjacency lists
	
	public Graph(int V) {
		this.V=V;
		this.E=0;
		adj= (Bag<Integer>[]) newBag[V]; // Create array of lists.
		for(int v = 0; v < V; v++)       // Initializeall lists
			adj[v] = newBag<Integer>();     // to empty
	}
	
	public Graph(In in) {
	 this(in.readInt()); // Read V and construct this graph.
	 int E = in.readInt(); // Read E.
	 for (int i = 0; i < E; i++) { // Add an edge.
		 int v = in.readInt(); // Read a vertex,
		 int w = in.readInt(); // read another vertex,
		 addEdge(v, w); // and add edge connecting them.
	 	}
	 }
	
	 public int V() { return V; }
	 public int E() { return E; }
	 
	 public void addEdge(int v, int w){
		 adj[v].add(w); // Add w to v’s list.
		 adj[w].add(v); // Add v to w’s list.
		 E++;
	 }
	 
	 public static int degree(Graph G, int v){
	  int degree = 0;
	  for (int w : G.adj(v)) degree++;
	  return degree;
	 }
	 
	 //compute maximum degree
	 public static int maxDegree(Graph G)
	 {
	  int max = 0;
	  for (int v = 0; v < G.V(); v++)
	  if (degree(G, v) > max)
	  max = degree(G, v);
	  return max;
	 }
	 
	 //compute average degree 
	 public static int avgDegree(Graph G){ 
		 return 2 * G.E() / G.V(); 
		 }
	 
	 //count self-loops
	 public static int numberOfSelfLoops(Graph G){
	  int count = 0;
	  for (int v = 0; v < G.V(); v++)
	  for (int w : G.adj(v))
	  if (v == w) count++;
	  return count/2; // each edge counted twice
	 }
	 
	 //string representation of the graph’s adjacency lists(instance method in Graph)
	 public String toString()
	 {
	  String s = V + " vertices, " + E + " edges\n";
	  for (int v = 0; v < V; v++)
	  {
	  s += v + ": ";
	  for (int w : this.adj(v))
	  s += w + " ";
	  s += "\n";
	  }
	  return s;
	 }
	 
	 
	 
	 
	 public static void main(String[] args)
	 {
	 Graph G = new Graph(new In(args[0]));
	 int s = Integer.parseInt(args[1]);
	 Search search = new Search(G, s);
	 for (int v = 0; v < G.V(); v++)
	 if (search.marked(v))
	 StdOut.print(v + " ");
	 StdOut.println();
	 if (search.count() != G.V())
	 StdOut.print("NOT ");
	 StdOut.println("connected");
	 } 
	 
	

}
